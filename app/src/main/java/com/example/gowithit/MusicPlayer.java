package com.example.gowithit;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayer extends AppCompatActivity {
    TextView title,currentTime,totalTime;
    SeekBar seekBar;
    ImageView pauseplay,nextbtn,previousbtn,musicicon;
    ArrayList<AudioModel> songlist;
    AudioModel currentsong;
    MediaPlayer mediaPlayer=MyMedialPlayer.getInstance();
    int x=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        title=findViewById(R.id.songtitle);
        currentTime=findViewById(R.id.currenttime);
        totalTime=findViewById(R.id.totaltime);
        seekBar=findViewById(R.id.seekbar);
        pauseplay=findViewById(R.id.pause_play);
        nextbtn=findViewById(R.id.next);
        previousbtn=findViewById(R.id.previous);
        musicicon=findViewById(R.id.musicicon);

        title.setSelected(true);

        songlist=(ArrayList<AudioModel>) getIntent().getSerializableExtra("List");
        setResourcesWithMusic();
        MusicPlayer.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentTime.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));
                    if (mediaPlayer.isPlaying()){
                        pauseplay.setImageResource(R.drawable.pause);
                        musicicon.setRotation(x++);
                    }else {
                        pauseplay.setImageResource(R.drawable.play);
                        musicicon.setRotation(0);
                    }
                }
                new Handler().postDelayed(this,100);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mediaPlayer!=null&&fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    void  setResourcesWithMusic(){
        currentsong=songlist.get(MyMedialPlayer.currentIndex);
        title.setText(currentsong.getTitle());
        totalTime.setText(convertToMMSS(currentsong.getDuration()));

        pauseplay.setOnClickListener(v-> pausePlay());
        nextbtn.setOnClickListener(v-> playNextSong());
        previousbtn.setOnClickListener(v-> playPreviousSong());

        playMusic();

    }
    private void playMusic(){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentsong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void playNextSong(){
        if (MyMedialPlayer.currentIndex==songlist.size()-1)
            return;
        MyMedialPlayer.currentIndex+=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }
    private void playPreviousSong(){
        if (MyMedialPlayer.currentIndex==0)
            return;
        MyMedialPlayer.currentIndex-=1;
        mediaPlayer.reset();
        setResourcesWithMusic();

    }
    private void pausePlay(){
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();

    }

    public static String convertToMMSS(String duration){
        Long millis=Long.parseLong(duration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis)%TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis)%TimeUnit.MINUTES.toSeconds(1));
    }

}