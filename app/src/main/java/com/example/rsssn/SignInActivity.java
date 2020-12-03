package com.example.rsssn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rsssn.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText memail, mpassword;
    Button signupbtn, signinbtn;
    TextView signuptv, signintv;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        memail = findViewById(R.id.emailET);
        mpassword = findViewById(R.id.passwordET);
        signinbtn = findViewById(R.id.signInBtn);
        signupbtn = findViewById(R.id.signUpBtn);
        signintv = findViewById(R.id.signInTV);
        signuptv = findViewById(R.id.signUpTV);

        fauth = FirebaseAuth.getInstance();


        if (fauth.getCurrentUser() != null){

            startActivity(new Intent(SignInActivity.this,MainActivity.class));
            finish();
        }




        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){

                    memail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){

                    mpassword.setError("Password is Required");
                    return;
                }

                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{

                            Toast.makeText(SignInActivity.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
        signuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));

            }
        });



    }
}