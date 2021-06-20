package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Forgot password?");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ResetPassword(View view){
        TextView textViewReset =(TextView)findViewById(R.id.ResetPassword_TextView);
        String text = textViewReset.getText().toString();

        EditText editTextEmail =(EditText)findViewById(R.id.ResetPassword_Email);
        String email = editTextEmail.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(ResetPasswordActivity.this, "Please write your Email address first...", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPasswordActivity.this, "Please check your Email account, If you want to reset your password...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                    }
                    else{
                        String message = task.getException().getMessage();
                        Toast.makeText(ResetPasswordActivity.this, "Error occurred: "+ message, Toast.LENGTH_SHORT).show();

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
                    Intent intent3 = new Intent(ResetPasswordActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.ic_baseline_flight_takeoff_24:
                    Intent intent1 = new Intent(ResetPasswordActivity.this, BookingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ic_baseline_person_24:
                    Intent intent2 = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                    startActivity(intent2);
                    break;
            }
            return true;
        }
    };

}
