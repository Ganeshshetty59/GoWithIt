package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrieveHotel extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Hotels hotels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_hotel);

        listView=findViewById(R.id.listview);
        adapter=new ArrayAdapter<String>(this,R.layout.hotel_info,R.id.hotelinfo,list);
        listView.setAdapter(adapter);

//        hotels=new Hotels();
//        database=FirebaseDatabase.getInstance();
//        list=new ArrayList<>();
//        reference=FirebaseDatabase.getInstance().getReference().child("Hotels");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds:dataSnapshot.getChildren()){
//                    list.add(ds.child("HotelName").getValue().toString()+"  "+ds.child("PhoneNo").getValue().toString()+"  "+
//                            ds.child("Type").getValue().toString()+"  ");
//
//                }
//                listView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}