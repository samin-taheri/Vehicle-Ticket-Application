package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import dto.User;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatePickerDialog.OnDateSetListener mDate;
    private static final String TAG ="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Don't have an account?");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        EditText mDisplayDate =(EditText)findViewById(R.id.register_birthday);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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

    public void createUser(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.register_editTextEmail);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.register_editTextPassword);
        String password = editTextPassword.getText().toString();

        EditText editTextConfirmPassword = (EditText) findViewById(R.id.register_editTextConfirmPassword);
        String confirmPassword = editTextConfirmPassword.getText().toString();

        EditText editTextName = (EditText) findViewById(R.id.register_name);
        String name = editTextName.getText().toString();

        EditText editTextBirthday = (EditText) findViewById(R.id.register_birthday);
        String birthday = editTextBirthday.getText().toString();

        EditText editTextPhone = (EditText) findViewById(R.id.register_phone);
        String phone = editTextPhone.getText().toString();

        EditText editTextPassport = (EditText) findViewById(R.id.register_passport);
        String passport = editTextPassport.getText().toString();

        EditText editTextAddress = (EditText) findViewById(R.id.register_address);
        String address = editTextAddress.getText().toString();

        User userInfo = new User(name, birthday, phone, passport, address);

        // User userInfo = new User(name);

        if (!password.equals(confirmPassword)){
            Toast.makeText(RegisterActivity.this, "Passwords don't match.", Toast.LENGTH_SHORT).show();
        }else{
            Intent mainIntent = new Intent(this, ProfileActivity.class);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("users").child(user.getUid()).setValue(userInfo);
                        Toast.makeText(RegisterActivity.this, "Registration has been successful", Toast.LENGTH_SHORT).show();
                        startActivity(mainIntent);
                    }else{
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_baseline_search_24:
                    Intent intent3 = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.ic_baseline_flight_takeoff_24:
                    Intent intent1 = new Intent(RegisterActivity.this, BookingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ic_baseline_person_24:
                    //Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
                    //startActivity(intent2);
                    break;
            }
            return true;
        }
    };
}