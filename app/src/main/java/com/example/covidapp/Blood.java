package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Blood extends AppCompatActivity {

    TextView fbname,lbname,address,group,number,mail;
    Button submit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String fname,lname,add,bgrp,mno,mailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        fbname = findViewById(R.id.fbname);
        lbname = findViewById(R.id.lbname);
        address = findViewById(R.id.address);
        group = findViewById(R.id.group);
        number = findViewById(R.id.number);
        mail = findViewById(R.id.mailid);

        submit = findViewById(R.id.submitb);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Blood Patients");

        submit.setOnClickListener(v -> {
            fname = fbname.getText().toString().trim();
            lname = lbname.getText().toString().trim();
            add = address.getText().toString().trim();
            bgrp = group.getText().toString().trim();
            mno = number.getText().toString().trim();
            mailid = mail.getText().toString().trim();

            if(TextUtils.isEmpty(fname)){
                fbname.setError("First Name Required!!");
                return;
            }
            if(TextUtils.isEmpty(lname)){
                lbname.setError("Last Name Required!!");
                return;
            }if(TextUtils.isEmpty(add)){
                address.setError("Address Required!!");
                return;
            }
            if(TextUtils.isEmpty(bgrp)){
                group.setError("Blood group Required!!");
                return;
            }
            if(TextUtils.isEmpty(mno)){
                number.setError("Mobile Number Required!!");
                return;
            }
            if(TextUtils.isEmpty(mailid)){
                mail.setError("E-Mail ID Required!!");
                return;
            }

            Donation dna = new Donation(fname,lname,add,bgrp,mno,mailid);
            myRef.push().setValue(dna, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    Toast.makeText(Blood.this,"Data saved successfully!!",Toast.LENGTH_LONG).show();
                }
            });

            Log.i("Send email", "");
            String[] TO = {"vaishnavijarange@gmail.com"};
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Blood Donation");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "User wants to donate blood");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I want to donate blood kindly contact me through mail!!"
                    +"Full Name:-"+fname+"Last Name:-"+lname+"Address"+add+"Blood Group"+bgrp+"mobile No."+mno);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                Log.i("Finished sending email...", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Blood.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}