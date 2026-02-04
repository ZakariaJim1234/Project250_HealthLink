package com.example.healthlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DataHolder2> list;
    DatabaseReference df;

    myadapter adapter;

    SearchView searchView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        searchView = findViewById(R.id.search22) ;
        searchView.clearFocus();

       diplayitems() ;
       //displayitems2() ;
    }

    /*
    private void displayitems2() {
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<DataHolder2>options =
                new FirebaseRecyclerOptions.Builder<DataHolder2>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("SPH"),DataHolder2.class)
                        .build() ;
        adapter2 = new myadapter2(options) ;
        recyclerView.setAdapter(adapter2);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter2.stopListening();
    }
    */
    private void diplayitems() {
        recyclerView =findViewById(R.id.recycle);
        df = FirebaseDatabase.getInstance().getReference("SPH");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myadapter(this, list);
        recyclerView.setAdapter(adapter);

        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DataHolder2 datalist = datasnapshot.getValue(DataHolder2.class);
                        list.add(datalist);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchlist2(newText);
                return false;
            }
        });
    }
    public void searchlist2(String text)
    {
        ArrayList<DataHolder2> searchList2 = new ArrayList<>() ;
        for(DataHolder2 d2 : list)
        {
            if(d2.getDname().toLowerCase().contains(text.toLowerCase()) || d2.getPlace().toLowerCase().contains(text.toLowerCase()) || d2.getSpecial().toLowerCase().contains(text.toLowerCase()))
            {
                searchList2.add(d2) ;
            }
        }
        adapter.searchdatalist(searchList2);
    }
}