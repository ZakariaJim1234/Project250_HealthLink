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

public class MainActivity4 extends AppCompatActivity {

    EditText reg_name , reg_email , reg_pass , reg_con ;
    TextView t1 ;
    Button b1 ;
    MediaPlayer m1,m2 ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+" ;
    ProgressDialog progressDialog ;
    FirebaseAuth mAuth ;
    FirebaseUser mUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        t1 = findViewById(R.id.textViewLoginLink);
        reg_name = findViewById(R.id.TextUsername);
        reg_email = findViewById(R.id.TextEmail);
        reg_pass = findViewById(R.id.TextPassword);
        reg_con = findViewById(R.id.TextUserconfirm);
        b1 = findViewById(R.id.buttonSignUp);
        m1 = MediaPlayer.create(this, R.raw.click);
        m2 = MediaPlayer.create(this, R.raw.click);
        progressDialog = new ProgressDialog(this) ;
        mAuth = FirebaseAuth.getInstance() ;
        mUser = mAuth.getCurrentUser() ;

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this, MainActivity7.class));
                m1.start();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              performauth();
            }
        });
    }
            private void performauth() {
                String email = reg_email.getText().toString() ;
                String password = reg_pass.getText().toString() ;
                String confirmPassword = reg_con.getText().toString() ;

                if(!email.matches(emailPattern))
                {
                    reg_email.setError("Enter correct email");
                    reg_email.requestFocus() ;
                }
                else if(password.isEmpty() || password.length()<6)
                {
                    reg_pass.setError("Password must be at least 6 characters!");
                    reg_pass.requestFocus() ;
                }
                else if(!password.equals(confirmPassword))
                {
                    reg_con.setError("Doesn't match both password!");
                    reg_con.requestFocus() ;
                }
                else
                {
                    progressDialog.setMessage("Kindly Wait While Registration...");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendUserToNextActivity() ;
                                Toast.makeText(MainActivity4.this,"Registration Successfully",Toast.LENGTH_SHORT).show() ;

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity4.this,""+task.getException(),Toast.LENGTH_SHORT).show() ;
                            }
                        }
                    }) ;
                }
            }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity4.this,MainActivity7.class) ;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK) ;
        startActivity(intent);
        m2.start();
    }
}
