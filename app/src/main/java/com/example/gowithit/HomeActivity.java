package com.example.gowithit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
    CardView googlemap,calendar,music,profile,categories,search,report,setting,about,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        googlemap=(CardView) findViewById(R.id.map);
        calendar=(CardView) findViewById(R.id.calendar);
        music=(CardView) findViewById(R.id.music);
        profile=(CardView) findViewById(R.id.profile);
        categories=(CardView) findViewById(R.id.category);
        search=(CardView) findViewById(R.id.search);
        report=(CardView) findViewById(R.id.bugs);
        setting=(CardView) findViewById(R.id.setting);
        about=(CardView) findViewById(R.id.about);
        logout=(CardView) findViewById(R.id.logout);



    }
}