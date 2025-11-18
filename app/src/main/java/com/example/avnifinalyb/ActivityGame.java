package com.example.avnifinalyb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityGame extends AppCompatActivity {

    EditText etGuess;
    Button btnEnterCountry, btnAiHelp;
    TextView tvGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etGuess = findViewById(R.id.etGuess);
        btnEnterCountry = findViewById(R.id.btnEnterCountry);
        btnAiHelp = findViewById(R.id.btnAiHelp);
        tvGame = findViewById(R.id.tvGame);

        btnAiHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGame.this);
                builder.setTitle("Are you sure you want to use a hint?");
                builder.setMessage("i dont know what this is yet");

                builder.setPositiveButton("Yes", (dialog, which) -> {
                    //here i will place the ai help code
                });

                builder.setNegativeButton("No", (dialog, which) -> {
                    //do nothing
                });

                builder.show();
            }

        });

        btnEnterCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}