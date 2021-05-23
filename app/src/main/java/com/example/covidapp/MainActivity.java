package com.example.covidapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.usertxt);
        TextView tv1 = findViewById(R.id.pswdtxt);

        Button btn = findViewById(R.id.btnlogin);
        Button btn2 = findViewById(R.id.btnmail);

        fAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = tv.getText().toString().trim();
                String pw = tv1.getText().toString().trim();

                if(TextUtils.isEmpty(un)){
                    tv.setError("Enter E-Mail");
                    return;
                }
                if(TextUtils.isEmpty(pw)) {
                    tv1.setError("Enter Password");
                    return;
                }
                if(pw.length()<6){
                    tv1.setError("Length should be greater than 6");
                    return;
                }

                fAuth.signInWithEmailAndPassword(un,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Successfully!!",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,Mail.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error Occured!!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, com.example.covidapp.Registration.class);
                startActivity(i);
            }
        });
    }
}