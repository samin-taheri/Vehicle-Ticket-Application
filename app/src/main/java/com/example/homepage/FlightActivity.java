package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightActivity extends AppCompatActivity {
    private final String TAG = "FlightActivity";
    private String startCity,endCity,flight_date,ticketDate,startTime,endTime,ticketPrice;
    private ArrayList<FlightDraft> flightList = new ArrayList<>();
    private ArrayList<FlightDraft> filteredList = new ArrayList<>();
    private FlightAdapter flightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        BottomNavigationView bottomNav = findViewById(R.id.top_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("Travel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        if(getIntent().hasExtra("startCity")){
            startCity = getIntent().getStringExtra("startCity");
        }
        if(getIntent().hasExtra("endCity")){
            endCity = getIntent().getStringExtra("endCity");
        }
        if(getIntent().hasExtra("flight_date")) {
            flight_date = getIntent().getStringExtra("flight_date");
        }

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDbRef = mDatabase.getReference("tickets");
        ListView lv1 = findViewById(R.id.flightBox);

        Query mQuery = mDbRef.orderByKey();

        // event to listen from database
        ValueEventListener mQueryValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshotIterator = snapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()){
                    //adds every child attributes to an array list called flightList without filtering
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    if(next.child("startCity").getValue().toString().equalsIgnoreCase(startCity)
                    && next.child("endCity").getValue().toString().equalsIgnoreCase(endCity)
                    && next.child("date").getValue().toString().equalsIgnoreCase(flight_date)){
                        flightList.add(new FlightDraft(next.child("startCity").getValue().toString()
                                ,next.child("startTime").getValue().toString()
                                ,next.child("date").getValue().toString()
                                ,next.child("endCity").getValue().toString()
                                ,next.child("arrivalTime").getValue().toString()
                                ,"$" + next.child("ticketPrice").getValue().toString()
                                ,next.child("companyName").getValue().toString()));
                    }

                }
                //adds that list to the adapter
                flightAdapter = new FlightAdapter(flightList,getApplicationContext());
                lv1.setAdapter(flightAdapter);
                Log.i(TAG,flightList.get(0).getStartCity() + " - " + flightList.get(0).getEndCity() + " - "
                        + flightList.get(0).getDate());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        //flightList always empty if it is outside of the event listener don't know why
        mQuery.addListenerForSingleValueEvent(mQueryValueListener);


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView startC = (TextView) view.findViewById(R.id.startCity);
                String startCity = startC.getText().toString();

                TextView endC = (TextView) view.findViewById(R.id.endCity);
                String endCity = endC.getText().toString();

                TextView flightD = (TextView) view.findViewById(R.id.flight_date);
                String date = flightD.getText().toString();

                TextView startT = (TextView) view.findViewById(R.id.startTime);
                String startTime = startT.getText().toString();

                TextView arrivalT = (TextView) view.findViewById(R.id.arrivalTime);
                String arrivalTime = arrivalT.getText().toString();

                TextView flightP = (TextView) view.findViewById(R.id.flight_price);
                String flightPrice = flightP.getText().toString();

                TextView companyN = (TextView) view.findViewById(R.id.company_name);
                String companyName = companyN.getText().toString();

                Intent moveDataToInfoPage = new Intent(FlightActivity.this,PassengerDetailsActivity.class);
                moveDataToInfoPage.putExtra("startCity",startCity);
                moveDataToInfoPage.putExtra("endCity",endCity);
                moveDataToInfoPage.putExtra("date",date);
                moveDataToInfoPage.putExtra("startTime",startTime);
                moveDataToInfoPage.putExtra("arrivalTime",arrivalTime);
                moveDataToInfoPage.putExtra("price",flightPrice);
                moveDataToInfoPage.putExtra("companyName",companyName);

                startActivity(moveDataToInfoPage);

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TrainFragment()).commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.ic_train:
                    selectedFragment = new TrainFragment();
                    break;
                case R.id.ic_bus:
                    selectedFragment = new BusFragment();
                    break;
                case R.id.ic_flight:
                    selectedFragment = new FlightFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}