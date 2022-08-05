package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PetrolAdapter  extends RecyclerView.Adapter<PetrolAdapter.MyViewHolder>{

    Context context;

    ArrayList<Petrol> hotelsArrayList;



    public PetrolAdapter(Context context, ArrayList<Petrol> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public PetrolAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.petrol_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetrolAdapter.MyViewHolder holder, int position) {
        Petrol petrol=hotelsArrayList.get(position);
        holder.name.setText(petrol.getName());



    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.petrolname1);


        }
    }
}

