package com.example.emerg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username;
        EditText password;
        TextView forgotPassword;
        Button login;
        Button createAccount;

        username = findViewById(R.id.username_id);
        password = findViewById(R.id.password_id);

        String userUsername = username.getText().toString();
        String userPassword = password.getText().toString();

        if (userUsername.isEmpty()){
            username.setError("Please Enter Your Username");
        }
        if (userPassword.isEmpty()){
            password.setError("Please Enter Your Password");
        }

        login = findViewById(R.id.login_id);
        forgotPassword = findViewById(R.id.forgottenPass_id);
        createAccount = findViewById(R.id.createAcount_id);


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                i = 0;
            }
        });


        createAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        username.setText("");
        password.setText("");
    }
}
