package id16den.cs.umu.lab1thirty;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;

/**
 * @author David Eriksson, ID16
 * Class responsible for handling array of dice.
 */
public class DieHandler implements Parcelable {

    ArrayList<Die> dieArrayList;

    /**
     * Method: DieHandler
     * Constructor for DieHandler class
     */
    public DieHandler() {
        dieArrayList = new ArrayList<>();
        init();
    }

    /**
     * Method: DieHandler
     * Constructor for Parcelable DieHandler object
     * @param in
     */
    public DieHandler(Parcel in) {
        dieArrayList = new ArrayList<>();

        for (int i = 0; i < 6; i ++) {
            dieArrayList.add(new Die(in.readInt(),in.readInt() == 1));
        }
    }

    /**
     * Method: init
     * Used to initiate each die within array to the value 1, and set the die to inactive
     */
    private void init() {
        for (int i = 0; i < 6; i ++) {
            dieArrayList.add(new Die(1,false));
        }
    }

    /**
     * Method: roll
     * "Rolls" each die randomly in the array within the range 1-6
     */
    public void roll() {
        for (Die die : dieArrayList) {
            int random = 1 + (int)(Math.random() * ((6 - 1) + 1));
            if(!die.checkActive()) {
                die.setValue(random);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        for(Die die: dieArrayList) {
            dest.writeInt(die.getValue());
            dest.writeInt(die.checkActive()?1:0);
        }
    }

    /**
     * Method: Parcelable.Creator: DieHandler
     * Creates a parcelable object for the DieHandler class
     */
    public static final Parcelable.Creator<DieHandler> CREATOR
            = new Parcelable.Creator<DieHandler>() {
        public DieHandler createFromParcel(Parcel in) {
            return new DieHandler(in);
        }

        public DieHandler[] newArray(int size) {
            return new DieHandler[size];
        }
    };
    
}
