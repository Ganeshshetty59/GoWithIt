package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BanksAdapter  extends RecyclerView.Adapter<BanksAdapter.MyViewHolder>{

    Context context;

    ArrayList<Banks> hotelsArrayList;



    public BanksAdapter(Context context, ArrayList<Banks> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public BanksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.bank_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BanksAdapter.MyViewHolder holder, int position) {
        Banks banks=hotelsArrayList.get(position);
        holder.name.setText(banks.getBankname());
        holder.ifsc.setText(banks.getIfsc());


    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,ifsc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.bankname1);
            ifsc=itemView.findViewById(R.id.ifsc);

        }
    }
}


