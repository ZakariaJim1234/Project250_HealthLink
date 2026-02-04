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

import java.util.UUID;

public class MainActivity11 extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4 ;
    MediaPlayer m2 ;

    Button sh2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        ed1 = findViewById(R.id.TextUsername22) ;
        ed3 = findViewById(R.id.Textlocation) ;
        ed4 = findViewById(R.id.Texthotline) ;
        ed2 = findViewById(R.id.Textemail) ;
        sh2 = findViewById(R.id.show10) ;

        sh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity11.this,MainActivity5.class) ;
                startActivity(intent);
            }
        });
    }

    public void process(View view) {
        m2 = MediaPlayer.create(this, R.raw.click);

        String n1 = ed1.getText().toString().trim() ;
        String e1 = ed2.getText().toString().trim() ;
        String l1 = ed3.getText().toString().trim() ;
        String h1 = ed4.getText().toString().trim() ;

        String uniqueID = UUID.randomUUID().toString();

        DataHolder obj = new DataHolder(uniqueID,n1,e1,l1,h1) ;

        FirebaseDatabase fd = FirebaseDatabase.getInstance() ;
        DatabaseReference node = fd.getReference("NLH") ;

        m2.start();
        if(n1.isEmpty())
        {
            ed1.setError("Enter Name!");
            ed1.requestFocus() ;
        }
        else if(e1.isEmpty())
        {
            ed2.setError("Enter Email!");
            ed2.requestFocus() ;
        }
        else if(l1.isEmpty())
        {
            ed3.setError("Enter Location!");
            ed3.requestFocus() ;
        }
        else if(h1.isEmpty())
        {
            ed4.setError("Enter Hotline!");
            ed4.requestFocus() ;
        }
        else
        {
            //node.child(n1).child(l1).setValue(obj) ;
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");

            node.push().setValue(obj) ;
            Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(MainActivity11.this,MainActivity5.class));
        }

    }
}