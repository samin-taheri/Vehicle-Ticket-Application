package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TermsOfServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_service);
        getSupportActionBar().setTitle("Terms of service");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}