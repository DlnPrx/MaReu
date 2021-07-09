package com.dylanprioux.mareu.ui.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.dylanprioux.mareu.R;

import java.util.GregorianCalendar;

/**
 * TimeFilterFragment
 * displays the date picker for the choice of filter
 */

public class TimeFilterFragment extends Fragment {

    private View mView;
    DatePicker mDatePicker;
    Button mValidateButton;
    MeetingListFragment mMeetingListFragment;
    GregorianCalendar mCalendar;

    public TimeFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_time_filter, container, false);
        mCalendar = new GregorianCalendar();
        setupWidgets();
        return mView;
    }

    private void setupWidgets() {
        //setup DatePicker and validate button
        mDatePicker = mView.findViewById(R.id.fragment_time_filter_date_picker);
        mValidateButton = mView.findViewById(R.id.fragment_time_filter_button_validate);
        mValidateButton.setTransformationMethod(null); //remove all caps
        mValidateButton.setOnClickListener(v -> {


            mCalendar.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
            configureAndShowMeetingListWithTimeFilter();

        });

    }


    public void configureAndShowMeetingListWithTimeFilter() {
        mMeetingListFragment = (MeetingListFragment) getParentFragmentManager().findFragmentById(R.id.time_filter_fragment);
        if (mMeetingListFragment == null) {
            mMeetingListFragment = new MeetingListFragment(mCalendar);
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_container, mMeetingListFragment).commit();

        }
    }
}