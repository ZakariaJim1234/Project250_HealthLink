package com.example.healthlink;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder>{
    Context context ;
    ArrayList<DataHolder2> list ;

    public myadapter(Context context , ArrayList<DataHolder2> list) {
        this.context = context;
        this.list = list ;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userty2,parent,false) ;
        return new MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataHolder2 datah2 = list.get(position) ;
        holder.Name.setText(datah2.getDname());
        holder.Place.setText(datah2.getPlace());
        holder.Special.setText(datah2.getSpecial());
        holder.Contact.setText(datah2.getContact());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchdatalist(ArrayList<DataHolder2> searchlist2)
    {
        list = searchlist2 ;
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name,Place,Special,Contact ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.textN2) ;
            Place = itemView.findViewById(R.id.textN4) ;
            Special = itemView.findViewById(R.id.textN6) ;
            Contact = itemView.findViewById(R.id.textN8) ;

        }
    }
}