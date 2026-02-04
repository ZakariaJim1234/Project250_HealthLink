package com.example.healthlink;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.ValueEventListener;

public class MainActivity13 extends AppCompatActivity {

    EditText nameEditText, emailEditText, locationEditText, hotlineEditText;
    Button updateButton, showButton;
    DatabaseReference databaseReference;

    MediaPlayer m1, m2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        nameEditText = findViewById(R.id.TextUsername23);
        emailEditText = findViewById(R.id.Textemail10);
        locationEditText = findViewById(R.id.Textlocation2);
        hotlineEditText = findViewById(R.id.Texthotline2);

        updateButton = findViewById(R.id.buttonupdate);
        showButton = findViewById(R.id.show122);

        databaseReference = FirebaseDatabase.getInstance().getReference("NLH");

        m1 = MediaPlayer.create(this, R.raw.click);
        m2 = MediaPlayer.create(this, R.raw.click);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity13.this, MainActivity5.class));
                m2.start();
            }
        });
    }

    private void updateData() {
        final String name = nameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String location = locationEditText.getText().toString().trim();
        final String hotline = hotlineEditText.getText().toString().trim();

        // Check if any of the fields are empty
        if (name.isEmpty() || email.isEmpty() || location.isEmpty() || hotline.isEmpty()) {
            Toast.makeText(MainActivity13.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("NLH");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataHolder dataHolder = snapshot.getValue(DataHolder.class);
                    if (dataHolder != null && dataHolder.getName().equalsIgnoreCase(name)) {
                        // Check if other fields match too
                        if (dataHolder.getLocation().equalsIgnoreCase(location) ||
                                dataHolder.getEmail().equalsIgnoreCase(email) ||
                                dataHolder.getHotline().equalsIgnoreCase(hotline)) {
                            // Update the data
                            snapshot.getRef().child("email").setValue(email);
                            snapshot.getRef().child("location").setValue(location);
                            snapshot.getRef().child("hotline").setValue(hotline);
                            found = true;
                            break;
                        }
                    }
                    else if (dataHolder != null && dataHolder.getLocation().equalsIgnoreCase(location)) {
                        // Check if other fields match too
                        if (dataHolder.getName().equalsIgnoreCase(name) ||
                                dataHolder.getEmail().equalsIgnoreCase(email) ||
                                dataHolder.getHotline().equalsIgnoreCase(hotline)) {
                            // Update the data
                            snapshot.getRef().child("email").setValue(email);
                            snapshot.getRef().child("name").setValue(name);
                            snapshot.getRef().child("hotline").setValue(hotline);
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    Toast.makeText(MainActivity13.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    clearEditTextFields();
                    m1.start();
                } else {
                    Toast.makeText(MainActivity13.this, "Data not found", Toast.LENGTH_SHORT).show();
                    clearEditTextFields();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity13.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void clearEditTextFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        locationEditText.setText("");
        hotlineEditText.setText("");
    }
}