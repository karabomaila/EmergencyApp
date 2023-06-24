package com.example.emerg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FireBrigade extends AppCompatActivity {
    LocationManager locationManager;
    Geocoder geocoder;
    static GetLocation getLocation;
    int PERMISSION_ID = 44;

    private Button locationAccess;
    private Button sendLocation;
    private Button sendLocation2;
    private EditText typeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_brigade);

        getLocation = new GetLocation(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(this, Locale.getDefault());

        locationAccess = findViewById(R.id.fire_allowlocation);
        sendLocation = findViewById(R.id.fbsendLocation_id);
        sendLocation2 = findViewById(R.id.fbsendlocation2_id);
        typeLocation = findViewById(R.id.fbtypeLocation_id);

        locationAccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getLocation.getLastLocation(isLocationEnabled());
                if (getLocation.isLocationNotFound()){
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            }
        });

        sendLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nInfo = cm.getActiveNetworkInfo();
                boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

                if (!connected){
                    Toast toast = Toast.makeText(FireBrigade.this, "Please Connect To The Internet", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                if (getLocation.isLocationNotFound()){
                    sendLocation2.setVisibility(v.VISIBLE);
                    typeLocation.setVisibility(v.VISIBLE);
                }
                else{
                    List<Address> addresses = null;

                    try {
                        addresses = geocoder.getFromLocation(getLocation.Latitude, getLocation.Longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String address = addresses.get(0).getAddressLine(0);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String postalCode = addresses.get(0).getPostalCode();
                    String streetNumber = addresses.get(0).getFeatureName();
                }
            }
        });

        sendLocation2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String location = typeLocation.getText().toString();
            }
        });

    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation.getLastLocation(isLocationEnabled());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getLocation.checkPermissions()) {
            getLocation.getLastLocation(isLocationEnabled());
        }
    }


}