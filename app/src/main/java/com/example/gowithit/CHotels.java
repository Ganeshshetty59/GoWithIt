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
    String actvtext;
    ListView listView;
//    List<Hotels> hotelsList;
   ArrayList<Hotels> hotelsArrayList;
//   HotelListAdapter adapter;
   DatabaseReference reference;
    FirebaseAuth auth;
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
        hotelsArrayList=new ArrayList<>();
        actvtext=autoCompleteTextView.getText().toString();
        recyclerView=findViewById(R.id.recycler);

        auth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Hotels");


        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Places);
        autoCompleteTextView.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvtext=autoCompleteTextView.getText().toString();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        hotelsArrayList.clear();
                        String hplace=dataSnapshot.child(actvtext).child("Place").getValue(String.class);

                        if(hplace.equals(actvtext)){
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                Toast.makeText(CHotels.this,hplace, Toast.LENGTH_SHORT).show();
                                Hotels hotels = ds.getValue(Hotels.class);
                                hotelsArrayList.add(hotels);
                            }
                            Toast.makeText(CHotels.this, hplace, Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                    }
                });
    }
}