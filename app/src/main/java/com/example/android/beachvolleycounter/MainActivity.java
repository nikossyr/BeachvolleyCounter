package com.example.android.beachvolleycounter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int setTeamA = 0;
    int setTeamB = 0;
    int setWinningScore = 11;
    int decisiveSetWinningScore = 5;
    TextView setView;
    int timeOutA = 0;
    int timeOutB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView = (TextView) findViewById(R.id.set_number);
    }

    /**
     * Activated upon tapping the Point button for Team A
     */
    public void point_teamA(View view) {
        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, "The game is over. Please hit RESET to start a new game", Toast.LENGTH_LONG).show();
        } else {
            scoreTeamA = scoreTeamA + 1;
            displayScoreTeamA(scoreTeamA);
            checkSetStatus(scoreTeamA, scoreTeamB);
            setNumbering(setTeamA, setTeamB);
        }
    }


    /**
     * Activated upon tapping the Point button for Team B
     */
    public void point_teamB(View view) {
        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, "The game is over. Please hit RESET to start a new game", Toast.LENGTH_LONG).show();
        } else {
            scoreTeamB = scoreTeamB + 1;
            displayScoreTeamB(scoreTeamB);
            checkSetStatus(scoreTeamA, scoreTeamB);
            setNumbering(setTeamA, setTeamB);
        }
    }


    /**
     * Activated upon tapping the TIMEOUT button for Team A
     */
    public void timeoutA(View view) {
        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, "The game is over. Please hit RESET to start a new game", Toast.LENGTH_LONG).show();
        } else if (timeOutA == 0) {
            new CountDownTimer(10000, 1000) {
                public void onTick(long millisUntilFinished) {
                    String timeOutText = ("Time Out\n" + millisUntilFinished / 1000 + " seconds remaining");
                    setView.setText(timeOutText);
                }
                public void onFinish() {
                    setNumbering(setTeamA, setTeamB);
                    timeOutA += 1;
                }
            }.start();
        } else if (timeOutA > 0) {
            Toast.makeText(this, "No more TimeOuts for Team A for this Set", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Activated upon tapping the TIMEOUT button for Team A
     */
    public void timeoutB(View view) {
        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, "The game is over. Please hit RESET to start a new game", Toast.LENGTH_LONG).show();
        } else if (timeOutB == 0) {
            new CountDownTimer(10000, 1000) {
                public void onTick(long millisUntilFinished) {
                    String timeOutText = ("Time Out\n" + millisUntilFinished / 1000 + " seconds remaining");
                    setView.setText(timeOutText);
                }
                public void onFinish() {
                    setNumbering(setTeamA, setTeamB);
                    timeOutB += 1;
                }
            }.start();
        } else if (timeOutB > 0) {
            Toast.makeText(this, "No more TimeOuts for Team A for this Set", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Activated upon tapping the RESET button
     */
    public void resetScore(View view) {
        scoreTeamB = 0;
        scoreTeamA = 0;
        setTeamA = 0;
        setTeamB = 0;
        timeOutA = 0;
        timeOutB = 0;
        setNumbering(setTeamA,setTeamB);
        displayScoreTeamA(scoreTeamA);
        displayScoreTeamB(scoreTeamB);
        displaySetTeamA(setTeamA);
        displaySetTeamB(setTeamB);
        displayHistoryTeamA(10,10);
        displayHistoryTeamB(10,10);

    }

    /**
     * Checks the score to determine if a team has won the set
     */
    public void checkSetStatus(int scoreA, int scoreB) {
        if (setTeamA + setTeamB < 2) {
            if (scoreA == setWinningScore && scoreB < setWinningScore - 1) {
                setTeamA += 1;
                int setsAdded = setTeamA + setTeamB;
                Toast.makeText(this, "Set " + setsAdded + " is over", Toast.LENGTH_SHORT).show();
                timeOutA = 0;
                timeOutB = 0;
                displayHistoryTeamA(scoreTeamA, scoreTeamB);
                displaySetTeamA(setTeamA);
                scoreTeamA = 0;
                displayScoreTeamA(scoreTeamA);
                scoreTeamB = 0;
                displayScoreTeamB(scoreTeamB);
                setWinningScore = 11;
            } else if (scoreA < setWinningScore - 1 && scoreB == setWinningScore) {
                setTeamB += 1;
                int setsAdded = setTeamA + setTeamB;
                Toast.makeText(this, "Set " + setsAdded + " is over", Toast.LENGTH_SHORT).show();
                timeOutA = 0;
                timeOutB = 0;
                displayHistoryTeamB(scoreTeamA, scoreTeamB);
                displaySetTeamB(setTeamB);
                scoreTeamA = 0;
                displayScoreTeamA(scoreTeamA);
                scoreTeamB = 0;
                displayScoreTeamB(scoreTeamB);
                setWinningScore = 11;
            } else if (scoreA == setWinningScore - 1 && scoreB == setWinningScore - 1) {
                setWinningScore += 1;
            }
        }
        else if (setTeamA == 1 && setTeamB == 1) {
            if (scoreA == decisiveSetWinningScore && scoreB <decisiveSetWinningScore - 1) {
                setTeamA += 1;
                timeOutA = 0;
                timeOutB = 0;
                displayHistoryTeamA(scoreTeamA, scoreTeamB);
                displaySetTeamA(setTeamA);
                scoreTeamA = 0;
                displayScoreTeamA(scoreTeamA);
                scoreTeamB = 0;
                displayScoreTeamB(scoreTeamB);
                decisiveSetWinningScore = 5;
            } else if (scoreA < decisiveSetWinningScore - 1 && scoreB == decisiveSetWinningScore) {
                setTeamB += 1;
                timeOutA = 0;
                timeOutB = 0;
                displayHistoryTeamB(scoreTeamA, scoreTeamB);
                displaySetTeamB(setTeamB);
                scoreTeamA = 0;
                displayScoreTeamA(scoreTeamA);
                scoreTeamB = 0;
                displayScoreTeamB(scoreTeamB);
                decisiveSetWinningScore = 5;
            } else if (scoreA == decisiveSetWinningScore - 1 && scoreB == decisiveSetWinningScore - 1) {
                decisiveSetWinningScore += 1;
            }
        }
    }

    /**
     * Determines SET count
     **/
    public void setNumbering(int setA, int setB) {

        if (setA+setB == 0) {
            setView.setText(String.valueOf("Set 1"));
        }
        else if (setA+setB == 1) {
            setView.setText(String.valueOf("Set 2"));
        }
        else if (setA == 1 && setB == 1) {
            setView.setText(String.valueOf("Decisive Set 3"));
        }
        else if (setA+setB>=2){
            setView.setText(String.valueOf("Game Over"));
        }
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayScoreTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the SET count for Team A.
     */
    public void displaySetTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_sets);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Displays the given score for Team B.
     */
    public void displayScoreTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the SET count for Team B.
     */
    public void displaySetTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_sets);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Displays the SCORES that Team A won in the corresponding SETS
     */
    public void displayHistoryTeamA(int scoreA, int scoreB){
        TextView set1View = (TextView) findViewById(R.id.set1TeamA);
        TextView set2View = (TextView) findViewById(R.id.set2TeamA);
        TextView set3View = (TextView) findViewById(R.id.set3TeamA);
        TextView winningView = (TextView) findViewById(R.id.winningTeam);
        if (setTeamA == 1 && setTeamB == 0) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set1View.setText(scoreString);
        }
        else if(setTeamA == 1 && setTeamB == 1){
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
        }
        else if(setTeamA == 2 && setTeamB == 0){
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
            String teamAwins = "Team A wins!!";
            winningView.setText(teamAwins);
        }
        else if(setTeamA == 2 && setTeamB == 1){
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set3View.setText(scoreString);
            String teamAwins = "Team A wins!!";
            winningView.setText(teamAwins);
        }
        else if(scoreA == 10 && scoreB == 10){
            String clearTextView = "";
            set1View.setText(clearTextView);
            set2View.setText(clearTextView);
            set3View.setText(clearTextView);
            winningView.setText(clearTextView);
        }
    }
    /**
     * Displays the SCORES that Team B won in the corresponding SETS
     */
    public void displayHistoryTeamB(int scoreA, int scoreB){
        TextView set1View = (TextView) findViewById(R.id.set1TeamB);
        TextView set2View = (TextView) findViewById(R.id.set2TeamB);
        TextView set3View = (TextView) findViewById(R.id.set3TeamB);
        TextView winningView = (TextView) findViewById(R.id.winningTeam);
        if (setTeamB == 1 && setTeamA == 0) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set1View.setText(scoreString);
        }
        else if (setTeamB == 1 && setTeamA == 1) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
        }
        else if(setTeamB == 2 && setTeamA == 0){
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
            String teamBwins = "Team B wins!!";
            winningView.setText(teamBwins);
        }
        else if(setTeamB == 2 && setTeamA == 1){
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set3View.setText(scoreString);
            String teamBwins = "Team B wins!!";
            winningView.setText(teamBwins);
        }
        else if(scoreA == 10 && scoreB == 10){
            String clearTextView = "";
            set1View.setText(clearTextView);
            set2View.setText(clearTextView);
            set3View.setText(clearTextView);
            winningView.setText(clearTextView);
        }
    }
}
