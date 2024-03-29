package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseDatabase database;
    EditText username, password, email,phoneno;
    Button signup, signin;
    FirebaseUser user;
    private static final Pattern PASSWORD_PATTERN=
        Pattern.compile("^" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" +".{6,}");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phoneno=(EditText) findViewById(R.id.phoneno);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString().trim();
               String pass1=password.getText().toString();
               // String PASSWORD_PATTERN="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
                if (username.getText().toString().isEmpty() )
                {
                    Toast.makeText(MainActivity.this, "username cannot be empty", Toast.LENGTH_SHORT).show();
                }else
                    if( password.getText().toString().isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "password cannot be empty", Toast.LENGTH_SHORT).show();
                    }else
                        if( email.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "email cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if (! Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
                        Toast.makeText(MainActivity.this, "invalid email address", Toast.LENGTH_SHORT).show();
                    }
                    else if (!PASSWORD_PATTERN.matcher(pass1).matches())
                    {
                        Toast.makeText(MainActivity.this, "please use >6 Charecters ,any one special charecter(@#$%^&+=)and do not use SPACE for password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        register(username.getText().toString(), email.getText().toString(), password.getText().toString());
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                }
           // }
        });
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
    private void register(String username,String email,String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("LOGIN",email);


                try {

                    if (task.isSuccessful()){
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String userid =firebaseUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                        HashMap<String ,String> hashMap = new HashMap<>();
                        hashMap.put("id",userid);
                        hashMap.put("username",username);
                        hashMap.put("email",email);
                        hashMap.put("phoneno",phoneno.getText().toString());
//                        hashMap.put("Bio","default");
//                        hashMap.put("status","Offline");
                        Log.d("LOGIN",userid);




                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                        });
                    }else{
                        Toast.makeText(MainActivity.this,"this email already exist ! please try to login",Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        user=FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

    }
}
