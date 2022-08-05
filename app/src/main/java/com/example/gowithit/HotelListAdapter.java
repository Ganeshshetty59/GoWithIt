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



//public class HotelListAdapter extends ArrayAdapter<Hotel> {
//    private Activity context;
//    private  int resource;
//    private List<Hotel> hotellist;
//    FirebaseUser user;
//
//
//    public HotelListAdapter(@NonNull Activity context,@LayoutRes int resource,@NonNull List<Hotel> objects) {
//        super(context,resource,objects);
//        this.context = context;
//        this.resource=resource;
//        hotellist = objects;
//    }
//    @NonNull
//@Override
//    public View getView(final int position,@Nullable View convertView,@NonNull ViewGroup parent){
//        LayoutInflater inflater=context.getLayoutInflater();
//        View view=inflater.inflate(resource,null);
//        final TextView name=(TextView) view.findViewById(R.id.hotelname1);
//        final TextView phone=(TextView) view.findViewById(R.id.phoneno1);
//        final TextView type=(TextView) view.findViewById(R.id.hoteltype);
//        final TextView place=(TextView) view.findViewById(R.id.hotelplace);
//        name.setText(hotellist.get(position).getHotelname());
//        phone.setText(hotellist.get(position).getPhoneno());
//        type.setText(hotellist.get(position).getType());
//        place.setText(hotellist.get(position).getPlace());
//        return view;
//}
    /*@NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.hotel_info,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        user= FirebaseAuth.getInstance().getCurrentUser();
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
            phone=itemView.findViewById(R.id.phoneno1);
            type=itemView.findViewById(R.id.hoteltype);
            place=itemView.findViewById(R.id.hotelplace);
        }
    }
*/
//}
}


