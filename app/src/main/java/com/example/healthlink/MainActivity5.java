package com.example.healthlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    RecyclerView recyclerView2;
    ArrayList<DataHolder> list2;
    DatabaseReference df2;

    myadapter3 adapter3;

    SearchView searchView2 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        searchView2 = findViewById(R.id.search2) ;
        searchView2.clearFocus();
        diplayitems() ;

    }

    private void diplayitems() {
        recyclerView2 =findViewById(R.id.recycle2);
        df2 = FirebaseDatabase.getInstance().getReference("NLH");
        list2 = new ArrayList<>();
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter3 = new myadapter3(list2,this) ;
        recyclerView2.setAdapter(adapter3);

        df2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list2.clear();
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        DataHolder datalist2 = datasnapshot.getValue(DataHolder.class);
                        list2.add(datalist2);
                    }
                    adapter3.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchlist(newText);
                return false;
            }
        });
    }
    public void searchlist(String text)
    {
        ArrayList<DataHolder> searchList = new ArrayList<>() ;
        for(DataHolder d : list2)
        {
            if(d.getName().toLowerCase().contains(text.toLowerCase()) || d.getLocation().toLowerCase().contains(text.toLowerCase()))
            {
                searchList.add(d) ;
            }
        }
        adapter3.searchdatalist(searchList);
    }
}
