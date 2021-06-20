package com.example.homepage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnRateUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        

        Button btnTermsOfService = findViewById(R.id.button_termsOfService);
        btnTermsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TermsOfServiceIntent = new Intent(SettingsActivity.this, TermsOfServiceActivity.class);
                startActivity(TermsOfServiceIntent);
            }
        });

        Button btnPrivacyPolicy = findViewById(R.id.button_privacyPolicy);
        btnPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PrivacyPolicyIntent = new Intent(SettingsActivity.this, PrivacyPolicyActivity.class);
                startActivity(PrivacyPolicyIntent);
            }
        });


        btnRateUs = (Button) findViewById(R.id.button_rateUs);
        btnRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Rate us!");
                builder.setMessage("How do you like the app?");
                builder.setNegativeButton("It needs work", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("I love it!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SettingsActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }
}