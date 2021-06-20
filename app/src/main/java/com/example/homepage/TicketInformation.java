package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicketInformation extends AppCompatActivity {
    private String startCity,endCity,date,startTime,arrivalTime,companyName,ticketPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Ticket Information");
        setContentView(R.layout.activity_ticket_information);
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

        TextView sc = (TextView) findViewById(R.id.info_start_city);
        sc.setText(startCity);

        TextView ec = (TextView) findViewById(R.id.info_end_city);
        ec.setText(endCity);

        TextView d = (TextView) findViewById(R.id.info_date);
        d.setText(date);

        TextView startingT = (TextView) findViewById(R.id.info_startingTime);
        startingT.setText(startTime);

        TextView arrivalT = (TextView) findViewById(R.id.info_arrivalTime);
        arrivalT.setText(arrivalTime);

        TextView price = (TextView) findViewById(R.id.info_ticket_price);
        price.setText(ticketPrice);

        TextView compName = (TextView) findViewById(R.id.info_companyName);
        compName.setText(companyName);

        Button returnMainPage = (Button) findViewById(R.id.returnToMain);
        returnMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainPage = new Intent(TicketInformation.this,MainActivity.class);
                startActivity(toMainPage);
            }
        });

    }
}