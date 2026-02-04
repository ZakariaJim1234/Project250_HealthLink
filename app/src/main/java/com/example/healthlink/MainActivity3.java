package com.example.healthlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    Button b1,b2 ;
    MediaPlayer m1,m2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        m1 = MediaPlayer.create(this, R.raw.click);
        m2 = MediaPlayer.create(this, R.raw.click);
        b1 = findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the next activity class
                Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
                // Start the next activity
                startActivity(intent);
                m1.start();
            }
        });
        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Define the next activity class
                Intent intent2 = new Intent(MainActivity3.this, MainActivity6.class);
                // Start the next activity
                startActivity(intent2);
                m2.start();
            }
        });
    }
}