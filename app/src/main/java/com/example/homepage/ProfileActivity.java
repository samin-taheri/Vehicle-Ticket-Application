package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    CharSequence[] items = {"Euro", "Dollar", "Pound", "Yen", "Won", "TL", "Yuan", "Rouble"};
    boolean[] selectedItems = {false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Hello...");

        Button btnCurrency = findViewById(R.id.buttonCurrency);
        btnCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setTitle("Currency");
                alertDialogBuilder.setMultiChoiceItems(items, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selectedItems[which] = isChecked;
                    }
                });
                alertDialogBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProfileActivity.this, "Currency has been selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
            }
        });

        Button btnCustomerService = findViewById(R.id.buttonCustomerService);
        btnCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CustomerServiceIntent = new Intent(ProfileActivity.this, ContactWithEmailActivity.class);
                startActivity(CustomerServiceIntent);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        Button btnLogout = findViewById(R.id.main_LogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent profileIntent = new Intent(ProfileActivity.this, LoginActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(profileIntent);

                }
        });
        /*
        Button btnCardInfo = findViewById(R.id.button_CardInfo);
        btnCardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CardInfoIntent = new Intent(ProfileActivity.this, CardFormActivity.class);
                startActivity(CardInfoIntent);
            }
        });


         */
    }
    private String itemsToString(){
        String text = "";
        for (int i = 0; i < selectedItems.length; i ++){
            if(selectedItems[i]){
                text = text + items[i] + " ";
            }
        }
        return text.trim();
    }

    protected void onStart() {
        super.onStart();
/*
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()){
                            User user = task.getResult().getValue(User.class);
                            TextView textViewUser = (TextView) findViewById(R.id.main_textViewWelcome);
                            textViewUser.setText("Welcome, " + user.name);
                        }else {
                            System.err.println(task.getException().getMessage());
                        }
                    }
                });

 */
    }

    /*
    public void logout(View view) {

        mAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Are you sure you want to sign out" );
        dialog.setPositiveButton("Sign out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
        finish();

    }


     */
    public void navigateLogin(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }

    public void navigateRegister(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
    public void navigateSettings(View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.ic_baseline_search_24:
                    Intent intent0 = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent0);
                    break;
                case R.id.ic_baseline_flight_takeoff_24:
                    Intent intent1 = new Intent(ProfileActivity.this, BookingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ic_baseline_person_24:
                    break;
            }
            return true;
        }
    };
}
