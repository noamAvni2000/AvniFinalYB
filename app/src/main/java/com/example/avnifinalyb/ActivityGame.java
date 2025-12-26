package com.example.avnifinalyb;

import static com.example.avnifinalyb.MyItemData.GetRandomCountry;
import static com.example.avnifinalyb.MyItemData.getItems;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

    private MyItem randomCountry;

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
        adapter.setTargetCountry(randomCountry);//sets the random country chosen as the adapters target country

        Log.d("guessed country", "correct country: "+randomCountry.getCountry());


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
        btnAiHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ActivityGame.this)
                        .setTitle("Are you sure you want to use a hint?")
                        .setMessage("It will hurt your statistics")
                        .setPositiveButton("Yes", (dialog, which) -> { /* TODO AI help */ })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    /** Handles the Enter button → adds selected country to guessed list. */
    private void setupEnterCountryButton() {
        btnEnterCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                    //if the user guessed correctly switches to the statistics screen(not final version of this "if" need to add more logic)
                    if(isLastGuessCorrect()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGame.this);
                        builder.setTitle("You won!");
                        builder.setMessage("What would you like to do now?");

                        // כפתור לאתחול המשחק
                        builder.setPositiveButton("Reset game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartGame();
                            }
                        });

                        // כפתור לעבור למסך אחר
                        builder.setNegativeButton("View statistics", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ActivityGame.this, ActivityStatistics.class);
                                startActivity(intent);
                                finish(); // אם רוצים לסגור את המסך הנוכחי
                            }
                        });

                        builder.show();
                    }
                }
        });
        Log.d("some crash", "did we reach the end of the btnClickListener?");
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

    ///assigns a random country that will be the country needed to be guessed
    private void chooseRandomCountry(){
        randomCountry=GetRandomCountry();
    }

    ///compares the last guessed country with the random country return true if match return false if
    ///  doesnt match/there is no random country/no country was guessed yet
    private boolean isLastGuessCorrect() {

        // אם אין ניחושים או שאין מדינה רנדומלית
        if (guessedCountries.isEmpty() || randomCountry == null) {
            return false;
        }

        // הניחוש האחרון
        MyItem lastGuessed = guessedCountries.get(guessedCountries.size() - 1);

        // השוואה לפי שם המדינה (לא רגיש לאותיות)
        return lastGuessed.getCountry().equalsIgnoreCase(randomCountry.getCountry());
    }

    /// compares the poplation of the target country and guessed country and returns input based
    /// on the result of the comparison
    private String comparePopulation(MyItem guess, MyItem target) {
        int guessPop = parsePopulation(guess.getPopulation());
        int targetPop = parsePopulation(target.getPopulation());

        if (guessPop > targetPop) return "More";
        if (guessPop < targetPop) return "Less";
        return "Same";
    }

    ///makes the short of million(M) to number
    private int parsePopulation(String pop) {
        if (pop.endsWith("M")) {
            return Integer.parseInt(pop.replace("M", "")) * 1_000_000;
        }
        return 0;
    }

    /// compares the continents of the target country and the guessed country returns input based
    /// on the result of the comparison
    private String compareContinent(MyItem guess, MyItem target) {
        if (guess.getContinent().equalsIgnoreCase(target.getContinent())) {
            return "Same";
        }
        return "Different";
    }

    /// compares if the target country and the guessed country are landlocked the same way and
    /// returns input based on the result of the comparison
    private String compareLandLocked(MyItem guess, MyItem target) {
        if (guess.getLandLocked().equalsIgnoreCase(target.getLandLocked())) {
            return "Same";
        }
        return "Different";
    }

    /// resets the game if the user decides to play again
    private void restartGame() {
        guessedCountries.clear();
        adapter.notifyDataSetChanged();

        randomCountry = MyItemData.GetRandomCountry();
        adapter.setTargetCountry(randomCountry);

        etGuess.setText("");
    }


}
