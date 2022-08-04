package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveHotel extends AppCompatActivity {
    ListView listView;
    RecyclerView recyclerView;
    ArrayList<Hotels> hotelsArrayList;
    AutoCompleteTextView autoCompleteTextView;
    HotelListAdapter myadapter;
    String actvtext1;
//    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
//    ArrayList<Hotels> list;
//    ArrayAdapter<String> adapter;
//    Hotels hotels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_hotel);

        recyclerView=findViewById(R.id.recycler);
        autoCompleteTextView=findViewById(R.id.autoComplete);
        hotelsArrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager((RetrieveHotel.this));
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

//        recyclerView.setAdapter(myadapter);

        Intent intent=getIntent();
        actvtext1=intent.getStringExtra("actvtext");
//        list=new ArrayList<>();


//        actvtext=autoCompleteTextView.getText().toString();
//        Toast.makeText(RetrieveHotel.this,actvtext1, Toast.LENGTH_SHORT).show();
//        user=FirebaseAuth.getInstance().getCurrentUser();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Hotels");
//        Toast.makeText(RetrieveHotel.this,reference.toString(), Toast.LENGTH_SHORT).show();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hplace=dataSnapshot.child(actvtext1).child("Place").getValue(String.class);
                if(dataSnapshot.child(actvtext1).child("Place").exists()) {
//                Toast.makeText(RetrieveHotel.this,hplace, Toast.LENGTH_SHORT).show();
                    if (hplace.equals(actvtext1)) {
                        hotelsArrayList.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                Toast.makeText(RetrieveHotel.this,ds.toString(), Toast.LENGTH_SHORT).show();
                            Hotels hotels = ds.getValue(Hotels.class);
                            Toast.makeText(RetrieveHotel.this, hotels.getHotelName(), Toast.LENGTH_SHORT).show();
                            hotelsArrayList.add(hotels);
//                        Log.d("hotels",hotelsArrayList.toString());
                        }
                        myadapter=new HotelListAdapter(RetrieveHotel.this,hotelsArrayList);
                        recyclerView.setAdapter(myadapter);

//                    myadapter.notifyDataSetChanged();

//                            Toast.makeText(CHotels.this,, Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(RetrieveHotel.this, "No hotels found in this location", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RetrieveHotel.this,"Error"+databaseError, Toast.LENGTH_SHORT).show();

            }
        });

    }
}