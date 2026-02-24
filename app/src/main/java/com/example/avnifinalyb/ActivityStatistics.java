package com.example.avnifinalyb;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class ActivityStatistics extends AppCompatActivity {

    TextView tvTitle, tvWinAmount, tvAvgGuesses, tvGuessAmountNow, tvRecord, tvWinsAi, tvAiThisGame, tvDistance;
    TextView tvWinAmountResult, tvWinsAiResult, tvAiThisGameResult, tvAvgGuessesResult, tvGuessAmountNowResult, tvRecordResult;
    Button btnNewGame, btnSwitchUser;
    int winAmount, guessesThisGame, aiWinAmount, record;
    boolean aiUse;
    double avgGuesses;
    String usernameStr;
    String targetCountryName;

    private FusedLocationProviderClient fusedLocationClient;//הוא ה--API הראשי באנדרואיד לקבלת מיקום משתמש בצורה חכמה, חסכונית
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MyItem targetCountryItem;
    private MyItemData myItemData = new MyItemData();

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
        setupButtons();
        setupLocation();
    }

    private void setupLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);//הוא ה--API הראשי באנדרואיד לקבלת מיקום משתמש בצורה חכמה, חסכונית
        
        if (targetCountryItem != null) {
            // Check for location permission to calculate distance
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            } else {
                fetchLocationAndCalculateDistance();
            }
        }
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
        tvRecordResult=findViewById(R.id.tvRecordResult);
        tvDistance = findViewById(R.id.tvDistance);
        db= UsersDatabase.getInstance(this);
        userNamesDao = db.usernamesDao();
    }

    private void setupButtons() {
        btnNewGame.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityStatistics.this, ActivityGame.class);
            if (getIntent() != null && getIntent().hasExtra("USERNAME_KEY_ADMIN")) {
                intent.putExtra("USERNAME_KEY_ADMIN", usernameStr);
            } else {
                intent.putExtra("USERNAME_KEY", usernameStr);
            }
            startActivity(intent);
            finish();
        });

        btnSwitchUser.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityStatistics.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setStats(){
        if(getIntent()!=null) {
            guessesThisGame = getIntent().getIntExtra("guessAmount", -1);
            aiUse = getIntent().getBooleanExtra("aiUse", false);
            targetCountryName = getIntent().getStringExtra("targetCountry");
            if (targetCountryName != null) {
                targetCountryItem = myItemData.getCountryInfo(targetCountryName);
            }
        }

        else {
            guessesThisGame=-1;
            aiUse=false;
        }

        usernameStr = "";
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
                record = user.getRecord();
            }
        }

        tvWinAmountResult.setText(String.valueOf(winAmount));

        tvWinsAiResult.setText(String.valueOf(winAmount-aiWinAmount));

        tvAvgGuessesResult.setText(String.format("%.2f", avgGuesses));///makes the number to a string with 2 decimal places

        tvRecordResult.setText(String.valueOf(record));

    }

    private void fetchLocationAndCalculateDistance() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }// if no permission for location stop the function

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null && targetCountryItem != null) {
                    LatLng countryPos = new LatLng(targetCountryItem.getLatitude(), targetCountryItem.getLongitude());

                    // Calculate distance
                    float[] results = new float[1];
                    Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                            countryPos.latitude, countryPos.longitude, results);
                    float distanceInKm = results[0] / 1000;

                    tvDistance.setText(String.format("You are at a distance of: %.0f km from the country", distanceInKm));
                } else if (targetCountryItem != null) {
                    tvDistance.setText("Could not determine your location");
                }
            }
        });
    }

    private static class LatLng {
        double latitude;
        double longitude;
        LatLng(double lat, double lng) {
             this.latitude = lat;
             this.longitude = lng;
        }
    }

    //checks if the user gave permission to use location
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocationAndCalculateDistance();
            } else {
                tvDistance.setText("Location permission denied");
            }
        }
    }
}
