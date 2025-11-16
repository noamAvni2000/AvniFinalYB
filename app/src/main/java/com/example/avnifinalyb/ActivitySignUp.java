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
                String username=etUsername2.getText().toString().trim();
                String password=etPassword2.getText().toString().trim();//getting the text from the user(trim removes unnecessary spaces)
                String confirm=etConfirm.getText().toString().trim();
                Log.d("ActivitySignUp", "btnRegister.onclick: confirm="+confirm+" password="+password);

                if(!confirm.equals(password))//checks if the password is entered the same in both edit texts
                {
                    tvEnter.setText("Password or username incorrect");
                    Toast.makeText(ActivitySignUp.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    Log.d("ActivitySignUp", "btnRegister.onclick: confirm="+confirm+" password="+password);
                }
                else {
                    Usernames user1 = userNamesDao.login(username, password);
                    if(user1!=null) {
                        userNamesDao.insert(user1);
                        Intent intent = new Intent(ActivitySignUp.this, ActivityGame.class);
                        intent.putExtra("USERNAME_KEY", username);//saves the username of the user
                        startActivity(intent);
                        finish();
                        Log.d("ActivitySignUp", "btnRegister.onclick: user1");
                    }
                    else{
                        tvEnter.setText("Password or username incorrect");
                        Toast.makeText(ActivitySignUp.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                        Log.d("ActivitySignUp", "btnRegister.onclick: user2");
                    }
                }
            }
        });
    }
}