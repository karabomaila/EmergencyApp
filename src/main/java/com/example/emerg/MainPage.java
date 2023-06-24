package com.example.emerg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
    private Button hospital;
    private Button police;
    private Button fireBrigade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        hospital = findViewById(R.id.hospital_id);
        police = findViewById(R.id.police_id);
        fireBrigade = findViewById(R.id.fire_id);

        hospital.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, HospitalForm.class);
                startActivity(intent);
            }
        });

        police.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, PoliceHelpline.class);
                startActivity(intent);
            }
        });

        fireBrigade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, FireBrigade.class);
                startActivity(intent);
            }
        });
    }
}
