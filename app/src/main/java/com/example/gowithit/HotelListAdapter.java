package com.example.gowithit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.MyViewHolder>{

    Context context;

    ArrayList<Hotels> hotelsArrayList;



    public HotelListAdapter(Context context, ArrayList<Hotels> list) {
        this.context = context;
        this.hotelsArrayList = list;

    }

    @NonNull
    @Override
    public HotelListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.hotel_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.MyViewHolder holder, int position) {
        Hotels hotels=hotelsArrayList.get(position);
        holder.name.setText(hotels.getHotelname());
        holder.phone.setText(hotels.getPhoneno());
        holder.type.setText(hotels.getType());
        holder.place.setText(hotels.getPlace());

    }

    @Override
    public int getItemCount() {
        return hotelsArrayList.size();
    }


        public static class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name,phone,type,place;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.hotelname1);
                phone=itemView.findViewById(R.id.phoneno1);
                type=itemView.findViewById(R.id.hoteltype);
                place=itemView.findViewById(R.id.hotelplace);
            }
        }
}


