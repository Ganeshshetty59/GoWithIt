package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AutoAdapter  extends RecyclerView.Adapter<AutoAdapter.MyViewHolder>{

    Context context;

    ArrayList<Auto> hotelsArrayList;



    public AutoAdapter(Context context, ArrayList<Auto> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public AutoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.auto_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoAdapter.MyViewHolder holder, int position) {
        Auto auto=hotelsArrayList.get(position);
        holder.name.setText(auto.getDrivername());
        holder.phone.setText(auto.getPhoneno());


    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.drivername1);
            phone=itemView.findViewById(R.id.phoneno11);

        }
    }
}

