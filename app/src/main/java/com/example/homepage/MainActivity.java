package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String startCity, endCity, flight_date;
    private DatePickerDialog.OnDateSetListener mDate;
    private static final String TAG ="MainActivity";
    private ArrayList<NewsDraft> newsList = new ArrayList<>();
    private ArrayList<announcementDraft> announcementList = new ArrayList<>();
    private newsAdapter newsAdapter;
    private announcementAdapter announcementAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Where to?");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);



        RecyclerView recyclerView = findViewById(R.id.newsBox);
        DatabaseReference newsRef = database.getReference("news");

        Query newsQuery = newsRef.orderByKey();

        ValueEventListener newsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshotIterator = snapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()){
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    newsList.add(new NewsDraft(next.child("title").getValue().toString(),next.child("news-content").getValue().toString()
                            ,next.child("date").getValue().toString()));

                }
                newsAdapter = new newsAdapter(newsList);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(newsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        newsQuery.addListenerForSingleValueEvent(newsListener);

        RecyclerView recyclerView1 = findViewById(R.id.announcementList);
        DatabaseReference announcementRef = database.getReference("announcement");

        Query announcementQuery = announcementRef.orderByKey();

        ValueEventListener announcementListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshotAnnouncementIterator = snapshot.getChildren();
                Iterator<DataSnapshot> announcementIterator = snapshotAnnouncementIterator.iterator();
                while (announcementIterator.hasNext()){
                    DataSnapshot next = (DataSnapshot) announcementIterator.next();
                    announcementList.add(new announcementDraft(next.child("content").getValue().toString()));

                }
                announcementAdapter = new announcementAdapter(announcementList);
                LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
                mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView1.setLayoutManager(mLayoutManager1);
                recyclerView1.setAdapter(announcementAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        announcementQuery.addListenerForSingleValueEvent(announcementListener);

        EditText mDisplayDate =(EditText)  findViewById(R.id.editTextDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                flight_date = date;
            }
        };

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(MainActivity.this, FlightActivity.class);
                searchIntent.putExtra("startCity", startCity);
                searchIntent.putExtra("endCity", endCity);
                searchIntent.putExtra("flight_date", flight_date);
                startActivity(searchIntent);
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_baseline_search_24:
                    break;
                case R.id.ic_baseline_flight_takeoff_24:
                    Intent intent1 = new Intent(MainActivity.this, BookingsActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.ic_baseline_person_24:
                    Intent intent2 = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent2);
                    break;
            }
            return true;
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinner1:
                startCity = parent.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, startCity, Toast.LENGTH_LONG).show();
                break;
            case R.id.spinner2:
                endCity = parent.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, endCity, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}