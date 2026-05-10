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

public class MainActivity14 extends AppCompatActivity {

    EditText nameEditText, emailEditText, locationEditText, hotlineEditText;
    Button updateButton, showButton;
    DatabaseReference databaseReference;

    MediaPlayer m1,m2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        nameEditText = findViewById(R.id.TextUsername26);
        emailEditText = findViewById(R.id.TextlPlace2);
        locationEditText = findViewById(R.id.Textspecial2);
        hotlineEditText = findViewById(R.id.TextContact2);

        updateButton = findViewById(R.id.buttonupdate2);
        showButton = findViewById(R.id.show111);

        databaseReference = FirebaseDatabase.getInstance().getReference("SPH");

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
                startActivity(new Intent(MainActivity14.this, MainActivity6.class));
                m2.start();
            }
        });
    }

    private void updateData() {
        final String dname = nameEditText.getText().toString().trim();
        final String chamber = emailEditText.getText().toString().trim();
        final String special = locationEditText.getText().toString().trim();
        final String contact = hotlineEditText.getText().toString().trim();

        // Check if any of the fields are empty
        if (dname.isEmpty() || chamber.isEmpty() || special.isEmpty() || contact.isEmpty()) {
            Toast.makeText(MainActivity14.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SPH");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DataHolder2 dataHolder2 = snapshot.getValue(DataHolder2.class);
                    // Match by doctor name only, then update all other fields
                    if (dataHolder2 != null && dataHolder2.getDname().equalsIgnoreCase(dname)) {
                        snapshot.getRef().child("place").setValue(chamber);
                        snapshot.getRef().child("special").setValue(special);
                        snapshot.getRef().child("contact").setValue(contact);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    Toast.makeText(MainActivity14.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    nameEditText.setText("");
                    emailEditText.setText("");
                    locationEditText.setText("");
                    hotlineEditText.setText("");
                    m1.start();
                } else {
                    Toast.makeText(MainActivity14.this, "Data not found", Toast.LENGTH_SHORT).show();
                    nameEditText.setText("");
                    emailEditText.setText("");
                    locationEditText.setText("");
                    hotlineEditText.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity14.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
