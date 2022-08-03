package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class BugReport extends AppCompatActivity {
    Button submit;
    EditText text;
    String name1,email2;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference,userref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);

        submit=findViewById(R.id.submit);
        text=findViewById(R.id.text1);
        auth=FirebaseAuth.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        userref=FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 name1=dataSnapshot.child("username").getValue().toString();
                 email2=dataSnapshot.child("email").getValue().toString();
//                String number2=dataSnapshot.child("phoneno").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference= FirebaseDatabase.getInstance().getReference("BugReport").child(user.getUid());

                HashMap<String,Object> map= new HashMap<>();
                map.put("name",name1);
                map.put("email",email2);
                map.put("userReport",text.getText().toString());

                reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(BugReport.this,"success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(BugReport.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(BugReport.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}