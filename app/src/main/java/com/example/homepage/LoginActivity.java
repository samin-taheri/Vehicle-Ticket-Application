package com.example.homepage;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Already have an account?");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){
            Intent mainIntent = new Intent(this,LoginActivity.class);
            startActivity(mainIntent);
        }
    }

    public void navigateUser(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void navigateResetPassword(View view){
        Intent intent = new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.login_editTextEmail);
        String email = editTextEmail.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.login_editTextPassword);
        String password = editTextPassword.getText().toString();

        Intent mainIntent = new Intent(this, ProfileActivity.class);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(mainIntent);
                }else{
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_baseline_search_24:
                    Intent intent3 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.ic_baseline_flight_takeoff_24:
                    Intent intent1 = new Intent(LoginActivity.this, BookingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ic_baseline_person_24:
                    break;
            }
            return true;
        }
    };

}
