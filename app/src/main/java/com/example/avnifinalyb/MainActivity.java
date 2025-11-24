package com.example.avnifinalyb;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
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

public class MainActivity extends AppCompatActivity {

    TextView tvEnterDetails;
    EditText etPassword, etUsername;
    Button btnLogin, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvEnterDetails=findViewById(R.id.tvEnterDetails);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        etUsername=findViewById(R.id.etUsername);

        etPassword.setTransformationMethod(new PasswordTransformationMethod());//hides the password during login

        UsersDatabase db= UsersDatabase.getInstance(this);
        UsernamesDao userNamesDao=db.usernamesDao();
        Usernames user=new Usernames();
        user.setUsername("noam");
        user.setPassword("123");
        userNamesDao.insert(user);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();//getting the text from the user(trim removes unnecessary spaces)
                Usernames user=userNamesDao.login(username,password);
                if(user!=null)
                {
                    Intent intent = new Intent(MainActivity.this, ActivityGame.class);
                    intent.putExtra("USERNAME_KEY", username);//saves the username of the user
                    startActivity(intent);
                    finish();
                }//using the function from the dao to check if the user is in the database(it retuns null if not found)
                else {
                    Toast.makeText(MainActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }//a toast to show the user their login attempt failed
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}