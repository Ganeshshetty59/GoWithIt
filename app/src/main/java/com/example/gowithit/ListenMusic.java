package com.example.gowithit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ListenMusic extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView Nomusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_music);

        recyclerView=findViewById(R.id.recyclersong);
        Nomusic=findViewById(R.id.nosong);

        if (checkPermission()==false){
            requestPermission();
            return;
        }

    }
    boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(ListenMusic.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result== PackageManager.PERMISSION_GRANTED){
            return true;
        }else {
            return false;
        }
    }
    void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(ListenMusic.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(this, "READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT).show();
        }else {
            ActivityCompat.requestPermissions(ListenMusic.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }
}