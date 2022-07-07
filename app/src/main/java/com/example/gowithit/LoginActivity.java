package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class

LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnlogin,btnforgot;
    ImageButton backbtn;

    FirebaseAuth auth;
    FirebaseDatabase database;

    public static void setVisibility(int invisible) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnforgot = (Button) findViewById(R.id.forgot);
        backbtn=(ImageButton) findViewById(R.id.back) ;
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPassword.class));
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //try {


                    if (email.getText().toString().isEmpty()|| password.getText().toString().isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();

                    } else {
                        try {


                            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else {
                                        Toast.makeText(LoginActivity.this, "Can't find account!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}