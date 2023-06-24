package com.example.emerg;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GetLocation {
    public FusedLocationProviderClient mFusedLocationClient;
    Context context;
    LocationManager locationManager;
    int PERMISSION_ID = 44;

    double Latitude;
    double Longitude;

    public GetLocation(Context mcontext){
        this.context = mcontext;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mcontext);
        Latitude = 0;
        Longitude = 0;
    }
    @SuppressLint("MissingPermission")
    public void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    public LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            Latitude =  mLastLocation.getLatitude();
            Longitude = mLastLocation.getLongitude();
        }
    };

    public boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        //return  ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);

    }

    public boolean isLocationNotFound(){
        return Latitude==0 && Longitude ==0;
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation(boolean isLocationEnabled) {
        if (checkPermissions()) {
            if (isLocationEnabled) {

                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();

                        if (location == null) {
                            requestNewLocationData();
                        } else { 
                            Latitude = location.getLatitude();
                            Longitude = location.getLongitude();
                        }
                    }
                });
            }
        } else {
            requestPermissions();
        }
    }
}
