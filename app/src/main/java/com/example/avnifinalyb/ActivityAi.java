package com.example.avnifinalyb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityAi extends AppCompatActivity {
    private static final String TAG = "GeminiDemo";
    public static final String EXTRA_COUNTRY = "com.example.avnifinalyb.EXTRA_COUNTRY";

    private EditText editPrompt;
    private ProgressBar progress;
    private TextView txtAnswer;

    private GenerativeModel gm;
    private final Executor executor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);
        Log.d(TAG, "onCreate()");

        connectUiElements();

        String apiKey = BuildConfig.GOOGLE_API_KEY;
        if (apiKey == null || apiKey.isEmpty()) {
            Log.w(TAG, "GOOGLE_API_KEY is empty – did you set it in local.properties?");
        } else {
            Log.d(TAG, "Loaded GOOGLE_API_KEY (length=" + apiKey.length() + ")");
        }
        gm = new GenerativeModel("gemini-2.5-flash", apiKey);

        // Check for incoming data to auto-generate a clue
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_COUNTRY)) {
            String country = intent.getStringExtra(EXTRA_COUNTRY);
            if (country != null && !country.isEmpty()) {
                String promptForClue = "Give me one interesting and not too obvious clue for the country: " + country;
                editPrompt.setText(promptForClue);
                sendToGemini();
            }
        }

    }

    private void connectUiElements(){
        editPrompt = findViewById(R.id.editPrompt);
        progress = findViewById(R.id.progress);
        txtAnswer = findViewById(R.id.txtAnswer);
        editPrompt.setVisibility(View.GONE);
    }

    private void checkIfThereIsKey(){

    }

    private void sendToGemini() {
        final String prompt = editPrompt.getText().toString().trim();
        if (prompt.isEmpty()) {
            editPrompt.setError("הקלידו שאלה");
            Log.w(TAG, "Empty prompt – showing EditText error");
            return;
        }
        final String shortPrompt = prompt.length() > 80 ? prompt.substring(0, 80) + "…" : prompt;
        Log.d(TAG, "sendToGemini() prompt=('" + shortPrompt + "') len=" + prompt.length());

        progress.setVisibility(View.VISIBLE);
        txtAnswer.setText("");

        Content content = new Content.Builder().addText(prompt).build();
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String text = result.getText();
                Log.d(TAG, "Response received; text? " + (text != null));
                final String uiText = (text != null && !text.isEmpty()) ? text : "אין טקסט בתשובה. נסו שוב.";
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.v(TAG, "Updating UI with response (length=" + uiText.length() + ")");
                        progress.setVisibility(View.GONE);
                        txtAnswer.setText(uiText);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Request failed: " + t.getMessage(), t);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        txtAnswer.setText("שגיאה: " + t.getMessage());
                    }
                });
            }
        }, executor);
    }
}
