package com.example.rsssn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NearbyPlaces extends AppCompatActivity implements View.OnClickListener {

     DatabaseReference databaseReference;
     FirebaseDatabase database;
     DatabaseReference rootreference;
     DatabaseReference locationreference;
    private Button AddMarkerbtn;
    private Button Mapbtn;
    private EditText IncidentET;
    private  EditText LatitudeET;
    private EditText LongitudeET;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places);

        database= FirebaseDatabase.getInstance();
        rootreference= database.getReference();


        locationreference = rootreference.child("Location");
        IncidentET = findViewById(R.id.IncidentET);
        LatitudeET = findViewById(R.id.LatitudeET);
        LongitudeET = findViewById(R.id.LongitudeET);
        Mapbtn = findViewById(R.id.Mapbtn);
        AddMarkerbtn = findViewById(R.id.AddMarkerbtn);
        AddMarkerbtn.setOnClickListener(this);

    }

    private void saveUserInformation(){

        id = locationreference.push().getKey();
        String name = IncidentET.getText().toString().trim();
        double latitude = Double.parseDouble(LatitudeET.getText().toString().trim());
        double longitude = Double.parseDouble(LongitudeET.getText().toString().trim());
        UserInformationMap userInformationMap = new UserInformationMap(id,name, latitude, longitude);
        locationreference.child(id).setValue(userInformationMap);
        Toast.makeText(this,"Marker Added",Toast.LENGTH_LONG).show();
    }



    @Override
    public void onClick(View view) {

        if (view ==Mapbtn){
            finish();
        }

        if (view == AddMarkerbtn){

            saveUserInformation();
            IncidentET.getText();
            LatitudeET.getText();
            LongitudeET.getText();

        }

    }

    public void map(View view) {

        startActivity(new Intent(NearbyPlaces.this,MapsActivity.class));
    }
}