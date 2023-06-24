package com.example.emerg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HospitalHelpline extends AppCompatActivity {
    Button emergency;
    Button medication;
    Button consultation;
    Button other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_helpline);

        emergency = findViewById(R.id.emergency_id);
        medication = findViewById(R.id.meds_id);
        consultation = findViewById(R.id.consultation_id);;
        other= findViewById(R.id.other_id);


        emergency.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HospitalHelpline.this, HospitalLocation.class);
                startActivity(intent);
            }
        });

        medication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HospitalHelpline.this, HospitalLocation.class);
                startActivity(intent);
            }
        });

        consultation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HospitalHelpline.this, HospitalLocation.class);
                startActivity(intent);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int i =0;
            }
        });
    }
}
