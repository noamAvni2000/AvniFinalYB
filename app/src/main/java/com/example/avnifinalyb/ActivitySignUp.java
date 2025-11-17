package com.example.avnifinalyb;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitySignUp extends AppCompatActivity {

    TextView tvEnter;
    EditText etUsername2, etPassword2, etConfirm;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etConfirm=findViewById(R.id.etConfirm);
        etUsername2=findViewById(R.id.etUsername2);
        etPassword2=findViewById(R.id.etPassword2);
        btnRegister=findViewById(R.id.btnRegister);
        tvEnter=findViewById(R.id.tvEnter);

        //etPassword2.setTransformationMethod(new PasswordTransformationMethod());
        //etConfirm.setTransformationMethod(new PasswordTransformationMethod());//hides the text entered into the edit text

        UsersDatabase db= UsersDatabase.getInstance(this);
        UsernamesDao userNamesDao=db.usernamesDao();//creating veriables for the database

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername2.getText().toString().trim();
                String password = etPassword2.getText().toString().trim();
                String confirm = etConfirm.getText().toString().trim();

                //checking if there is text in the username edittext
                if (username.isEmpty()) {
                    tvEnter.setText("Please enter a username");
                    return;
                }

                //checking if there is text in the password edittext
                if (password.isEmpty()) {
                    tvEnter.setText("Please enter a password");
                    return;
                }

                //checking if there is text in the confirm edittext
                if (confirm.isEmpty()) {
                    tvEnter.setText("Please confirm your password");
                    return;
                }

                //checking if the passwords are the same
                if (!confirm.equals(password)) {
                    tvEnter.setText("Passwords are not the same");
                    return;
                }

                //checking if the username already exists
                Usernames existing = userNamesDao.getUserByUsername(username);

                if (existing != null) {
                    tvEnter.setText("Username already exists");
                    return;
                }

                //if passed all checks creates a new user, inserts it and goes to the game activity
                Usernames newUser = new Usernames(username, password);
                userNamesDao.insert(newUser);
                Intent intent = new Intent(ActivitySignUp.this, ActivityGame.class);
                intent.putExtra("USERNAME_KEY", username);
                startActivity(intent);
                finish();
            }
        });
    }
}