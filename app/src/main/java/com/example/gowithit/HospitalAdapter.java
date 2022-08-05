package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HospitalAdapter  extends RecyclerView.Adapter<HospitalAdapter.MyViewHolder>{

    Context context;

    ArrayList<Hospital> hotelsArrayList;



    public HospitalAdapter(Context context, ArrayList<Hospital> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public HospitalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.hospital_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.MyViewHolder holder, int position) {
        Hospital hospital=hotelsArrayList.get(position);
        holder.name.setText(hospital.getHname());
        holder.phone.setText(hospital.getPhoneno());



    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.hospitalname1);
            phone=itemView.findViewById(R.id.phoneno111);



        }
    }
}

