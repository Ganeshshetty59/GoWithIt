package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveChurch extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Church> hotelsArrayList;
    AutoCompleteTextView autoCompleteTextView;
    ChurchAdapter myadapter;
    String actvtext1;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_hotel);

        recyclerView=findViewById(R.id.recycler);
        autoCompleteTextView=findViewById(R.id.autoComplete);
        hotelsArrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        actvtext1=intent.getStringExtra("actvtext");

        reference= FirebaseDatabase.getInstance().getReference("Church").child(actvtext1);

        myadapter =new ChurchAdapter(this,hotelsArrayList);
        recyclerView.setAdapter(myadapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hotelsArrayList.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Church church= dataSnapshot.getValue(Church.class);
                        hotelsArrayList.add(church);

                    }
                    myadapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(RetrieveChurch.this, "No Church Found In this Location", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}