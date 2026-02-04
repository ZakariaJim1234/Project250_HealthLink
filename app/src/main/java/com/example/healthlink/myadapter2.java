package com.example.healthlink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter2 extends FirebaseRecyclerAdapter<DataHolder2,myadapter2.myviewholder> {

    public myadapter2(@NonNull FirebaseRecyclerOptions<DataHolder2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull DataHolder2 DataHolder2) {
        holder.DN.setText(DataHolder2.getDname());
        holder.CH.setText(DataHolder2.getPlace());
        holder.Sp.setText(DataHolder2.getSpecial());
        holder.CT.setText(DataHolder2.getContact());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userty2,parent,false) ;
        return new myviewholder(v) ;
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView DN , CH , Sp , CT ;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            DN = (TextView)itemView.findViewById(R.id.textN2) ;
            CH = (TextView)itemView.findViewById(R.id.textN4) ;
            Sp = (TextView)itemView.findViewById(R.id.textN6) ;
            CT = (TextView)itemView.findViewById(R.id.textN8) ;
        }
    }
}
