package com.example.android.beachvolleycounter;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;


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
    int counterTimeOutA = 0;
    int counterTimeOutB = 0;
    CountDownTimer timer;
    Button pointAbtn;
    Button pointBbtn;
    Button timeoutAbtn;
    Button timeoutBbtn;
    DecimalFormat twoDecimalPrecision = new DecimalFormat(".##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView = (TextView) findViewById(R.id.set_number);
        pointAbtn = (Button) findViewById(R.id.pointBtnA);
        pointBbtn = (Button) findViewById(R.id.pointBtnB);
        timeoutAbtn = (Button) findViewById(R.id.timeoutBtnA);
        timeoutBbtn = (Button) findViewById(R.id.timeoutBtnB);

    }

    /**
     * Activated upon tapping the Point button for Team A
     */
    public void point_teamA(View view) {
        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, getText(R.string.game_over_toast), Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, getText(R.string.game_over_toast), Toast.LENGTH_LONG).show();
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
        counterTimeOutA += 1;
        timer = new CountDownTimer(10000, 1) {
            public void onTick(long millisUntilFinished) {
                if (counterTimeOutA > 1) {
                    cancel();
                    setNumbering(setTeamA, setTeamB);
                    counterTimeOutA = 0;
                    pointAbtn.setClickable(true);
                    pointBbtn.setClickable(true);
                    timeoutBbtn.setClickable(true);
                    pointAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                    pointBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                    timeoutAbtn.setText(getString(R.string.timeout));
                    timeoutBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                } else {
                    String timeOutText = ("Time Out\n" + millisUntilFinished / 1000 + " seconds remaining");
                    setView.setText(timeOutText);
                }
            }

            public void onFinish() {
                setNumbering(setTeamA, setTeamB);
                timeOutA += 2;
                pointAbtn.setClickable(true);
                pointBbtn.setClickable(true);
                timeoutBbtn.setClickable(true);
                pointAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                pointBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                timeoutAbtn.setText(getString(R.string.timeout));
                timeoutBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
            }
        };

        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, getText(R.string.game_over_toast), Toast.LENGTH_LONG).show();
        } else if (timeOutA > 0) {
            Toast.makeText(this, getText(R.string.timeout_limit), Toast.LENGTH_LONG).show();
        } else if (timeOutA == 0 && counterTimeOutA == 1) {
            pointAbtn.setClickable(false);
            pointBbtn.setClickable(false);
            timeoutBbtn.setClickable(false);
            pointAbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            pointBbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            timeoutAbtn.setText(getString(R.string.cancel));
            timeoutBbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            timer.start();


        }
    }

    /**
     * Activated upon tapping the TIMEOUT button for Team A
     */
    public void timeoutB(View view) {
        counterTimeOutB += 1;
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (counterTimeOutB > 1) {
                    cancel();
                    setNumbering(setTeamA, setTeamB);
                    counterTimeOutB = 0;
                    pointAbtn.setClickable(true);
                    pointBbtn.setClickable(true);
                    timeoutAbtn.setClickable(true);
                    pointAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                    pointBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                    timeoutAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                    timeoutBbtn.setText(getString(R.string.timeout));
                } else {
                    String timeOutText = ("Time Out\n" + millisUntilFinished / 1000 + " seconds remaining");
                    setView.setText(timeOutText);
                }
            }

            public void onFinish() {
                setNumbering(setTeamA, setTeamB);
                timeOutB += 2;
                pointAbtn.setClickable(true);
                pointBbtn.setClickable(true);
                timeoutAbtn.setClickable(true);
                pointAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                pointBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                timeoutAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
                timeoutBbtn.setText(getString(R.string.timeout));
            }
        };

        if (setTeamA == 2 || setTeamB == 2 || setTeamA + setTeamB == 3) {
            Toast.makeText(this, getText(R.string.game_over_toast), Toast.LENGTH_LONG).show();
        } else if (timeOutB > 0) {
            Toast.makeText(this, getText(R.string.timeout_limit), Toast.LENGTH_LONG).show();
        } else if (timeOutB == 0 && counterTimeOutB == 1) {
            pointAbtn.setClickable(false);
            pointBbtn.setClickable(false);
            timeoutAbtn.setClickable(false);
            pointAbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            pointBbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            timeoutAbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            timeoutBbtn.setText(getString(R.string.cancel));
            timer.start();
        }
    }

    /**
     * Activated upon tapping the RESET button
     */
    public void resetScore(View view) {
        timer.cancel();
        scoreTeamB = 0;
        scoreTeamA = 0;
        setTeamA = 0;
        setTeamB = 0;
        timeOutA = 0;
        timeOutB = 0;
        counterTimeOutA = 0;
        counterTimeOutB = 0;
        pointAbtn.setClickable(true);
        pointBbtn.setClickable(true);
        timeoutAbtn.setClickable(true);
        timeoutBbtn.setClickable(true);
        pointAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
        pointBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
        timeoutAbtn.setBackgroundColor(Color.parseColor("#2196F3"));
        timeoutBbtn.setBackgroundColor(Color.parseColor("#2196F3"));
        timeoutAbtn.setText(getString(R.string.timeout));
        timeoutBbtn.setText(getString(R.string.timeout));
        setNumbering(setTeamA, setTeamB);
        displayScoreTeamA(scoreTeamA);
        displayScoreTeamB(scoreTeamB);
        displaySetTeamA(setTeamA);
        displaySetTeamB(setTeamB);
        displayHistoryTeamA(10, 10);
        displayHistoryTeamB(10, 10);
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
        } else if (setTeamA == 1 && setTeamB == 1) {
            if (scoreA == decisiveSetWinningScore && scoreB < decisiveSetWinningScore - 1) {
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

        if (setA + setB == 0) {
            setView.setText(String.valueOf(getString(R.string.set_1)));
            counterTimeOutA = 0;
            counterTimeOutB = 0;
        } else if (setA + setB == 1) {
            setView.setText(String.valueOf(getString(R.string.set_2)));
            counterTimeOutA = 0;
            counterTimeOutB = 0;
        } else if (setA == 1 && setB == 1) {
            setView.setText(String.valueOf(getString(R.string.set_3)));
            counterTimeOutA = 0;
            counterTimeOutB = 0;
        } else if (setA + setB >= 2) {
            setView.setText(String.valueOf(getText(R.string.game_over)));
            pointAbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            pointBbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            timeoutAbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
            timeoutBbtn.setBackgroundColor(Color.parseColor("#BDBDBD"));
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
    public void displayHistoryTeamA(int scoreA, int scoreB) {
        TextView set1View = (TextView) findViewById(R.id.set1TeamA);
        TextView set2View = (TextView) findViewById(R.id.set2TeamA);
        TextView set3View = (TextView) findViewById(R.id.set3TeamA);
        TextView winningView = (TextView) findViewById(R.id.winningTeam);
        if (setTeamA == 1 && setTeamB == 0) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set1View.setText(scoreString);
        } else if (setTeamA == 1 && setTeamB == 1) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
        } else if (setTeamA == 2 && setTeamB == 0) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
            winningView.setText(getText(R.string.win_team_a));
        } else if (setTeamA == 2 && setTeamB == 1) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set3View.setText(scoreString);
            winningView.setText(getText(R.string.win_team_a));
        } else if (scoreA == 10 && scoreB == 10) {
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
    public void displayHistoryTeamB(int scoreA, int scoreB) {
        TextView set1View = (TextView) findViewById(R.id.set1TeamB);
        TextView set2View = (TextView) findViewById(R.id.set2TeamB);
        TextView set3View = (TextView) findViewById(R.id.set3TeamB);
        TextView winningView = (TextView) findViewById(R.id.winningTeam);
        if (setTeamB == 1 && setTeamA == 0) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set1View.setText(scoreString);
        } else if (setTeamB == 1 && setTeamA == 1) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
        } else if (setTeamB == 2 && setTeamA == 0) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set2View.setText(scoreString);
            winningView.setText(getText(R.string.win_team_b));
        } else if (setTeamB == 2 && setTeamA == 1) {
            String scoreString = String.valueOf(scoreA) + "/" + String.valueOf(scoreB);
            set3View.setText(scoreString);
            winningView.setText(getText(R.string.win_team_b));
        } else if (scoreA == 10 && scoreB == 10) {
            String clearTextView = "";
            set1View.setText(clearTextView);
            set2View.setText(clearTextView);
            set3View.setText(clearTextView);
            winningView.setText(clearTextView);
        }
    }
}
