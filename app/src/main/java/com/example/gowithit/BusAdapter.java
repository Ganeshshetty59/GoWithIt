package com.example.gowithit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusAdapter  extends RecyclerView.Adapter<BusAdapter.MyViewHolder>{

    Context context;

    ArrayList<Bus> hotelsArrayList;



    public BusAdapter(Context context, ArrayList<Bus> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public BusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.bus_info,parent,false);
        return new BusAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BusAdapter.MyViewHolder holder, int position) {
        Bus bus=hotelsArrayList.get(position);
        holder.name.setText(bus.getBusname());
        holder.time.setText(bus.getBustime());
        holder.to.setText(bus.getTo());


    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,time,to;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.busname1);
            time=itemView.findViewById(R.id.time1);
            to=itemView.findViewById(R.id.to1);

        }
    }

}
