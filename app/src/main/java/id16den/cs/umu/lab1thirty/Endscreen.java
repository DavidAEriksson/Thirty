package id16den.cs.umu.lab1thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author David Eriksson, ID16
 * Class responsible for showing the individual as well as the total scores at the end of the game.
 */
public class Endscreen extends AppCompatActivity {

    private TextView total, lowScore, fourScore, fiveScore, sixScore, sevenScore, eightScore, nineScore, tenScore, elevenScore, twelveScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //Enable full-screen.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_endscreen);

        Intent intent = getIntent();
        HashMap<Integer, String> scoresMap = (HashMap<Integer, String>) intent.getSerializableExtra("scoresMap");
        int totalScore = intent.getIntExtra("totalEndScore",0);

        lowScore = findViewById(R.id.lowScore);
        fourScore = findViewById(R.id.fourScore);
        fiveScore = findViewById(R.id.fiveScore);
        sixScore = findViewById(R.id.sixScore);
        sevenScore = findViewById(R.id.sevenScore);
        eightScore = findViewById(R.id.eightScore);
        nineScore = findViewById(R.id.nineScore);
        tenScore = findViewById(R.id.tenScore);
        elevenScore = findViewById(R.id.elevenScore);
        twelveScore = findViewById(R.id.twelveScore);
        total = findViewById(R.id.totalScore);
        total.setText(String.valueOf(totalScore));

        for(Map.Entry<Integer, String> entry : scoresMap.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();

            if(key == 1) {
                lowScore.setText(value);
            } else if(key == 4) {
                fourScore.setText(value);
            } else if(key == 5) {
                fiveScore.setText(value);
            } else if(key == 6) {
                sixScore.setText(value);
            } else if(key == 7) {
                sevenScore.setText(value);
            } else if(key == 8) {
                eightScore.setText(value);
            } else if(key == 9) {
                nineScore.setText(value);
            } else if(key == 10) {
                tenScore.setText(value);
            } else if(key == 11) {
                elevenScore.setText(value);
            } else if(key == 12) {
                twelveScore.setText(value);
            }
        }
    }
}
