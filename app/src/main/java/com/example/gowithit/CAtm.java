package com.example.gowithit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class CAtm extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    Button submit;
    String actvtext;
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

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Places);
        autoCompleteTextView.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actvtext=autoCompleteTextView.getText().toString();
                Intent intent = new Intent(CAtm.this,RetrieveATM.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("actvtext",actvtext);
                startActivity(intent);


            }
        });
    }
}