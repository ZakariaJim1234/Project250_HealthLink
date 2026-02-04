package com.example.healthlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity16 extends AppCompatActivity {

    EditText textUserDoctor;
    Button buttonDelete, show;

    DatabaseReference databaseReference;

    MediaPlayer m1,m2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        textUserDoctor = findViewById(R.id.etx20);

        buttonDelete = findViewById(R.id.buttondelete2);
        show = findViewById(R.id.show12); // Correct initialization for 'show' button

        // Get reference to Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("SPH");
        m1 = MediaPlayer.create(this, R.raw.click);
        m2 = MediaPlayer.create(this, R.raw.click);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameToDelete =  textUserDoctor .getText().toString().toLowerCase().trim();
                // Check if both name and location are provided
                if (!nameToDelete.isEmpty()) {
                    deleteData(nameToDelete);
                } else {
                    Toast.makeText(MainActivity16.this, "Please enter Doctor's name to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity16.this, MainActivity6.class);
                startActivity(intent);
                m2.start();
            }
        });
    }

    private void deleteData(final String name) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean dataFound = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataHolder2 data = snapshot.getValue(DataHolder2.class);
                    if (data != null && data.getDname().toLowerCase().equals(name)) {
                        snapshot.getRef().removeValue();
                        dataFound = true;
                    }
                }
                if (dataFound) {
                    Toast.makeText(MainActivity16.this, "Data for " + name + " deleted successfully", Toast.LENGTH_SHORT).show();
                    textUserDoctor.setText("");
                    m1.start() ;
                } else {
                    Toast.makeText(MainActivity16.this, "No data found for " + name , Toast.LENGTH_SHORT).show();
                    textUserDoctor.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity16.this, "Failed to delete data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
