package com.example.healthlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity12 extends AppCompatActivity {
    EditText ed11,ed22,ed33,ed44 ;
    MediaPlayer m1 ;
    Button sh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        ed11 = findViewById(R.id.TextUname25) ;
        ed22 = findViewById(R.id.TextPlace) ;
        ed33 = findViewById(R.id.Textspecial100) ;
        ed44 = findViewById(R.id.TextContact100) ;
        sh = findViewById(R.id.buttonadd500) ;

        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity12.this,MainActivity6.class) ;
                startActivity(intent);
            }
        });
    }
    public void process2(View view) {
        m1 = MediaPlayer.create(this, R.raw.click);

        String n11 = ed11.getText().toString() ;
        String l11 = ed22.getText().toString() ;
        String h11 = ed33.getText().toString() ;
        String e11 = ed44.getText().toString() ;

        DataHolder2 obj1 = new DataHolder2(n11,l11,h11,e11) ;

        FirebaseDatabase fd1 = FirebaseDatabase.getInstance() ;
        DatabaseReference node2 = fd1.getReference("SPH") ;
        m1.start();
        if(n11.isEmpty())
        {
            ed11.setError("Enter Doctor's name!");
            ed11.requestFocus() ;
        }
        else if(l11.isEmpty())
        {
            ed22.setError("Enter Place!");
            ed22.requestFocus() ;
        }
        else if(h11.isEmpty())
        {
            ed33.setError("Enter Specialized in!");
            ed33.requestFocus() ;
        }
        else if(e11.isEmpty())
        {
            ed44.setError("Enter Contact no!");
            ed44.requestFocus() ;
        }
        else
        {
            //node2.child(e11).setValue(obj1) ;

            ed11.setText("");
            ed22.setText("");
            ed33.setText("");
            ed44.setText("");
            node2.push().setValue(obj1) ;
            Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
    }
}

}