package com.example.gowithit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    Button submit;
    ListView listView;
    ArrayList<Hotels> hotelsArrayList;
    ArrayAdapter<String> adapter;
    DatabaseReference reference;
    //FirebaseAuth auth;
    FirebaseFirestore db;



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
        db=FirebaseFirestore.getInstance();
        Intent intent = new Intent(getApplicationContext(), RetrieveHotel.class);
        hotelsArrayList=new ArrayList<>();


        //listView=findViewById(R.id.listview);
        //adapter=new ArrayAdapter<String>(this,R.layout.hotel_info,R.id.hotelinfo,hotelsArrayList);
        //listView.setAdapter(adapter);
        //reference=FirebaseDatabase.getInstance().getReference("Hotels");
       // Query checkPlace=reference.orderByKey().equalTo(autoCompleteTextView.getText().toString());
//        dataSnapshot=FirebaseDatabase.getInstance().getReference().toString();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Places);
        autoCompleteTextView.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Hotels").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d:list){
                                Hotels c=d.toObject(Hotels.class);
                                hotelsArrayList.add(c);
                            }
                            adapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(CHotels.this, "NO Hotels Found In This Place", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CHotels.this, "Fail to get data", Toast.LENGTH_SHORT).show();
                    }
                });
//                try {
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