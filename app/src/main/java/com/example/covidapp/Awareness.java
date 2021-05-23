package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Awareness extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);

        ViewPager mViewPager = findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);

        TextView linkTextView = findViewById(R.id.more);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView1 = findViewById(R.id.more2);
        linkTextView1.setMovementMethod(LinkMovementMethod.getInstance());
    }
}