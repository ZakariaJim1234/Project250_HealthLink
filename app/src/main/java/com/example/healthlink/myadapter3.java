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


public class myadapter3 extends RecyclerView.Adapter<myadapter3.MyViewHolder>{
    Context context ;
    ArrayList<DataHolder> list ;

    public myadapter3(ArrayList<DataHolder> list,Context context) {
        this.list = list;
        this.context = context ;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userty,parent,false) ;
        return new MyViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataHolder datah = list.get(position) ;
        holder.Name2.setText(datah.getName());
        holder.Email.setText(datah.getEmail());
        holder.Location.setText(datah.getLocation());
        holder.Hotline.setText(datah.getHotline());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void searchdatalist(ArrayList<DataHolder> searchlist)
    {
        list = searchlist ;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name2,Location,Email,Hotline ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name2 = itemView.findViewById(R.id.textView2) ;
            Location = itemView.findViewById(R.id.textView6) ;
            Email = itemView.findViewById(R.id.textView4) ;
            Hotline = itemView.findViewById(R.id.textView8) ;

        }
    }
}
