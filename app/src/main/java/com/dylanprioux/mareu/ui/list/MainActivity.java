package com.dylanprioux.mareu.ui.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dylanprioux.mareu.R;


/**
 * MainActivity
 * container for  fragments list elements
 */

public class MainActivity extends AppCompatActivity {
    MeetingListFragment mMeetingListFragment;
    RoomFilterFragment mRoomFilterFragment;
    TimeFilterFragment mTimeFilterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureAndShowMeetingListFragment();
        Toolbar toolbar = findViewById(R.id.toolbar_filter);
        setSupportActionBar(toolbar);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_by_room:
                configureAndShowRoomFilter();
                return true;

            case R.id.filter_by_time:
                configureAndShowTimeFilter();
                return true;

            case R.id.cancel_filter:
                configureAndShowMeetingListFragment();
        }
        return super.onOptionsItemSelected(item);
    }


    public void configureAndShowMeetingListFragment() {
        mMeetingListFragment = (MeetingListFragment) getSupportFragmentManager().findFragmentById(R.id.meeting_list_fragment);
        if (mMeetingListFragment == null) {
            mMeetingListFragment = new MeetingListFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, mMeetingListFragment).commit();

        }
    }

    public void configureAndShowRoomFilter() {
        mRoomFilterFragment = (RoomFilterFragment) getSupportFragmentManager().findFragmentById(R.id.room_filter_fragment);
        if (mRoomFilterFragment == null) {
            mRoomFilterFragment = new RoomFilterFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, mRoomFilterFragment).commit();

        }
    }

    public void configureAndShowTimeFilter() {
        mTimeFilterFragment = (TimeFilterFragment) getSupportFragmentManager().findFragmentById(R.id.time_filter_fragment);
        if (mTimeFilterFragment == null) {
            mTimeFilterFragment = new TimeFilterFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container, mTimeFilterFragment).commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}