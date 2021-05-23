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

public class Registration extends AppCompatActivity {

    String confirm,un,pw;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fAuth = FirebaseAuth.getInstance();

        TextView tv = findViewById(R.id.uname);
        TextView tv1 = findViewById(R.id.passtxt);
        TextView tv2 = findViewById(R.id.confirmtxt);

        Button btn = findViewById(R.id.btnregister);
        Button btn1 = findViewById(R.id.backbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un = tv.getText().toString().trim();
                pw = tv1.getText().toString().trim();
                confirm = tv2.getText().toString().trim();

                if(TextUtils.isEmpty(un)){
                    tv.setError("Enter E-Mail");
                    return;
                }
                if(TextUtils.isEmpty(pw)){
                    tv1.setError("Enter Password");
                    return;
                }
                if(pw.length()<6){
                    tv1.setError("Length should be greater than 6");
                    return;
                }
                    fAuth.createUserWithEmailAndPassword(un, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registration.this, "Registered Successfully!!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Registration.this, Mail.class));
                            } else {
                                Toast.makeText(Registration.this, "Error Occured!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                tv1.setText("");
                tv2.setText("");
            }
        });
    }
}