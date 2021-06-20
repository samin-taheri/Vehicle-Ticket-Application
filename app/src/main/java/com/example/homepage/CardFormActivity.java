package com.example.homepage;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.example.homepage.R;

public class CardFormActivity extends AppCompatActivity {
    private String startCity,endCity,date,startTime,arrivalTime,companyName,ticketPrice;

    CardForm cardForm;
    Button btnPay;
    AlertDialog.Builder alertBuilder;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Card Info");
        setContentView(R.layout.activity_card_form);
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


        cardForm = findViewById(R.id.card_form);
        btnPay = findViewById(R.id.btn_pay);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(CardFormActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardForm.isValid()){
                    alertBuilder = new AlertDialog.Builder(CardFormActivity.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n"+
                            "Card CVV: " + cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode() + "\n" +
                            "Phone number: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(CardFormActivity.this, "Thank you for purchase", Toast.LENGTH_SHORT).show();
                            Intent goToTicketInfo = new Intent(CardFormActivity.this,TicketInformation.class);

                            goToTicketInfo.putExtra("startCity",startCity);
                            goToTicketInfo.putExtra("endCity",endCity);
                            goToTicketInfo.putExtra("date",date);
                            goToTicketInfo.putExtra("startTime",startTime);
                            goToTicketInfo.putExtra("arrivalTime",arrivalTime);
                            goToTicketInfo.putExtra("price",ticketPrice);
                            goToTicketInfo.putExtra("companyName",companyName);

                            startActivity(goToTicketInfo);
                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(CardFormActivity.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}