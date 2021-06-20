package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dto.Service;
import dto.User;

public class CustomerServiceActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        getSupportActionBar().setTitle("Customer service");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    /*
    public void createCustomerService(View view){
        EditText serviceEmail = findViewById(R.id.service_email);
        String editTextEmail = serviceEmail.getText().toString();

        EditText serviceName = findViewById(R.id.service_name);
        String editTextName = serviceName.getText().toString();

        EditText servicePhone = findViewById(R.id.service_phoneNumber);
        String editTextPhone = servicePhone.getText().toString();

        EditText serviceTopic = findViewById(R.id.service_topic);
        String editTextTopic= serviceTopic.getText().toString();

        EditText serviceCompany = findViewById(R.id.service_travelCompany);
        String editTextCompany = serviceCompany.getText().toString();

        EditText serviceSubject = findViewById(R.id.service_subject);
        String editTextSubject = serviceSubject.getText().toString();

        EditText serviceDescription = findViewById(R.id.service_description);
        String editTextDescription = serviceDescription.getText().toString();

        Service serviceInfo = new Service(editTextEmail, editTextName, editTextPhone, editTextTopic, editTextCompany, editTextSubject, editTextDescription);

        Intent mainIntent = new Intent(this, ProfileActivity.class);
        Button btnSubmit = findViewById(R.id.button_submit);

    }

     */
}