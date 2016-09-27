package com.androidworld.mvnshrikanth.soccerscore;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int passMadeTeamA = 0;
    int passMadeTeamB = 0;
    int foulsTeamA = 0;
    int foulsTeamB = 0;
    CountDownTimer countDownTimer;
    Boolean matchRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTimer();
    }

    public void passA(View view) {
        passMadeTeamA++;
        showCommentry("Team A", "Pass");
    }

    public void passB(View view) {
        passMadeTeamB++;
        showCommentry("Team B", "Pass");
    }

    public void foulA(View view) {
        foulsTeamA++;
        showCommentry("Team A", "Foul");
    }

    public void foulB(View view) {
        foulsTeamB++;
        showCommentry("Team B", "Foul");
    }

    public void goalA(View view) {
        scoreTeamA++;
        showCommentry("Team A", "Goal");
        displayScoreA();
    }

    public void goalB(View view) {
        scoreTeamB++;
        showCommentry("Team B", "Goal");
        displayScoreB();
    }

    public void resetScores(View view) {
        resetApp();
    }

    private void displayScoreA() {
        TextView textViewA = (TextView) findViewById(R.id.team_a_score);
        textViewA.setText(String.valueOf(scoreTeamA));

    }

    private void displayScoreB() {
        TextView textViewB = (TextView) findViewById(R.id.team_b_score);
        textViewB.setText(String.valueOf(scoreTeamB));
    }

    private void showCommentry(String teamName, String action) {
        String message = "";

        if (action == "Pass") {
            message = "What a pass by " + teamName;
        } else if (action == "Foul") {
            message = "A very bad tackle by " + teamName + " on the opposition, ref shows him a card.";
        } else if (action == "Goal") {
            message = "What a goal by " + teamName + " goalkeeper had no chance.....";
        } else if (action == "Match Finished") {

            if (scoreTeamB > scoreTeamA) {
                message = "Team B wins the game by " + (scoreTeamB - scoreTeamA) + " goals.";
            } else if (scoreTeamA > scoreTeamB) {
                message = "Team A wins the game by " + (scoreTeamA - scoreTeamB) + " goals.";
            } else {
                message = "Its a stalemate... " + scoreTeamA + " - " + scoreTeamB + " draw.";
            }
            String summary = "\nPass Made  \nTeam A: " + passMadeTeamA +
                    "   Team B: " + passMadeTeamB + "\nFouls on \nTeam A: " + foulsTeamB + "   Team B: " + foulsTeamA;
            TextView textViewSmmry = (TextView) findViewById(R.id.text_summary);
            textViewSmmry.setText(summary);

        }

        TextView textView = (TextView) findViewById(R.id.text_comntry);
        textView.setText(message);

    }

    private void startTimer() {


        final TextView mTextField = (TextView) findViewById(R.id.text_timer_count_down);
        final Button buttonPsTmA = (Button) findViewById(R.id.btn_pass_teamA);
        final Button buttonFlTmA = (Button) findViewById(R.id.btn_foul_teamA);
        final Button buttonGlTmA = (Button) findViewById(R.id.btn_goal_teamA);

        final Button buttonPsTmB = (Button) findViewById(R.id.btn_pass_teamB);
        final Button buttonFlTmB = (Button) findViewById(R.id.btn_foul_teamB);
        final Button buttonGlTmB = (Button) findViewById(R.id.btn_goal_teamB);
        final Button buttonRst = (Button) findViewById(R.id.reset_button);
        final TextView textViewCmntry = (TextView) findViewById(R.id.text_comntry);

        if (buttonRst.getText().equals("START MATCH")) {
            textViewCmntry.setText(R.string.text_cmntry);
            buttonPsTmA.setEnabled(true);
            buttonFlTmA.setEnabled(true);
            buttonGlTmA.setEnabled(true);

            buttonPsTmB.setEnabled(true);
            buttonFlTmB.setEnabled(true);
            buttonGlTmB.setEnabled(true);
            buttonRst.setText("RESET MATCH");
        }

        if (buttonRst.getText().equals("RESET MATCH") && matchRunning) {
            textViewCmntry.setText(R.string.text_cmntry);
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(15000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                matchRunning = true;
                mTextField.setText(" " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                mTextField.setText("Full Time!!!");
                matchRunning = false;

                buttonPsTmA.setEnabled(false);
                buttonFlTmA.setEnabled(false);
                buttonGlTmA.setEnabled(false);

                buttonPsTmB.setEnabled(false);
                buttonFlTmB.setEnabled(false);
                buttonGlTmB.setEnabled(false);
                showCommentry("None", "Match Finished");
                buttonRst.setText("START MATCH");
            }
        }.start();
    }

    private void resetApp() {
        scoreTeamA = 0;
        scoreTeamB = 0;
        passMadeTeamA = 0;
        passMadeTeamB = 0;
        foulsTeamA = 0;
        foulsTeamB = 0;
        displayScoreA();
        displayScoreB();
        startTimer();
    }
}
