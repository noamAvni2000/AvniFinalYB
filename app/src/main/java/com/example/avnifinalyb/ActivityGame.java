package com.example.avnifinalyb;

//import static com.example.avnifinalyb.MyItemData.data;
import static com.example.avnifinalyb.MyItemData.getItems;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

        connectUiElements();//all the ui references

        // Master data (all countries)
        List<MyItem> fromData = getItems();
        final ArrayList<MyItem> allCountries = new ArrayList<>(fromData);

        // Lists we'll use
        final ArrayList<MyItem> guessedCountries = new ArrayList<>(); // items user confirmed
        final ArrayList<MyItem> suggestions = new ArrayList<>();     // current suggested items while typing

        // --- initialize suggestions adapter FIRST then set it on the RecyclerView ---
        suggestionsAdapter = new MyAdapter(suggestions);
        recyclerViewSuggestions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSuggestions.setAdapter(suggestionsAdapter);
        recyclerViewSuggestions.setVisibility(View.GONE); // hide initially

        // --- initialize guessed adapter and set on main RecyclerView ---
        adapter = new MyAdapter(guessedCountries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // --- suggestion item click: fill the EditText (no lambdas) ---
        suggestionsAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyItem item) {
                etGuess.setText(item.getCountry());
                etGuess.setSelection(item.getCountry().length());
                // hide suggestions after selecting
                recyclerViewSuggestions.setVisibility(View.GONE);
            }
        });


        // --- TextWatcher: update suggestions as user types ---
        etGuess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* no-op */ }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();
                suggestions.clear();

                if (input.isEmpty()) {
                    // hide suggestions when input is empty
                    suggestionsAdapter.updateList(new ArrayList<MyItem>());
                    recyclerViewSuggestions.setVisibility(View.GONE);
                    return;
                }

                String lower = input.toLowerCase();
                for (MyItem mi : allCountries) {
                    if (mi.getCountry().toLowerCase().startsWith(lower)) { // startsWith requirement
                        suggestions.add(mi);
                    }
                }

                // update suggestions adapter (replace list)
                suggestionsAdapter.updateList(new ArrayList<MyItem>(suggestions));

                // show or hide suggestions view
                if (suggestions.isEmpty()) {
                    recyclerViewSuggestions.setVisibility(View.GONE);
                } else {
                    recyclerViewSuggestions.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { /* no-op */ }
        });

        // --- AI help button (kept as you had it) ---
        btnAiHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGame.this);
                builder.setTitle("Are you sure you want to use a hint?");
                builder.setMessage("It will hurt your statistics");

                builder.setPositiveButton("Yes", (dialog, which) -> {
                    // AI help code
                });

                builder.setNegativeButton("No", (dialog, which) -> {
                    // do nothing
                });

                builder.show();
            }
        });

        // --- Enter button: confirm the typed country and add to guessed list ---
        btnEnterCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String countryText = etGuess.getText().toString().trim();
                if (countryText.isEmpty()) {
                    Toast.makeText(ActivityGame.this, "Please enter a country", Toast.LENGTH_SHORT).show();
                    return;
                }

                // lookup (case-insensitive) — MyItemData keys are exact names, so try exact match first,
                // then try case-insensitive search through allCountries.
                MyItem found = MyItemData.getCountryInfo(countryText);
                // getCountryInfo returns Unknown fallback if not found in map; adjust check:
                if (found.getContinent().equals("Unknown") && found.getReligion().equals("Unknown") &&
                        found.getPopulation().equals("Unknown")) {
                    // try case-insensitive match against allCountries to accept "israel" or "Israel"
                    MyItem ci = null;
                    for (MyItem mi : allCountries) {
                        if (mi.getCountry().equalsIgnoreCase(countryText)) {
                            ci = mi;
                            break;
                        }
                    }
                    if (ci == null) {
                        Toast.makeText(ActivityGame.this, "Country not found", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        found = ci;
                    }
                }

                // avoid duplicates by comparing country names (case-insensitive)
                boolean already = false;
                for (MyItem mi : guessedCountries) {
                    if (mi.getCountry().equalsIgnoreCase(found.getCountry())) {
                        already = true;
                        break;
                    }
                }
                if (already) {
                    Toast.makeText(ActivityGame.this, "Already guessed this country", Toast.LENGTH_SHORT).show();
                    etGuess.setText("");
                    recyclerViewSuggestions.setVisibility(View.GONE);
                    return;
                }

                // add to guessed list and update adapter
                adapter.addItem(found);
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);

                // clear input and hide suggestions
                etGuess.setText("");
                suggestionsAdapter.updateList(new ArrayList<MyItem>());
                recyclerViewSuggestions.setVisibility(View.GONE);
            }
        });
    }


    private void fillTheGuess(){
                   // String Partial = etGuess.getText();
                   // String [] countries=new String[getItems().size()]; //a list of all the countries
               String[] COUNTRIES = new String[] {
                    "Belgium", "France", "Italy", "Germany", "Spain"
            };

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityGame.this,
                            android.R.layout.simple_dropdown_item_1line, COUNTRIES);
            AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.etGuess);
            textView.setAdapter(adapter);


    }

    private void connectUiElements(){
        etGuess = findViewById(R.id.etGuess);
        btnEnterCountry = findViewById(R.id.btnEnterCountry);
        btnAiHelp = findViewById(R.id.btnAiHelp);
        tvGame = findViewById(R.id.tvGame);
        recyclerViewSuggestions = findViewById(R.id.recyclerViewSuggestions);
        recyclerView = findViewById(R.id.recyclerView);
    }

    /*public void onTextChanged(CharSequence s, int start, int before, int count){
        String input = s.toString().trim();
        suggestions.clear();

        if (input.isEmpty()) {
            // hide suggestions when input is empty
            suggestionsAdapter.updateList(new ArrayList<MyItem>());
            recyclerViewSuggestions.setVisibility(View.GONE);
            return;
        }

        String lower = input.toLowerCase();
        for (MyItem mi : allCountries) {
            if (mi.getCountry().toLowerCase().startsWith(lower)) { // startsWith requirement
                suggestions.add(mi);
            }
        }

        // update suggestions adapter (replace list)
        suggestionsAdapter.updateList(new ArrayList<MyItem>(suggestions));

        // show or hide suggestions view
        if (suggestions.isEmpty()) {
            recyclerViewSuggestions.setVisibility(View.GONE);
        } else {
            recyclerViewSuggestions.setVisibility(View.VISIBLE);
        }
    }

    @Override*/
    //public void afterTextChanged(Editable s) { /* no-op */ }
    //}
}
