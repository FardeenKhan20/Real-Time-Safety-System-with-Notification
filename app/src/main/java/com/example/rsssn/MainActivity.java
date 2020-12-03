package com.example.rsssn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void Logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
        finish();
    }

    public void HelplineNumbers(View view) {
        startActivity(new Intent(MainActivity.this,Helpline.class));
    }

    public void Howtouse(View view) {
        startActivity(new Intent(MainActivity.this,Howtouse.class));
    }


    public void AddContacts(View view) {

        startActivity(new Intent(MainActivity.this, AddContacts.class));
    }

    public void Nearbyplaces(View view) {

        startActivity(new Intent(MainActivity.this, NearbyPlaces.class));
    }




}