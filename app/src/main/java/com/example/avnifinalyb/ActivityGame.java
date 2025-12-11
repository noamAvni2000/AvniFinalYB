package com.example.avnifinalyb;

import static com.example.avnifinalyb.MyItemData.getItems;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityGame extends AppCompatActivity {

    EditText etGuess;
    Button btnEnterCountry, btnAiHelp;
    TextView tvGame;
    RecyclerView recyclerView, recyclerViewSuggestions;
    MyAdapter adapter, suggestionsAdapter;

    // Data lists
    ArrayList<MyItem> allCountries;
    ArrayList<MyItem> guessedCountries;
    ArrayList<MyItem> suggestions;

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
    }

    // --------------------------------------------------------------------
    // FUNCTIONS
    // --------------------------------------------------------------------


    /** Connects UI elements (findViewById for all views). */
    private void connectUiElements() {
        etGuess = findViewById(R.id.etGuess);
        btnEnterCountry = findViewById(R.id.btnEnterCountry);
        btnAiHelp = findViewById(R.id.btnAiHelp);
        tvGame = findViewById(R.id.tvGame);
        recyclerViewSuggestions = findViewById(R.id.recyclerViewSuggestions);
        recyclerView = findViewById(R.id.recyclerView);
    }

    /** Loads the full dataset and initializes working lists. */
    private void loadDataLists() {
        List<MyItem> fromData = getItems();
        allCountries = new ArrayList<>(fromData);
        guessedCountries = new ArrayList<>();
        suggestions = new ArrayList<>();
    }

    /** Sets up the RecyclerView that shows typing suggestions. */
    private void setupSuggestionAdapter() {
        suggestionsAdapter = new MyAdapter(suggestions);
        recyclerViewSuggestions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSuggestions.setAdapter(suggestionsAdapter);
        recyclerViewSuggestions.setVisibility(View.GONE);
    }

    /** Sets up the RecyclerView that shows guessed countries. */
    private void setupGuessedAdapter() {
        adapter = new MyAdapter(guessedCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    /** Allows tapping a suggestion to fill the EditText. */
    private void setupSuggestionClickListener() {
        suggestionsAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyItem item) {
                etGuess.setText(item.getCountry());
                etGuess.setSelection(item.getCountry().length());
                recyclerViewSuggestions.setVisibility(View.GONE);
            }
        });
    }

    /** Updates the suggestion list live while the user types. */
    private void setupTextWatcher() {
        etGuess.addTextChangedListener(new TextWatcher() {

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSuggestions(s.toString().trim());
            }

            @Override public void afterTextChanged(Editable s) {}
        });
    }

    /** Updates suggestions shown below the EditText. */
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

        if (suggestions.isEmpty()) {
            recyclerViewSuggestions.setVisibility(View.GONE);
        } else {
            recyclerViewSuggestions.setVisibility(View.VISIBLE);
        }
    }

    /** Handles the AI Hint button popup. */
    private void setupAiHelpButton() {
        btnAiHelp.setOnClickListener(v -> {
            new AlertDialog.Builder(ActivityGame.this)
                    .setTitle("Are you sure you want to use a hint?")
                    .setMessage("It will hurt your statistics")
                    .setPositiveButton("Yes", (dialog, which) -> { /* TODO AI help */ })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    /** Handles the Enter button → adds selected country to guessed list. */
    private void setupEnterCountryButton() {
        btnEnterCountry.setOnClickListener(v -> {
            String countryText = etGuess.getText().toString().trim();

            if (countryText.isEmpty()) {
                Toast.makeText(this, "Please enter a country", Toast.LENGTH_SHORT).show();
                return;
            }

            MyItem found = findCountry(countryText);
            if (found == null) {
                Toast.makeText(this, "Country not found", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isAlreadyGuessed(found)) {
                Toast.makeText(this, "Already guessed", Toast.LENGTH_SHORT).show();
                etGuess.setText("");
                recyclerViewSuggestions.setVisibility(View.GONE);
                return;
            }

            adapter.addItem(found);
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);

            etGuess.setText("");
            suggestionsAdapter.updateList(new ArrayList<>());
            recyclerViewSuggestions.setVisibility(View.GONE);
        });
    }

    /** Finds matching country (case-insensitive). Returns null if not found. */
    private MyItem findCountry(String name) {
        MyItem found = MyItemData.getCountryInfo(name);

        boolean isUnknown =
                found.getContinent().equals("Unknown") &&
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

    /** Checks if the user already guessed this country. */
    private boolean isAlreadyGuessed(MyItem item) {
        for (MyItem mi : guessedCountries) {
            if (mi.getCountry().equalsIgnoreCase(item.getCountry())) {
                return true;
            }
        }
        return false;
    }
}
