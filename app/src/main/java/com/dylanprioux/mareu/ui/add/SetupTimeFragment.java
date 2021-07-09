package com.dylanprioux.mareu.ui.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.model.Meeting;

import java.util.Calendar;
import java.util.Objects;


/**
 * SetupTimeFragment
 * Fragment for add the meeting start time
 */

public class SetupTimeFragment extends Fragment {
    private View mView;
    private Meeting mMeeting;


    private TimePicker mTimePicker;


    public SetupTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get meeting from arguments and put it into class member (mMeeting)
        Bundle args = getArguments();
        mMeeting = Objects.requireNonNull(args).getParcelable("newMeeting");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_setup_time, container, false);
        // Widgets
        setupWidgets();

        return mView;
    }

    private void setupWidgets() {

        mTimePicker = mView.findViewById(R.id.fragment_setup_time_time_picker);
        mTimePicker.setIs24HourView(true);

        Button button = mView.findViewById(R.id.fragment_setup_time_button_next);
        button.setTransformationMethod(null); //remove all caps
        button.setOnClickListener(v -> {


            setupNewEmptyMeeting();

            configureAndShowSetupDurationFragment();
        });


    }


    private void setupNewEmptyMeeting() {

        Calendar calendar = mMeeting.getStartCalendar();

        //use methode getCurrentHour() and getCurrentMinute deprecated in API 23, but working on api 21 or higher
        calendar.set(Calendar.HOUR_OF_DAY, mTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, mTimePicker.getCurrentMinute());
        mMeeting.setStartCalendar(calendar);


    }

    private void configureAndShowSetupDurationFragment() {
        SetupDurationFragment setupDurationFragment;
        setupDurationFragment = new SetupDurationFragment();
        Bundle args = new Bundle();

        args.putParcelable("newMeeting", mMeeting);
        setupDurationFragment.setArguments(args);
        getParentFragmentManager().beginTransaction().replace(R.id.add_activity_container, setupDurationFragment).commit();


    }

}