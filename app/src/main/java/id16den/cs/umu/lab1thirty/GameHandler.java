package id16den.cs.umu.lab1thirty;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Eriksson, ID16
 * GameHandler class, handles all game logic and operations.
 */
public class GameHandler implements Parcelable {

    // Game variables.
    public int maxRounds, roundScore, totalScore, selectedSpinnerValue;
    public ArrayList<Integer> dieScoreList;
    public ArrayList<String> spinnerItemsToBeDeleted;
    public Map<Integer,String> scoresMap;

    /**
     * Method: GameHandler
     * Constructor for GameHandler class
     */
    public GameHandler() {
        dieScoreList = new ArrayList<>();
        scoresMap = new HashMap<>();
        spinnerItemsToBeDeleted = new ArrayList<>();
        maxRounds = 0;
        roundScore = 0;
        totalScore = 0;
    }

    /**
     * Method: GameHandler
     * Constructor for Parcelable GameHandler object
     * @param in
     */
    public GameHandler(Parcel in) {
        dieScoreList = new ArrayList<>();
        spinnerItemsToBeDeleted = new ArrayList<>();
        scoresMap = new HashMap<>();
        maxRounds = in.readInt();
        roundScore = in.readInt();
        totalScore = in.readInt();
        selectedSpinnerValue = in.readInt();
    }

    /**
     * Method: calculateScoreForSelectedSpinnerValue
     * Calculates the score for a selected spinner value
     * @param spinnerVal: Used to calculate score for the selected spinner value.
     */
    public void calculateScoreForSelectedSpinnerValue(String spinnerVal) {
        if (!spinnerVal.equals("LOW")) {
            int temp = 0;
            selectedSpinnerValue = Integer.parseInt(spinnerVal);
            for (int i = 0; i < dieScoreList.size(); i++) {
                temp += dieScoreList.get(i);
            }
            if(temp % selectedSpinnerValue == 0) {
                roundScore = temp;
                totalScore += temp;
                scoresMap.put(selectedSpinnerValue,String.valueOf(roundScore));
            }
        } else {
            for (int i = 0; i < dieScoreList.size(); i++) {
                // Remove all dice that have a value greater than 3 as these do not give points
                // towards the "LOW"-spinner value
                if(dieScoreList.get(i) > 3) {
                    dieScoreList.remove(i);
                    i--;
                } else {
                    roundScore += dieScoreList.get(i);
                }
            }
            scoresMap.put(1,String.valueOf(roundScore));
            totalScore += roundScore;
        }
        dieScoreList.clear();
    }

    /**
     * Method: addDieScoreToList
     * @param val: Add the score of a selected die to the list.
     */
    public void addDieScoreToList(int val) {
        dieScoreList.add(val);
    }

    /**
     * Method: setSpinnerItemsToBeDeleted
     * @param s: The spinner value that should be deleted from the array list of available spinner
     * values.
     */
    public void setSpinnerItemsToBeDeleted(String s){
        spinnerItemsToBeDeleted.add(s);
    }

    /**
     * Method: increaseMaxRounds
     */
    public void increaseMaxRounds(){
        maxRounds += 1;
    }

    /**
     * Method: resetMaxRounds
     */
    public void resetMaxRounds() {
        maxRounds = 0;
    }

    /**
     * Method: getMaxRounds
     * @return maxRounds;
     */
    public int getMaxRounds() {
        return this.maxRounds;
    }

    /**
     * Method: Parcelable.Creator: GameHandler
     * Creates a parcelable object for the GameHandler class
     */
    public static final Parcelable.Creator<GameHandler> CREATOR
            = new Parcelable.Creator<GameHandler>() {
        public GameHandler createFromParcel(Parcel in) {
            return new GameHandler(in);
        }

        public GameHandler[] newArray(int size) {
            return new GameHandler[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maxRounds);
        dest.writeInt(roundScore);
        dest.writeInt(totalScore);
        dest.writeInt(selectedSpinnerValue);
        dest.writeArray(new ArrayList[]{dieScoreList});
        dest.writeArray(new ArrayList[]{spinnerItemsToBeDeleted});
        dest.writeMap(scoresMap);
    }
}
