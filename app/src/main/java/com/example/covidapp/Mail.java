package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class Mail extends AppCompatActivity {

    ImageButton clinic,blood,log,faq,contact,aware,out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        clinic = findViewById(R.id.clinicbtn);
        blood = findViewById(R.id.bloodimg);
        log = findViewById(R.id.logimg);
        faq = findViewById(R.id.faqimg);
        contact = findViewById(R.id.contactimg);
        aware = findViewById(R.id.awarebtn);
        out = findViewById(R.id.outimg);

        out.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Mail.this, MainActivity.class));
            finish();
        });

        clinic.setOnClickListener(v -> {
            Intent i = new Intent(Mail.this,IsolationAct.class);
            startActivity(i);
        });
        blood.setOnClickListener(v -> {
            Intent i = new Intent(Mail.this,Blood.class);
            startActivity(i);
        });
        log.setOnClickListener(v -> {
            Intent i = new Intent(Mail.this,MainActivity.class);
            startActivity(i);
        });
        aware.setOnClickListener(v -> {
            Intent i = new Intent(Mail.this,Awareness.class);
            startActivity(i);
        });
        faq.setOnClickListener(v -> {
            Intent i = new Intent(Mail.this,FAQAct.class);
            startActivity(i);
        });
        contact.setOnClickListener(v -> {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "1234567890", null)));

        });
    }
}