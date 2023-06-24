package com.example.emerg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    private EditText username;
    private EditText idNumber;
    private EditText passoword;
    private EditText confirmPassoword;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        username = findViewById(R.id.name_id);
        idNumber = findViewById(R.id.idnumber_id);
        passoword = findViewById(R.id.passwordid);
        confirmPassoword = findViewById(R.id.confirmPass_id);
        submit  = findViewById(R.id.submit_id);

        String userUsername = username.getText().toString();
        String userIdnumber = idNumber.getText().toString();
        String userPassword = passoword.getText().toString();
        String userPassConfirmation = confirmPassoword.getText().toString();

        if (userUsername.isEmpty() || userUsername.length()<2){
            username.setError("Please Enter Your Name");
            return;
        }
        if (!isValidPassword(userPassword)){
            passoword.setError("Please Enter a Strong Password");
            return;
        }
        if (!userPassword.equals(userPassConfirmation)){
            confirmPassoword.setError("Passwords Do not Match");
            return;
        }
        if (!checkLuhn(userIdnumber) || userIdnumber.isEmpty()){
            idNumber.setError("Please Enter a Valid IDNumber");
            return;
        }


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidPassword(String password){
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    static boolean checkLuhn(String idnumber)
    {
        int nDigits = idnumber.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = idnumber.charAt(i) - '0';

            if (isSecond)
                d = d * 2;

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

}
