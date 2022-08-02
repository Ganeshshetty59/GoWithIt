package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CHotels extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    RecyclerView recyclerView;
    Button submit;
    String hotels,actvtext;
    ListView listView;
//    List<Hotels> hotelsList;
   ArrayList<Hotels> hotelsArrayList;
   HotelListAdapter adapter;
   DatabaseReference reference;
    //FirebaseAuth auth;
//    FirebaseFirestore db;



    private static final String[] Places=new String[]{
            "Ajri","Alooru","Amasbail","Amparu","Anagalli","Balkuru","Basruru","Belooru","Bijadi","Chithoor",
            "Gangolli","Gopadi","Gujjadi","Gulwadi","Hakladi","Haladi","Hambadi","Mandadi","Hanglur","Hardalli",
            "Mandalli","Hattiangadi","Hemmadi","Hengavalli","Hosaadu","Hosangadi","Idoor","Kalavara","Kandavara",
            "Karkunje","Katbelthur","Kavradi","Kedur","Keradi","Koni","Korgi","Koteshwara","Kumbashi","Kundapura",
            "Molahalli", "Shankaranarayana","Siddapura","Tallur","Tekkatte","Trasi","Ullur74","Vandse","Yedamoge"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chotels);

        autoCompleteTextView=findViewById(R.id.autoComplete);
        submit=findViewById(R.id.submit);
        autoCompleteTextView.setThreshold(1);
//        db=FirebaseFirestore.getInstance();
//        Intent intent = new Intent(getApplicationContext(), RetrieveHotel.class);
        hotelsArrayList=new ArrayList<>();
        //TextView hinfo= findViewById(R.id.hotelinfo);
//        listView=findViewById(R.id.listview);
        actvtext=autoCompleteTextView.getText().toString();
        recyclerView=findViewById(R.id.recycler);
//        hotelsList=new ArrayList<>();
        hotels=FirebaseAuth.getInstance().getCurrentUser().getUid();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new HotelListAdapter(this,hotelsArrayList);


        //listView=findViewById(R.id.listview);

        reference=FirebaseDatabase.getInstance().getReference("Hotels");
       Query checkPlace=reference.orderByChild("Place").equalTo(actvtext);
//        dataSnapshot=FirebaseDatabase.getInstance().getReference().toString();

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Places);
        autoCompleteTextView.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPlace.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Toast.makeText(CHotels.this, "step5", Toast.LENGTH_SHORT).show();
                                Hotels hotels = ds.getValue(Hotels.class);
                                hotelsArrayList.add(hotels);

                            }
//                            HotelListAdapter hadapter = new HotelListAdapter(CHotels.this, hotelsList);
//                            listView.setAdapter(hadapter);
//                            setContentView(R.layout.activity_retrieve_hotel);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//                reference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        Toast.makeText(CHotels.this, "step1", Toast.LENGTH_SHORT).show();
//
//                            Toast.makeText(CHotels.this, "step2", Toast.LENGTH_SHORT).show();
//                            hotelsList.clear();
//                            for (DataSnapshot ds:dataSnapshot.getChildren()) {
//                                String pushkey=dataSnapshot.getKey();
//                                Toast.makeText(CHotels.this, "step1.1", Toast.LENGTH_SHORT).show();
//                                if (pushkey==autoCompleteTextView.getText().toString()){
//                                Toast.makeText(CHotels.this, "step3", Toast.LENGTH_SHORT).show();
//                                if (ds.exists()) {
//                                    Toast.makeText(CHotels.this, "step4", Toast.LENGTH_SHORT).show();
//                                    Hotels hotels = ds.getValue(Hotels.class);
//                                    hotelsList.add(hotels);
//                                }else{
//                                    Toast.makeText(CHotels.this, "NO Hotels Found In This Place", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            Toast.makeText(CHotels.this, "step5", Toast.LENGTH_SHORT).show();
//                            HotelListAdapter hadapter=new HotelListAdapter(CHotels.this,hotelsList);
//                            listView.setAdapter(hadapter);
//                            hadapter.notifyDataSetChanged();
//                            setContentView(R.layout.activity_retrieve_hotel);
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                Toast.makeText(CHotels.this, "step-1", Toast.LENGTH_SHORT).show();
//                reference.child("Hotels").addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        Toast.makeText(CHotels.this, "step0", Toast.LENGTH_SHORT).show();
//                        if (dataSnapshot.exists()){
//                            String pushkey=dataSnapshot.getKey();
//                            Toast.makeText(CHotels.this, "step1", Toast.LENGTH_SHORT).show();
//                            if (pushkey.matches(autoCompleteTextView.getText().toString())){
//                                Toast.makeText(CHotels.this, "step2", Toast.LENGTH_SHORT).show();
//                                reference.child("Hotels").child("pushkey").addChildEventListener(new ChildEventListener() {
//                                    @Override
//                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                                        hotelsList.clear();
//                                        Toast.makeText(CHotels.this, "step3", Toast.LENGTH_SHORT).show();
//                                        if (dataSnapshot.exists()){
//                                            Toast.makeText(CHotels.this, "step4", Toast.LENGTH_SHORT).show();
//                                            for (DataSnapshot ds:dataSnapshot.getChildren()){
//                                                Toast.makeText(CHotels.this, "step5", Toast.LENGTH_SHORT).show();
//                                                Hotels hotels=ds.getValue(Hotels.class);
//                                                hotelsList.add(hotels);
////                                                hinfo.setText(ds.child("HotelName").getValue().toString()+"  "+ds.child("PhoneNo").getValue().toString()+"  "+
////                                                        ds.child("Type").getValue().toString()+"  ");
//
//                                            }
//                                            HotelListAdapter hadapter=new HotelListAdapter(CHotels.this,hotelsList);
//                                            listView.setAdapter(hadapter);
////                                            String list1=hinfo.getText().toString();
////                                            hotelsArrayList.add(list1);
////                                            listView.setAdapter(adapter);
//                                            setContentView(R.layout.activity_retrieve_hotel);
//
//
//
////                                            listView.setAdapter(adapter);
////                                            listView.setAdapter(adapter);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }else {
//                                Toast.makeText(CHotels.this, "NO Hotels Found In This Place", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//                db.collection("Hotels").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        if (!queryDocumentSnapshots.isEmpty()){
//                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                            for (DocumentSnapshot d:list){
//                                Hotels c=d.toObject(Hotels.class);
//                                hotelsArrayList.add(c);
//                            }
//                            adapter.notifyDataSetChanged();
//                        }else{
//                            Toast.makeText(CHotels.this, "NO Hotels Found In This Place", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(CHotels.this, "Fail to get data", Toast.LENGTH_SHORT).show();
//                    }
//                });
////                try {
//                    checkPlace.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            Toast.makeText(CHotels.this, "hii", Toast.LENGTH_SHORT).show();
//                            //String ds = dataSnapshot.child("Hotels").getValue().toString();
//                            if (autoCompleteTextView.getText().toString() == dataSnapshot.getValue(String.class)) {
//                                try {
//                                    Intent intent = new Intent(getApplicationContext(), RetrieveHotel.class);
//                                    startActivity(intent);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            } else {
//                                Toast.makeText(CHotels.this, "No Hotels There in This place", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });
    }
}