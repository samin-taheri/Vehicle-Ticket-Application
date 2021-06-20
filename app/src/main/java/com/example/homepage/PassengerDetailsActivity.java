package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import dto.User;

public class PassengerDetailsActivity extends AppCompatActivity {
    private String startCity,endCity,date,startTime,arrivalTime,companyName,ticketPrice;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatePickerDialog.OnDateSetListener mDate;
    private static final String TAG ="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Details...");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(getIntent().hasExtra("startCity")){
            startCity = getIntent().getStringExtra("startCity");
        }
        if(getIntent().hasExtra("endCity")){
            endCity = getIntent().getStringExtra("endCity");
        }
        if(getIntent().hasExtra("startCity")){
            date = getIntent().getStringExtra("date");
        }
        if(getIntent().hasExtra("startCity")){
            startTime = getIntent().getStringExtra("startTime");
        }
        if(getIntent().hasExtra("startCity")){
            arrivalTime = getIntent().getStringExtra("arrivalTime");
        }
        if(getIntent().hasExtra("startCity")){
            ticketPrice = getIntent().getStringExtra("price");
        }
        if(getIntent().hasExtra("startCity")){
            companyName = getIntent().getStringExtra("companyName");
        }


        Button button_register = findViewById(R.id.register_Button_p);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(PassengerDetailsActivity.this, CardFormActivity.class);
                intent3.putExtra("startCity",startCity);
                intent3.putExtra("endCity",endCity);
                intent3.putExtra("date",date);
                intent3.putExtra("startTime",startTime);
                intent3.putExtra("arrivalTime",arrivalTime);
                intent3.putExtra("price",ticketPrice);
                intent3.putExtra("companyName",companyName);
                startActivity(intent3);
            }
        });

        EditText mDisplayDate =(EditText)findViewById(R.id.register_birthday_p);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PassengerDetailsActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDate, year, month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG,"onDateSet: mm/dd/yyy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }


    public void navigateToLogin(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void createUser2(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.register_editTextEmail_p);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.register_editTextPassword_p);
        String password = editTextPassword.getText().toString();

        EditText editTextConfirmPassword = (EditText) findViewById(R.id.register_editTextConfirmPassword_p);
        String confirmPassword = editTextConfirmPassword.getText().toString();

        EditText editTextName = (EditText) findViewById(R.id.register_name_p);
        String name = editTextName.getText().toString();

        EditText editTextBirthday = (EditText) findViewById(R.id.register_birthday_p);
        String birthday = editTextBirthday.getText().toString();

        EditText editTextPhone = (EditText) findViewById(R.id.register_phone_p);
        String phone = editTextPhone.getText().toString();

        EditText editTextPassport = (EditText) findViewById(R.id.register_passport_p);
        String passport = editTextPassport.getText().toString();

        EditText editTextAddress = (EditText) findViewById(R.id.register_address_p);
        String address = editTextAddress.getText().toString();

        User userInfo = new User(name, birthday, phone, passport, address);

        // User userInfo = new User(name);

        if (!password.equals(confirmPassword)){
            Toast.makeText(PassengerDetailsActivity.this, "Passwords don't match.", Toast.LENGTH_SHORT).show();
        }else{
            Intent mainIntent = new Intent(this, ProfileActivity.class);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("users").child(user.getUid()).setValue(userInfo);
                        Toast.makeText(PassengerDetailsActivity.this, "Registration has been successful", Toast.LENGTH_SHORT).show();
                        startActivity(mainIntent);
                    }else{
                        Toast.makeText(PassengerDetailsActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
}