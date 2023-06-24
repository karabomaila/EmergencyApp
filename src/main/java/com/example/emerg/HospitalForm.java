package com.example.emerg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HospitalForm extends AppCompatActivity {
    LocationManager locationManager;
    Geocoder geocoder;
    static GetLocation getLocation;
    int PERMISSION_ID = 44;

    EditText firstName;
    EditText lastName;
    EditText contact;
    EditText email;
    EditText nextOfKinFirstName;
    EditText nextOfKinLastName;
    EditText nextOfKinContact;
    EditText relationship;
    Button sendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_form);

        firstName = findViewById(R.id.firstName_id);
        lastName = findViewById(R.id.lastName_id);
        contact = findViewById(R.id.contactNumber_id);
        email = findViewById(R.id.emai_id);
        nextOfKinFirstName = findViewById(R.id.firstname2_id);
        nextOfKinLastName = findViewById(R.id.lastname2_id);
        nextOfKinContact = findViewById(R.id.contactNumber2_id);
        relationship = findViewById(R.id.relationship_id);

        final String HFirstName = firstName.getText().toString();
        final String HLastName= lastName.getText().toString();
        final String HContact = contact.getText().toString();
        final String HEmail = email.getText().toString();
        final String HNextOfKinFirstName = nextOfKinFirstName.getText().toString();
        final String HNextOfKinLastName = nextOfKinLastName.getText().toString();
        final String HnextOfKinContact  = nextOfKinContact.getText().toString();
        final String HRelationship = relationship.getText().toString();


        sendInfo = findViewById(R.id.save_id);
        sendInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (HFirstName.isEmpty() || HFirstName.length()<2){ firstName.setError("Please Enter Your First Name"); return;}
                if (HLastName.isEmpty() || HLastName.length()<2){ lastName.setError("Please Enter Your Last Name"); return;}
                if (HContact.isEmpty() || HContact.length()>10){ contact.setError("Please Enter Your Contact Number"); return;}
                if (HEmail.isEmpty()){ email.setError("Please Enter Your Email"); return;}

                if (HNextOfKinFirstName.isEmpty() || HNextOfKinFirstName.length()<2){ nextOfKinFirstName.setError("Please Enter Your Next Of Kin First Name"); return;}
                if (HNextOfKinLastName.isEmpty() || HNextOfKinLastName.length()<2){ nextOfKinLastName.setError("Please Enter Your Next Of Kin Last Name"); return;}
                if (HnextOfKinContact.isEmpty() || HnextOfKinContact.length()>10){ nextOfKinContact.setError("Please Enter Your Next Of Kin Contact Number"); return;}
                if (HRelationship.isEmpty() ){ firstName.setError("Please Enter Relationship"); return;}

                Intent intent = new Intent(HospitalForm.this, HospitalHelpline.class);
                startActivity(intent);
            }
        });


    }


}