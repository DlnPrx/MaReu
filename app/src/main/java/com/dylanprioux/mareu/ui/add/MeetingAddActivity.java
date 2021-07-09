package com.dylanprioux.mareu.ui.add;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dylanprioux.mareu.R;

/**
 * MeetingAddActivity
 * container for setup new meeting fragments
 */

public class MeetingAddActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_add);
        configureAndShowSetupSubjectFragment();

    }


    public void configureAndShowSetupSubjectFragment() {
        //Add first fragment into MeetingAddActivity(Subject)
        SetupSubjectFragment setupSubjectFragment = (SetupSubjectFragment) getSupportFragmentManager().findFragmentById(R.id.setup_subject_fragment);
        if (setupSubjectFragment == null) {
            setupSubjectFragment = new SetupSubjectFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.add_activity_container, setupSubjectFragment).commit();

        }
    }
}