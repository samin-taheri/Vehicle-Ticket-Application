package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportActionBar().setTitle("Bookings...");

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabItem tabUpcoming = findViewById(R.id.tab_upcoming);
        TabItem tabArchived = findViewById(R.id.tab_archived);
        ViewPager viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new UpcomingFragment(), "UPCOMING");
        pagerAdapter.addFragment(new ArchiveFragment(), "ARCHIVED");
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void navigateHome(View view){
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
/*
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment2 = null;
            switch (item.getItemId()) {
                case R.id.ic_upcoming:
                    selectedFragment2 = new UpcomingFragment();
                    break;
                case R.id.ic_archived:
                    selectedFragment2 = new ArchiveFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment2).commit();
            return true;
        }
    };


 */

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment2 = null;
            switch (item.getItemId()) {
                case R.id.ic_baseline_search_24:
                    Intent intent0 = new Intent(BookingsActivity.this, MainActivity.class);
                    startActivity(intent0);
                    break;
                case R.id.ic_baseline_flight_takeoff_24:
                    break;
                case R.id.ic_baseline_person_24:
                    Intent intent1 = new Intent(BookingsActivity.this, ProfileActivity.class);
                    startActivity(intent1);
                    break;
            }
            return true;
        }
    };
}
