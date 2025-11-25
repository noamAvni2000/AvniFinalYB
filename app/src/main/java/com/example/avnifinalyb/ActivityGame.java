package com.example.avnifinalyb;

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

    private void filter(String text) {
        ArrayList<MyItem> filteredList = new ArrayList<>();
        for (MyItem item : MyItemData.getItems()) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }

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

        recyclerViewSuggestions = findViewById(R.id.recyclerViewSuggestions);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewSuggestions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSuggestions.setAdapter(suggestionsAdapter);//Set layout manager

        ArrayList<MyItem> allCountries = new ArrayList<>(MyItemData.getItems()); // all countries
        ArrayList<MyItem> guessedCountries = new ArrayList<>(); // countries user guessed
        ArrayList<MyItem> suggestions = new ArrayList<>(); // filtered suggestions

        suggestionsAdapter = new MyAdapter(suggestions);
        // Start with an empty list
        adapter = new MyAdapter(guessedCountries);
        recyclerView.setAdapter(adapter);

        // Set click listener for suggestion items
        suggestionsAdapter.setOnItemClickListener(item -> {
            etGuess.setText(item.getCountry());
            etGuess.setSelection(item.getCountry().length()); // move cursor to end
        });

        etGuess.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim().toLowerCase();
                suggestions.clear(); // clear previous suggestions

                if (!input.isEmpty()) {
                    for (MyItem item : allCountries) {
                        if (item.getCountry().toLowerCase().startsWith(input)) {
                            suggestions.add(item);
                        }
                    }
                }

                suggestionsAdapter.notifyDataSetChanged(); // update suggestions RecyclerView
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });//shows only the countries that fit the users input into the edit text

        btnAiHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGame.this);
                builder.setTitle("Are you sure you want to use a hint?");
                builder.setMessage("It will hurt your statistics");

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
                String country = etGuess.getText().toString().trim();

                if (country.isEmpty()) {
                    Toast.makeText(ActivityGame.this, "Please enter a country", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Get the country info
                MyItem item = MyItemData.getCountryInfo(country);

                // Optional: check if the item is already in the list to avoid duplicates
                if (!guessedCountries.contains(item)) {
                    adapter.addItem(item); // Add only the guessed country
                    recyclerView.scrollToPosition(guessedCountries.size() - 1);
                }

                etGuess.setText("");
            }
        });
    }
}