package com.example.rsssn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Helpline extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private TextView tview1;
    private TextView tview2;
    private TextView tview3;
    private TextView tview4;
    private TextView tview5;
    private TextView tview6;

    private Button callbtn1, callbtn2, callbtn3, callbtn4, callbtn5, callbtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);

        tview1 = findViewById(R.id.tv1);
        tview2 = findViewById(R.id.tv2);
        tview3 = findViewById(R.id.tv3);
        tview4 = findViewById(R.id.tv4);
        tview5 = findViewById(R.id.tv5);
        tview6 = findViewById(R.id.tv6);

        callbtn1 = findViewById(R.id.button1);
        callbtn2 = findViewById(R.id.button2);
        callbtn3 = findViewById(R.id.button3);
        callbtn4 = findViewById(R.id.button4);
        callbtn5 = findViewById(R.id.button5);
        callbtn6 = findViewById(R.id.button6);

        callbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall1();
            }
        });

        callbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall2();
            }
        });

        callbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall3();
            }
        });

        callbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall4();
            }
        });

        callbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall5();
            }
        });

        callbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall6();
            }
        });


    }

    private void makePhoneCall1() {

        String number = tview1.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helpline.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }

    }

    private void makePhoneCall2() {

        String number = tview2.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helpline.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }



    }

    private void makePhoneCall3() {

        String number = tview3.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helpline.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }



    }

    private void makePhoneCall4() {

        String number = tview4.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helpline.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }



    }

    private void makePhoneCall5() {

        String number = tview5.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helpline.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }



    }

    private void makePhoneCall6() {

        String number = tview6.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Helpline.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Helpline.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall1();
                makePhoneCall2();
                makePhoneCall3();
                makePhoneCall4();
                makePhoneCall5();
                makePhoneCall6();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}