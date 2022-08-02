package com.example.gowithit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Hotels> hotellist;

    public HotelListAdapter(Context context, ArrayList<Hotels> hotellist) {
        this.context = context;
        this.hotellist = hotellist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.hotel_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hotels hotels=hotellist.get(position);
        holder.name.setText(hotels.getHotelName());
        holder.phone.setText(hotels.getPhoneNo());
        holder.type.setText(hotels.getType());
        holder.place.setText(hotels.getPlace());

    }

    @Override
    public int getItemCount() {
        return hotellist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,phone,type,place;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.hotelname);
            phone=itemView.findViewById(R.id.phoneno);
            type=itemView.findViewById(R.id.hoteltype);
            place=itemView.findViewById(R.id.hotelplace);
        }
    }
//    private Activity mcontext;
//    ArrayList<Hotels> hotelsList;
//    public HotelListAdapter(Activity mcontext,List<Hotels> hotelsList){
//        super(mcontext,R.layout.hotel_info,hotelsList);
//        this.mcontext=mcontext;
//        this.hotelsList=hotelsList;
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater=mcontext.getLayoutInflater();
//        View listitemView=inflater.inflate(R.layout.hotel_info,null,true);
//
//        TextView tvname=listitemView.findViewById(R.id.hotelname);
//        TextView tvphone=listitemView.findViewById(R.id.phoneno);
//        TextView tvtype=listitemView.findViewById(R.id.hoteltype);
//        TextView tvplace=listitemView.findViewById(R.id.hotelplace);
//
//        Hotels hotels=hotelsList.get(position);
//
//        tvname.setText(hotels.getHotelName());
//        tvphone.setText(hotels.getPhoneNo());
//        tvtype.setText(hotels.getType());
//        tvplace.setText(hotels.getPlace());
//
//        return listitemView;
//
//
//    }
}
