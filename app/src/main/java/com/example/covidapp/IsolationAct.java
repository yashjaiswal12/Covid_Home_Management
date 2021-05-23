package com.example.covidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IsolationAct extends AppCompatActivity {

    TextView oxy,bp,temp,day;
    Button back,update;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String ox,bpm,tempe,dy,rd1;
    RadioButton btn,btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isolation);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Patients");

        oxy = findViewById(R.id.oxygen);
        bp = findViewById(R.id.bpm);
        temp = findViewById(R.id.temperature);
        day = findViewById(R.id.quarantine);

        update = findViewById(R.id.update);
        back = findViewById(R.id.back);

        btn = findViewById(R.id.better);
        btn1 = findViewById(R.id.worst);
        btn2 = findViewById(R.id.same);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ox = oxy.getText().toString().trim();
                bpm = bp.getText().toString().trim();
                tempe = temp.getText().toString().trim();
                dy = day.getText().toString().trim();

                if(TextUtils.isEmpty(ox)){
                    oxy.setError("Oxygen Level Required!!");
                    return;
                }
                if(TextUtils.isEmpty(bpm)){
                    bp.setError("BPM Level Required!!");
                    return;
                }
                if(TextUtils.isEmpty(tempe)){
                    temp.setError("Temperature Required!!");
                    return;
                }
                if(TextUtils.isEmpty(dy)){
                    day.setError("No. of days Required!!");
                    return;
                }

                if(btn.isSelected()){
                    rd1 = (String) btn.getText();
                }
                else if(btn1.isSelected()){
                    rd1 = (String) btn1.getText();
                }
                else{
                    rd1 = (String) btn2.getText();
                }

                Isolation iso = new Isolation(ox,bpm,tempe,dy,rd1);
                myRef.push().setValue(iso, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        Toast.makeText(IsolationAct.this,"Data saved successfully!!",Toast.LENGTH_LONG).show();
                    }
                });

                if(Integer.parseInt(ox)<=90 || Integer.parseInt(bpm)<=50 || Integer.parseInt(bpm)>=120){
                    Log.i("Send email", "");
                    String[] TO = {"vaishnavijarange@gmail.com"};
                    String[] CC = {""};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Patients Health regarding");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Oxygen level of patient is falling down kindly take further actions!!");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                        Log.i("Finished sending email...", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(IsolationAct.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IsolationAct.this,Mail.class);
                startActivity(i);
            }
        });
    }
}