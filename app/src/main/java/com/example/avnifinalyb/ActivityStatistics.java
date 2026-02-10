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
    int winAmount, guessesThisGame;
    double avgGuesses;

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

    }

    private void setStats(){

    }
}