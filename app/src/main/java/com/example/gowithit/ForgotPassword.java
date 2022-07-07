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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText forgotemail;
    Button resetpassword;
    ImageButton back;
    String email;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotemail = (EditText) findViewById(R.id.foremail);
        resetpassword= (Button) findViewById(R.id.resetemail);
        back=(ImageButton)findViewById(R.id.back1);

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
    private void validateData(){
        email=forgotemail.getText().toString();
        if (email.isEmpty()){
            Toast.makeText(this, "email required", Toast.LENGTH_SHORT).show();
        }else
        {
            forgetpassword();
        }
    }
    private void forgetpassword(){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Please check your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotPassword.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ForgotPassword.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}