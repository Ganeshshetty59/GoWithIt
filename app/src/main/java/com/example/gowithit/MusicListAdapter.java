package com.example.gowithit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {


    ArrayList<AudioModel> songlist;
    Context context;

    public MusicListAdapter(ArrayList<AudioModel> songlist) {
        this.songlist = songlist;
    }

    public MusicListAdapter(Context context) {
        this.context = context;
    }

    public MusicListAdapter(ArrayList<AudioModel> songslist, Context applicationContext) {

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MusicListAdapter.ViewHolder holder, int position) {
        AudioModel songdata=songlist.get(position);
        holder.titletext.setText(songdata.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMedialPlayer.getInstance().reset();
                MyMedialPlayer.currentIndex=position;
                Intent intent=new Intent(context,MusicPlayer.class);
                intent.putExtra("List",songlist);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titletext;
        ImageView icon1;

        public ViewHolder( View itemView) {
            super(itemView);
            titletext=itemView.findViewById(R.id.musictitle);
            icon1=itemView.findViewById(R.id.musicicon);
        }

    }
}
