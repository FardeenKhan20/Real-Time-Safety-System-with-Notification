package com.example.rsssn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddContacts extends AppCompatActivity {



    //Shared Preferences
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText numberOne, numberTwo;
    private CheckBox mCheckbox;
    private Button mUpdate;

    //Location and Send Emergency Message

    public BackgroundService backgroundService;


    Button sendMessage;
    EditText emergencyMessage;

    public LocationManager locationManager;
    public LocationListener locationListener = new MyLocationListener();
    String lat, lon;

    private Boolean gps_enable = false;
    private Boolean network_enable = false;

    Geocoder geocoder;
    List<Address> sendAddress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        //Send Emergency Message

        ActivityCompat.requestPermissions(AddContacts.this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);



        //Shared Preferences
        numberOne = findViewById(R.id.AddcontactsET1);
        numberTwo = findViewById(R.id.AddcontactsET2);
        mCheckbox = findViewById(R.id.checkBox);
        mUpdate = findViewById(R.id.AddcontactsBtn);


        //Location and Send Emergency Message
        sendMessage = findViewById(R.id.EmergencyBtn);
        emergencyMessage = findViewById(R.id.EmergencyTV);


        //Shared Preferences
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        checkSharedPreferences();

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCheckbox.isChecked()) {

                    mEditor.putString(getString(R.string.checkbox), "True");
                    mEditor.commit();

                    String numberone = numberOne.getText().toString();
                    mEditor.putString(getString(R.string.numberone), numberone);
                    mEditor.commit();

                    String numbertwo = numberTwo.getText().toString();
                    mEditor.putString(getString(R.string.numbertwo), numbertwo);
                    mEditor.commit();
                } else {

                    mEditor.putString(getString(R.string.checkbox), "False");
                    mEditor.commit();


                    mEditor.putString(getString(R.string.numberone), "");
                    mEditor.commit();


                    mEditor.putString(getString(R.string.numbertwo), "");
                    mEditor.commit();
                }
            }


        });

        //Location and Sending Message

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMyLocation();





            }
        });

        checkLocationPermission();




    }



        private void checkSharedPreferences () {

            String checkbox = mPreferences.getString(getString(R.string.checkbox), "False");
            String numberone = mPreferences.getString(getString(R.string.numberone), "");
            String numbertwo = mPreferences.getString(getString(R.string.numbertwo), "");


            numberOne.setText(numberone);
            numberTwo.setText(numbertwo);

            if (checkbox.equals("True")) {

                mCheckbox.setChecked(true);

            } else {
                mCheckbox.setChecked(false);
            }
        }


    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {

            if (location != null) {

                locationManager.removeUpdates(locationListener);

                lat = "" + location.getLatitude();
                lon = "" + location.getLongitude();

                geocoder = new Geocoder(AddContacts.this, Locale.getDefault());

                try {
                    sendAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String address1 ="I need Emergency help!!! I am near this location: " + sendAddress.get(0).getAddressLine(0);

                emergencyMessage.setText(address1);

                String numberone = numberOne.getText().toString();
                String numbertwo = numberTwo.getText().toString();
                String emessage = emergencyMessage.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numberone,null,emessage,null,null);
                smsManager.sendTextMessage(numbertwo,null,emessage,null,null);


            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }

    public void getMyLocation() {

        try {
            gps_enable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

        }

        try {
            network_enable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {


        }

        if (!gps_enable && network_enable) {

            AlertDialog.Builder builder = new AlertDialog.Builder(AddContacts.this);
            builder.setTitle("Attention");
            builder.setMessage("Please enable location service");

            builder.create().show();
        }


        if (gps_enable) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }

        if (network_enable){

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
        }

        }


        private boolean checkLocationPermission(){

        int location1 = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int location2 = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION);

            List<String> listPermission = new  ArrayList<>();

            if (location1 != PackageManager.PERMISSION_GRANTED){

                listPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (location2 != PackageManager.PERMISSION_GRANTED){

                listPermission.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (!listPermission.isEmpty()){

                ActivityCompat.requestPermissions(this,listPermission.toArray(new String[listPermission.size()]),1);
            }


            return true;
        }


    private class BackgroundService extends Service implements SensorEventListener {

        float accelVal, accelLast, shake;

        Sensor accelerometer;
        SensorManager sensorManager;



        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onCreate() {
            super.onCreate();

            accelVal = SensorManager.GRAVITY_EARTH;
            accelLast = SensorManager.GRAVITY_EARTH;
            shake = 0.00f;

            sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelVal = accelLast;
            accelVal = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = accelVal - accelLast;
            shake = shake * 0.9f + delta;

            if (shake> 15){

                sendMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMyLocation();
                    }
                });
            }



        }






    }
}






