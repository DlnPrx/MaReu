package com.dylanprioux.mareu.ui.add;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.model.Meeting;

import java.util.Calendar;

/**
 * SetupDurationFragment
 * Fragment for add the meeting duration and end time
 */

public class SetupDurationFragment extends Fragment {
    Meeting mMeeting;
    SetupRoomFragment mSetupRoomFragment;
    View mView;
    Button mButtonNext;
    RadioGroup mRadioGroup;
    int mDuration;

    public SetupDurationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get meeting from arguments and put it into class member (mMeeting)
        Bundle args = getArguments();
        assert args != null;
        mMeeting = args.getParcelable("newMeeting");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_setup_duration, container, false);
        setupWidgets();

        return mView;
    }


    @SuppressLint("NonConstantResourceId")
    private void setupWidgets() {

        mRadioGroup = mView.findViewById(R.id.fragment_setup_duration_radio_group);
        mButtonNext = mView.getRootView().findViewById(R.id.fragment_setup_duration_next_button);
        mButtonNext.setEnabled(false);
        mButtonNext.setTransformationMethod(null); //remove all caps
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> mButtonNext.setEnabled(true));

        mButtonNext.setOnClickListener(v -> {

            int id = mRadioGroup.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radio_button_15: {
                    mDuration = 15;
                    break;
                }
                case R.id.radio_button_30: {
                    mDuration = 30;
                    break;
                }
                case R.id.radio_button_45: {
                    mDuration = 45;
                    break;
                }
                case R.id.radio_button_60: {
                    mDuration = 60;
                    break;
                }
                case R.id.radio_button_90: {
                    mDuration = 90;
                    break;
                }
                case R.id.radio_button_120: {
                    mDuration = 120;
                    break;
                }
            }

            setupNewMeeting(mDuration);

            configureAndShowSetupRoomFragment();
        });


    }

    private void configureAndShowSetupRoomFragment() {
        mSetupRoomFragment = (SetupRoomFragment) getParentFragmentManager().findFragmentById(R.id.layout_setup_room_fragment);
        mSetupRoomFragment = new SetupRoomFragment();
        Bundle args = new Bundle();

        args.putParcelable("newMeeting", mMeeting);
        mSetupRoomFragment.setArguments(args);
        getParentFragmentManager().beginTransaction().replace(R.id.add_activity_container, mSetupRoomFragment).commit();


    }

    private void setupNewMeeting(int duration) {
        //add end time into meeting (mMeeting)
        Calendar.getInstance();
        Calendar calendar;
        calendar = (Calendar) mMeeting.getStartCalendar().clone();
        calendar.add(Calendar.MINUTE, duration);
        mMeeting.setEndCalendar(calendar);

    }

}