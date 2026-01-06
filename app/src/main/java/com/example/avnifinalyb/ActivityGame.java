package com.example.avnifinalyb;

import static com.example.avnifinalyb.MyItemData.GetRandomCountry;
import static com.example.avnifinalyb.MyItemData.getItems;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityGame extends AppCompatActivity {

    EditText etGuess;
    Button btnEnterCountry, btnAiHelp;
    TextView tvGame, tvWinMessage;
    RecyclerView recyclerView, recyclerViewSuggestions;
    MyAdapter adapter;
    SuggestionAdapter suggestionsAdapter;
    ImageView confetti;

    // Data lists
    ArrayList<MyItem> allCountries;
    ArrayList<MyItem> guessedCountries;
    ArrayList<MyItem> suggestions;

    private MyItem randomCountry;

    // Animation variables
    private Handler handler = new Handler();
    private Runnable animationRunnable;
    private float scale = 1.0f;

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

        connectUiElements();
        loadDataLists();
        setupSuggestionAdapter();
        setupGuessedAdapter();
        setupSuggestionClickListener();
        setupTextWatcher();
        setupAiHelpButton();
        setupEnterCountryButton();
        chooseRandomCountry();
        adapter.setTargetCountry(randomCountry);

        Log.d("guessed country", "correct country: " + randomCountry.getCountry());
    }

    private void connectUiElements() {
        etGuess = findViewById(R.id.etGuess);
        btnEnterCountry = findViewById(R.id.btnEnterCountry);
        btnAiHelp = findViewById(R.id.btnAiHelp);
        tvGame = findViewById(R.id.tvGame);
        recyclerViewSuggestions = findViewById(R.id.recyclerViewSuggestions);
        recyclerView = findViewById(R.id.recyclerView);
        confetti = findViewById(R.id.confetti);
        tvWinMessage = findViewById(R.id.tvWinMessage);
    }

    private void loadDataLists() {
        List<MyItem> fromData = getItems();
        allCountries = new ArrayList<>(fromData);
        guessedCountries = new ArrayList<>();
        suggestions = new ArrayList<>();
    }

    private void setupSuggestionAdapter() {
        suggestionsAdapter = new SuggestionAdapter(suggestions);
        recyclerViewSuggestions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSuggestions.setAdapter(suggestionsAdapter);
        recyclerViewSuggestions.setVisibility(View.GONE);
    }

    private void setupGuessedAdapter() {
        adapter = new MyAdapter(guessedCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupSuggestionClickListener() {
        suggestionsAdapter.setOnItemClickListener(item -> {
            etGuess.setText(item.getCountry());
            etGuess.setSelection(item.getCountry().length());
            recyclerViewSuggestions.setVisibility(View.GONE);
        });
    }

    private void setupTextWatcher() {
        etGuess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSuggestions(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void updateSuggestions(String input) {
        suggestions.clear();
        if (input.isEmpty()) {
            suggestionsAdapter.updateList(new ArrayList<>());
            recyclerViewSuggestions.setVisibility(View.GONE);
            return;
        }
        String lower = input.toLowerCase();
        for (MyItem item : allCountries) {
            if (item.getCountry().toLowerCase().startsWith(lower)) {
                suggestions.add(item);
            }
        }
        suggestionsAdapter.updateList(new ArrayList<>(suggestions));
        recyclerViewSuggestions.setVisibility(suggestions.isEmpty() ? View.GONE : View.VISIBLE);
    }

    private void setupAiHelpButton() {
        btnAiHelp.setOnClickListener(v -> new AlertDialog.Builder(ActivityGame.this)
                .setTitle("Are you sure you want to use a hint?")
                .setMessage("It will hurt your statistics")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("No", null)
                .show());
    }

    private void setupEnterCountryButton() {
        btnEnterCountry.setOnClickListener(view -> {
            String countryText = etGuess.getText().toString().trim();
            if (countryText.isEmpty()) {
                Toast.makeText(ActivityGame.this, "Please enter a country", Toast.LENGTH_SHORT).show();
                return;
            }
            MyItem found = findCountry(countryText);
            if (found == null) {
                Toast.makeText(ActivityGame.this, "Country not found", Toast.LENGTH_SHORT).show();
                return;
            }
            if (isAlreadyGuessed(found)) {
                Toast.makeText(ActivityGame.this, "Already guessed", Toast.LENGTH_SHORT).show();
                etGuess.setText("");
                recyclerViewSuggestions.setVisibility(View.GONE);
                return;
            }
            adapter.addItem(found);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            etGuess.setText("");
            suggestionsAdapter.updateList(new ArrayList<>());
            recyclerViewSuggestions.setVisibility(View.GONE);

            if (isLastGuessCorrect()) {
                playWinAnimation();
            }
        });
    }

    private void playWinAnimation() {
        Intent playIntent = new Intent(this, MyWinAudioService.class);
        playIntent.setAction(MyWinAudioService.ACTION_PLAY);
        startService(playIntent);

        // Use a built-in Android icon for a definitive test
        confetti.setImageResource(android.R.drawable.btn_star_big_on);
        confetti.bringToFront();
        confetti.setVisibility(View.VISIBLE);
        tvWinMessage.bringToFront();
        tvWinMessage.setVisibility(View.VISIBLE);
        scale = 1.0f;

        animationRunnable = new Runnable() {
            @Override
            public void run() {
                scale += 0.02f;
                confetti.setScaleX(scale);
                confetti.setScaleY(scale);
                tvWinMessage.setScaleX(scale);
                tvWinMessage.setScaleY(scale);
                handler.postDelayed(this, 30);
            }
        };
        handler.post(animationRunnable);

        new Handler().postDelayed(() -> {
            handler.removeCallbacks(animationRunnable);
            confetti.setVisibility(View.GONE);
            tvWinMessage.setVisibility(View.GONE);

            Intent stopIntent = new Intent(this, MyWinAudioService.class);
            stopIntent.setAction(MyWinAudioService.ACTION_STOP);
            startService(stopIntent);

            if (!isFinishing()) {
                showWinDialog();
            }
        }, 5000); // 5-second delay
    }

    private void showWinDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGame.this);
        builder.setTitle("You won!");
        builder.setMessage("What would you like to do now?");

        builder.setPositiveButton("Reset game", (dialog, which) -> restartGame());
        builder.setNegativeButton("View statistics", (dialog, which) -> {
            Intent intent = new Intent(ActivityGame.this, ActivityStatistics.class);
            startActivity(intent);
            finish();
        });
        builder.show();
    }

    private MyItem findCountry(String name) {
        MyItem found = MyItemData.getCountryInfo(name);
        boolean isUnknown = found.getContinent().equals("Unknown") &&
                found.getReligion().equals("Unknown") &&
                found.getPopulation().equals("Unknown");
        if (!isUnknown) return found;
        for (MyItem item : allCountries) {
            if (item.getCountry().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    private boolean isAlreadyGuessed(MyItem item) {
        for (MyItem mi : guessedCountries) {
            if (mi.getCountry().equalsIgnoreCase(item.getCountry())) {
                return true;
            }
        }
        return false;
    }

    private void chooseRandomCountry() {
        randomCountry = GetRandomCountry();
    }

    private boolean isLastGuessCorrect() {
        if (guessedCountries.isEmpty() || randomCountry == null) {
            return false;
        }
        MyItem lastGuessed = guessedCountries.get(guessedCountries.size() - 1);
        return lastGuessed.getCountry().equalsIgnoreCase(randomCountry.getCountry());
    }

    private void restartGame() {
        guessedCountries.clear();
        adapter.notifyDataSetChanged();
        randomCountry = MyItemData.GetRandomCountry();
        adapter.setTargetCountry(randomCountry);
        etGuess.setText("");
    }
}
