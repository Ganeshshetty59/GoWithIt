package com.example.gowithit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class ListenMusic extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView Nomusic;
    ArrayList<AudioModel> songslist=new ArrayList<>();

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
        String[] projection={
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION

        };
        String selection=MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        while (cursor.moveToNext()){
            AudioModel songdata=new AudioModel(cursor.getString(1),cursor.getString(0),cursor.getString(2));
            if (new File(songdata.getPath()).exists())
                songslist.add(songdata);
        }
        if (songslist.size()==0){
            Nomusic.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdapter(songslist,getApplicationContext()));
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