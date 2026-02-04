package com.example.healthlink;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import java.util.Timer ;
import java.util.TimerTask;
import android.widget.TextView ;


public class MainActivity extends AppCompatActivity {
    Timer timer ;
    MediaPlayer media ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = new Timer() ;
        TextView myTextView = findViewById(R.id.text) ;

        // Example: Move the text horizontally
        // Example: Fade in and fade out the text
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(myTextView, "alpha", 0f, 1f);
        fadeIn.setDuration(2000); // set the duration in milliseconds

        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(myTextView, "alpha", 1f, 0f);
        fadeOut.setDuration(2000); // set the duration in milliseconds

        // Chain the fade-in and fade-out animations
        fadeIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fadeOut.start();
            }
        });

        // Start the animation
        fadeIn.start();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class) ;
                startActivity(intent);
                finish();
            }
        },4000);
        media = MediaPlayer.create(this, R.raw.music);

        // Start playing the tone
        media.start();
    }
}