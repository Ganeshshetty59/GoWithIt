package com.example.gowithit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HotelListAdapter extends ArrayAdapter {
    private Activity mcontext;
    List<Hotels> hotelsList;
    public HotelListAdapter(Activity mcontext,List<Hotels> hotelsList){
        super(mcontext,R.layout.hotel_info,hotelsList);
        this.mcontext=mcontext;
        this.hotelsList=hotelsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=mcontext.getLayoutInflater();
        View listitemView=inflater.inflate(R.layout.hotel_info,null,true);

        TextView tvname=listitemView.findViewById(R.id.hotelname);
        TextView tvphone=listitemView.findViewById(R.id.phoneno);
        TextView tvtype=listitemView.findViewById(R.id.hoteltype);

        Hotels hotels=hotelsList.get(position);

        tvname.setText(hotels.getHotelName());
        tvphone.setText(hotels.getPhoneNo());
        tvtype.setText(hotels.getType());

        return listitemView;


    }
}
