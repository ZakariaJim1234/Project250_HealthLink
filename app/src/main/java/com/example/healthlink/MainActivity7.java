package com.example.healthlink;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity7 extends AppCompatActivity {
    EditText reg_email, reg_pass;
    TextView t1;
    Button b2;
    MediaPlayer m1, m2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        t1 = findViewById(R.id.textViewSignUpLink);
        reg_email = findViewById(R.id.editTextUsername);
        reg_pass = findViewById(R.id.editTextPassword);
        b2 = findViewById(R.id.HELLOLogin);
        m1 = MediaPlayer.create(this, R.raw.click);
        m2 = MediaPlayer.create(this, R.raw.click);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity7.this, MainActivity4.class));
                m1.start();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performlogin();
            }
        });
    }

    private void performlogin() {
        String email = reg_email.getText().toString();
        String password = reg_pass.getText().toString();

        if (!email.matches(emailPattern)) {
            reg_email.setError("Enter correct email");
            reg_email.requestFocus();
        } else if (password.isEmpty() || password.length() < 6) {
            reg_pass.setError("Enter correct password");
            reg_pass.requestFocus();
        } else {
            progressDialog.setMessage("Kindly Wait While Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity() ;
                        Toast.makeText(MainActivity7.this,"Login Successfully",Toast.LENGTH_SHORT).show() ;
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity7.this,"Enter correct password",Toast.LENGTH_SHORT).show() ;
                    }
                }
            }) ;
        }
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity7.this,MainActivity8.class) ;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK) ;
        startActivity(intent);
        m2.start();
    }
}