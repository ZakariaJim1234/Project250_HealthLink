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

public class MainActivity15 extends AppCompatActivity {

    EditText textUsername, textUserLocation;
    Button buttonDelete, show;

    DatabaseReference databaseReference;

    MediaPlayer m1,m2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);

        textUsername = findViewById(R.id.TextUsername30);
        textUserLocation = findViewById(R.id.TextUserlocation30);
        buttonDelete = findViewById(R.id.buttondelete);
        show = findViewById(R.id.show11); // Correct initialization for 'show' button

        // Get reference to Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("NLH");

        m1 = MediaPlayer.create(this, R.raw.click);
        m2 = MediaPlayer.create(this, R.raw.click);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameToDelete = textUsername.getText().toString().toLowerCase().trim();
                String locationToDelete = textUserLocation.getText().toString().toLowerCase().trim(); // Get location input

                // Check if both name and location are provided
                if (!nameToDelete.isEmpty() && !locationToDelete.isEmpty()) {
                    deleteData(nameToDelete, locationToDelete);
                } else {
                    Toast.makeText(MainActivity15.this, "Please enter both name and location to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity15.this, MainActivity5.class);
                startActivity(intent);
                m2.start();
            }
        });
    }

    private void deleteData(final String name, final String location) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean dataFound = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataHolder data = snapshot.getValue(DataHolder.class);
                    if (data != null && data.getName().toLowerCase().equals(name) && data.getLocation().toLowerCase().equals(location)) {
                        snapshot.getRef().removeValue();
                        dataFound = true;
                    }
                }
                if (dataFound) {
                    Toast.makeText(MainActivity15.this, "Data for " + name + " at location " + location + " deleted successfully", Toast.LENGTH_SHORT).show();
                    textUsername.setText("");
                    textUserLocation.setText("");
                    m1.start();
                } else {
                    Toast.makeText(MainActivity15.this, "No data found for " + name + " at location " + location, Toast.LENGTH_SHORT).show();
                    textUsername.setText("");
                    textUserLocation.setText("");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity15.this, "Failed to delete data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
