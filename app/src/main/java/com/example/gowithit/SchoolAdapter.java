package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SchoolAdapter  extends RecyclerView.Adapter<SchoolAdapter.MyViewHolder>{

    Context context;

    ArrayList<School> hotelsArrayList;



    public SchoolAdapter(Context context, ArrayList<School> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public SchoolAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.school_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolAdapter.MyViewHolder holder, int position) {
        School school=hotelsArrayList.get(position);
        holder.name.setText(school.getName());



    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.schoolname1);


        }
    }
}

