package com.example.avnifinalyb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityStatistics extends AppCompatActivity {

    TextView tvTitle, tvWinAmount, tvAvgGuesses, tvGuessAmountNow, tvRecord, tvWinsAi, tvAiThisGame;
    TextView tvWinAmountResult, tvWinsAiResult, tvAiThisGameResult, tvAvgGuessesResult, tvGuessAmountNowResult;
    Button btnNewGame, btnSwitchUser;
    int winAmount, guessesThisGame, aiWinAmount;
    boolean aiUse;
    double avgGuesses;

    private UsernamesDao userNamesDao;

    UsersDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        connectUiElements();
        setStats();
    }

    private void connectUiElements(){
        tvAvgGuesses=findViewById(R.id.tvAvgGuesses);
        tvTitle=findViewById(R.id.tvTitle);
        tvWinAmount=findViewById(R.id.tvWinAmount);
        btnNewGame=findViewById(R.id.btnNewGame);
        tvGuessAmountNow=findViewById(R.id.tvGuessAmountNow);
        tvRecord=findViewById(R.id.tvRecord);
        tvWinsAi=findViewById(R.id.tvWinsAi);
        tvAiThisGame=findViewById(R.id.tvAiThisGame);
        btnSwitchUser=findViewById(R.id.btnSwitchUser);
        tvWinAmountResult=findViewById(R.id.tvWinAmountResult);
        tvWinsAiResult=findViewById(R.id.tvWinsAiResult);
        tvAiThisGameResult=findViewById(R.id.tvAiThisGameResult);
        tvAvgGuessesResult=findViewById(R.id.tvAvgGuessesResult);
        tvGuessAmountNowResult=findViewById(R.id.tvGuessAmountNowResult);
        db= UsersDatabase.getInstance(this);
        userNamesDao = db.usernamesDao();
    }

    private void setStats(){
        if(getIntent()!=null) {
            guessesThisGame = getIntent().getIntExtra("guessAmount", -1);
            aiUse = getIntent().getBooleanExtra("aiUse", false);
        }

        else {
            guessesThisGame=-1;
            aiUse=false;
        }

        String usernameStr = "";
        if (getIntent() != null && getIntent().hasExtra("USERNAME_KEY")) {
            usernameStr = getIntent().getStringExtra("USERNAME_KEY");
        }
        else if(getIntent() != null && getIntent().hasExtra("USERNAME_KEY_ADMIN")) {
            usernameStr = getIntent().getStringExtra("USERNAME_KEY_ADMIN");
        }
        tvGuessAmountNowResult.setText(String.valueOf(guessesThisGame));
        if(aiUse){
            tvAiThisGameResult.setText("Yes");
        }
        else{
            tvAiThisGameResult.setText("No");
        }

        if(usernameStr !=null && !usernameStr.isEmpty()) {
            Usernames user = userNamesDao.getUserByUsername(usernameStr);
            if (user != null) {
                winAmount = user.getWinAmount();
                aiWinAmount = user.getAiWinAmount();
                avgGuesses = user.getAvgGuessAmount();
            }
        }

        tvWinAmountResult.setText(String.valueOf(winAmount));

        tvWinsAiResult.setText(String.valueOf(winAmount-aiWinAmount));

        tvAvgGuessesResult.setText(String.valueOf(avgGuesses));

    }
}
