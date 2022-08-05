package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AtmAdapter  extends RecyclerView.Adapter<AtmAdapter.MyViewHolder>{

    Context context;

    ArrayList<Atm> hotelsArrayList;



    public AtmAdapter(Context context, ArrayList<Atm> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public AtmAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.atm_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AtmAdapter.MyViewHolder holder, int position) {
        Atm atm=hotelsArrayList.get(position);
        holder.name.setText(atm.getAtmname());
        holder.place.setText(atm.getPlace());


    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,place;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.atmname1);
            place=itemView.findViewById(R.id.place);

        }
    }
}


