package com.example.vehicleticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mAuth = FirebaseAuth.getInstance();
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

}