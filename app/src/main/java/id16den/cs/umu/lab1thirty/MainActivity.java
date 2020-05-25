package id16den.cs.umu.lab1thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author David Eriksson, ID16
 * Main class for application, handles all view elements of the main activity.
 */
public class MainActivity extends AppCompatActivity {

    // Tags for restoring data on screen rotation.
    private static final String ARRAY_TAG = "MainActivity.dieHandler";
    private static final String GAME_TAG = "MainActivity.gameHandler";

    // Variables used globally by the application within the main activity.
    public DieHandler dieHandler = new DieHandler();
    public GameHandler gameHandler = new GameHandler();
    public TextView totalScore;
    private Button diceButtonOne, diceButtonTwo, diceButtonThree, diceButtonFour, diceButtonFive, diceButtonSix;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private boolean viewRefreshed = false;

    /**
     * Method: onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Removes standard Android action bar from application.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //Enable full-screen.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
            dieHandler = savedInstanceState.getParcelable(ARRAY_TAG);
            gameHandler = savedInstanceState.getParcelable(GAME_TAG);
            for(Die die: dieHandler.dieArrayList){
                Log.d("die: ", String.valueOf(die.getValue()));
                refreshViewOnReload();
            }
        }

        final Button rollDiceButton = findViewById(R.id.rollDiceButton);
        final Button takeScoreButton = findViewById(R.id.takeScoreButton);

        // If activity has been destroyed application should not re-render the old array list of
        // spinner values.
        if(!viewRefreshed) {
            Resources res = getResources();
            String[] spinnerValues = res.getStringArray(R.array.spinnerItems);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,new ArrayList<>(Arrays.asList(spinnerValues)));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner = findViewById(R.id.scoreValueSpinner);
            spinner.setAdapter(adapter);
        }


        buttonActiveHandler();

        // onClickListener for rollDiceButton
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameHandler.increaseMaxRounds();
                //Player should not be able to roll more than three times.
                if(gameHandler.getMaxRounds() <= 3) {
                    rollDiceButtonHandler();
                }
            }
        });

        // onClickListener for takeScoreButton
        takeScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Player should not be able to claim a score without rolling at least once.
                if(gameHandler.getMaxRounds() > 0) {
                    takeScoreButtonHandler();
                }
            }
        });
    }

    /**
     * Method: rollDiceButtonHandler
     * Handles rolling of die.
     */
    private void rollDiceButtonHandler() {
        dieHandler.roll();
        diceButtonOne = findViewById(R.id.diceButtonOne);
        diceButtonTwo = findViewById(R.id.diceButtonTwo);
        diceButtonThree = findViewById(R.id.diceButtonThree);
        diceButtonFour = findViewById(R.id.diceButtonFour);
        diceButtonFive = findViewById(R.id.diceButtonFive);
        diceButtonSix = findViewById(R.id.diceButtonSix);

        for (int i = 0; i < dieHandler.dieArrayList.size(); i++) {
            if(i == 0 && !dieHandler.dieArrayList.get(i).checkActive()){
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 1  && !dieHandler.dieArrayList.get(i).checkActive()) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 2 && !dieHandler.dieArrayList.get(i).checkActive()) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 3 && !dieHandler.dieArrayList.get(i).checkActive()) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 4 && !dieHandler.dieArrayList.get(i).checkActive()) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 5 && !dieHandler.dieArrayList.get(i).checkActive()) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            }
        }
    }

    /**
     * Method: diceButtonImageHandler
     * @param die: Which die to update image for.
     * @param val: The new value to update image to.
     */
    public void diceButtonImageHandler(int die, int val) {
        switch(die) {
            case 0:
                if (val == 1 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.reddiceone);
                } else if (val == 1 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.greydiceone);
                } else if (val == 2 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.reddicetwo);
                } else if (val == 2 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.greydicetwo);
                } else if (val == 3 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.reddicethree);
                } else if (val == 3 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.greydicethree);
                } else if (val == 4 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.reddicefour);
                } else if (val == 4 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.greydicefour);
                } else if (val == 5 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.reddicefive);
                } else if (val == 5 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.greydicefive);
                } else if (val == 6 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.reddicesix);
                } else if (val == 6 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonOne.setBackgroundResource(R.drawable.greydicesix);
                } break;
            case 1:
                if (val == 1 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.reddiceone);
                } else if (val == 1 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.greydiceone);
                } else if (val == 2 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.reddicetwo);
                } else if (val == 2 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.greydicetwo);
                } else if (val == 3 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.reddicethree);
                } else if (val == 3 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.greydicethree);
                } else if (val == 4 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.reddicefour);
                } else if (val == 4 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.greydicefour);
                } else if (val == 5 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.reddicefive);
                } else if (val == 5 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.greydicefive);
                } else if (val == 6 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.reddicesix);
                } else if (val == 6 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonTwo.setBackgroundResource(R.drawable.greydicesix);
                } break;
            case 2:
                if (val == 1 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.reddiceone);
                } else if (val == 1 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.greydiceone);
                } else if (val == 2 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.reddicetwo);
                } else if (val == 2 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.greydicetwo);
                } else if (val == 3 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.reddicethree);
                } else if (val == 3 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.greydicethree);
                } else if (val == 4 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.reddicefour);
                } else if (val == 4 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.greydicefour);
                } else if (val == 5 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.reddicefive);
                } else if (val == 5 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.greydicefive);
                } else if (val == 6 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.reddicesix);
                } else if (val == 6 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonThree.setBackgroundResource(R.drawable.greydicesix);
                } break;
            case 3:
                if (val == 1 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.reddiceone);
                } else if (val == 1 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.greydiceone);
                } else if (val == 2 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.reddicetwo);
                } else if (val == 2 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.greydicetwo);
                } else if (val == 3 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.reddicethree);
                } else if (val == 3 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.greydicethree);
                } else if (val == 4 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.reddicefour);
                } else if (val == 4 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.greydicefour);
                } else if (val == 5 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.reddicefive);
                } else if (val == 5 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.greydicefive);
                } else if (val == 6 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.reddicesix);
                } else if (val == 6 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFour.setBackgroundResource(R.drawable.greydicesix);
                } break;
            case 4:
                if (val == 1 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.reddiceone);
                } else if (val == 1 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.greydiceone);
                } else if (val == 2 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.reddicetwo);
                } else if (val == 2 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.greydicetwo);
                } else if (val == 3 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.reddicethree);
                } else if (val == 3 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.greydicethree);
                } else if (val == 4 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.reddicefour);
                } else if (val == 4 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.greydicefour);
                } else if (val == 5 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.reddicefive);
                } else if (val == 5 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.greydicefive);
                } else if (val == 6 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.reddicesix);
                } else if (val == 6 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonFive.setBackgroundResource(R.drawable.greydicesix);
                } break;
            case 5:
                if (val == 1 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.reddiceone);
                } else if (val == 1 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.greydiceone);
                } else if (val == 2 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.reddicetwo);
                } else if (val == 2 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.greydicetwo);
                } else if (val == 3 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.reddicethree);
                } else if (val == 3 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.greydicethree);
                } else if (val == 4 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.reddicefour);
                } else if (val == 4 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.greydicefour);
                } else if (val == 5 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.reddicefive);
                } else if (val == 5 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.greydicefive);
                } else if (val == 6 && dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.reddicesix);
                } else if (val == 6 && !dieHandler.dieArrayList.get(die).checkActive()) {
                    diceButtonSix.setBackgroundResource(R.drawable.greydicesix);
                } break;
        }
    }

    /**
     * Method: takeScoreButtonHandler
     * Handles when player presses takeScoreButton, also sends application to Endscreen activity
     * when spinner is empty and passes necessary data to Endscreen activity.
     */
    private void takeScoreButtonHandler() {
        totalScore = findViewById(R.id.totalScoreCount);
        String selectedSpinnerValue = spinner.getSelectedItem().toString();
        adapter.remove((String) spinner.getSelectedItem());
        gameHandler.setSpinnerItemsToBeDeleted(selectedSpinnerValue);

        for(int i = 0; i < dieHandler.dieArrayList.size(); i++) {
            if(i == 0 && dieHandler.dieArrayList.get(i).checkActive()) {
                gameHandler.addDieScoreToList(dieHandler.dieArrayList.get(i).getValue());
            } else if(i == 1 && dieHandler.dieArrayList.get(i).checkActive()) {
                gameHandler.addDieScoreToList(dieHandler.dieArrayList.get(i).getValue());
            } else if(i == 2 && dieHandler.dieArrayList.get(i).checkActive()) {
                gameHandler.addDieScoreToList(dieHandler.dieArrayList.get(i).getValue());
            } else if(i == 3 && dieHandler.dieArrayList.get(i).checkActive()) {
                gameHandler.addDieScoreToList(dieHandler.dieArrayList.get(i).getValue());
            } else if(i == 4 && dieHandler.dieArrayList.get(i).checkActive()) {
                gameHandler.addDieScoreToList(dieHandler.dieArrayList.get(i).getValue());
            } else if(i == 5 && dieHandler.dieArrayList.get(i).checkActive()) {
                gameHandler.addDieScoreToList(dieHandler.dieArrayList.get(i).getValue());
            }
        }

        gameHandler.calculateScoreForSelectedSpinnerValue(selectedSpinnerValue);
        gameHandler.resetMaxRounds();
        resetDice();

        // Update total score TextView in main activity.
        totalScore.setText(String.valueOf(gameHandler.totalScore));

        if(adapter.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, Endscreen.class);
            intent.putExtra("scoresMap", (Serializable) gameHandler.scoresMap);
            intent.putExtra("totalEndScore", gameHandler.totalScore);
            startActivity(intent);
        }
    }

    /**
     * Method: buttonActiveHandler
     * Handles onClick events for each die.
     */
    private void buttonActiveHandler() {

        diceButtonOne = findViewById(R.id.diceButtonOne);
        diceButtonTwo = findViewById(R.id.diceButtonTwo);
        diceButtonThree = findViewById(R.id.diceButtonThree);
        diceButtonFour = findViewById(R.id.diceButtonFour);
        diceButtonFive = findViewById(R.id.diceButtonFive);
        diceButtonSix = findViewById(R.id.diceButtonSix);

        diceButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dieHandler.dieArrayList.get(0).checkActive() && gameHandler.getMaxRounds() > 0) {
                    dieHandler.dieArrayList.get(0).setActive();
                    diceButtonImageHandler(0,dieHandler.dieArrayList.get(0).getValue());
                } else {
                    dieHandler.dieArrayList.get(0).setInactive();
                    diceButtonImageHandler(0,dieHandler.dieArrayList.get(0).getValue());
                }
            }
        });

        diceButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dieHandler.dieArrayList.get(1).checkActive() && gameHandler.getMaxRounds() > 0) {
                    dieHandler.dieArrayList.get(1).setActive();
                    diceButtonImageHandler(1,dieHandler.dieArrayList.get(1).getValue());
                } else {
                    dieHandler.dieArrayList.get(1).setInactive();
                    diceButtonImageHandler(1,dieHandler.dieArrayList.get(1).getValue());
                }
            }
        });

        diceButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dieHandler.dieArrayList.get(2).checkActive() && gameHandler.getMaxRounds() > 0) {
                    dieHandler.dieArrayList.get(2).setActive();
                    diceButtonImageHandler(2,dieHandler.dieArrayList.get(2).getValue());
                } else {
                    dieHandler.dieArrayList.get(2).setInactive();
                    diceButtonImageHandler(2,dieHandler.dieArrayList.get(2).getValue());
                }
            }
        });

        diceButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dieHandler.dieArrayList.get(3).checkActive() && gameHandler.getMaxRounds() > 0) {
                    dieHandler.dieArrayList.get(3).setActive();
                    diceButtonImageHandler(3,dieHandler.dieArrayList.get(3).getValue());
                } else {
                    dieHandler.dieArrayList.get(3).setInactive();
                    diceButtonImageHandler(3,dieHandler.dieArrayList.get(3).getValue());
                }
            }
        });

        diceButtonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dieHandler.dieArrayList.get(4).checkActive() && gameHandler.getMaxRounds() > 0) {
                    dieHandler.dieArrayList.get(4).setActive();
                    diceButtonImageHandler(4,dieHandler.dieArrayList.get(4).getValue());
                } else {
                    dieHandler.dieArrayList.get(4).setInactive();
                    diceButtonImageHandler(4,dieHandler.dieArrayList.get(4).getValue());
                }
            }
        });

        diceButtonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dieHandler.dieArrayList.get(5).checkActive() && gameHandler.getMaxRounds() > 0) {
                    dieHandler.dieArrayList.get(5).setActive();
                    diceButtonImageHandler(5,dieHandler.dieArrayList.get(5).getValue());
                } else {
                    dieHandler.dieArrayList.get(5).setInactive();
                    diceButtonImageHandler(5,dieHandler.dieArrayList.get(5).getValue());
                }
            }
        });

    }

    /**
     * Method: refreshViewOnReload
     * Whenever activity is destroyed, this method is called to make sure that the application
     * restores all data that needs to be restored.
     */
    private void refreshViewOnReload() {
        viewRefreshed = true;
        diceButtonOne = findViewById(R.id.diceButtonOne);
        diceButtonTwo = findViewById(R.id.diceButtonTwo);
        diceButtonThree = findViewById(R.id.diceButtonThree);
        diceButtonFour = findViewById(R.id.diceButtonFour);
        diceButtonFive = findViewById(R.id.diceButtonFive);
        diceButtonSix = findViewById(R.id.diceButtonSix);

        totalScore = findViewById(R.id.totalScoreCount);
        totalScore.setText(String.valueOf(gameHandler.totalScore));

        String[] spinnerValuesToRender = {"LOW", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        List<String> spinnerValuesToRenderList = new ArrayList<>(Arrays.asList(spinnerValuesToRender));
        for(int i = 0; i < gameHandler.spinnerItemsToBeDeleted.size(); i++) {
            spinnerValuesToRenderList.remove(gameHandler.spinnerItemsToBeDeleted.get(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,spinnerValuesToRenderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.scoreValueSpinner);
        spinner.setAdapter(adapter);


        for (int i = 0; i < dieHandler.dieArrayList.size(); i++) {
            if(i == 0) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 1) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 2) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 3) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 4) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            } else if (i == 5) {
                diceButtonImageHandler(i, dieHandler.dieArrayList.get(i).getValue());
            }
        }
    }

    /**
     * Method: resetDice
     * Method to reset dice to have value one.
     */
    private void resetDice() {
        for (int i = 0;i < dieHandler.dieArrayList.size(); i++) {
            dieHandler.dieArrayList.get(i).setInactive();
            diceButtonImageHandler(i,1);
        }
    }

    /**
     * Method: onSaveInstanceState
     * Handles restoration of data in case of activity destruction.
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(ARRAY_TAG, dieHandler);
        savedInstanceState.putParcelable(GAME_TAG, gameHandler);
    }
}

