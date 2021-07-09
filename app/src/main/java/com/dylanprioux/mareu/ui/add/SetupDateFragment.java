package com.dylanprioux.mareu.ui.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.model.Meeting;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * SetupDateFragment
 * Fragment for add the meeting start day
 */

public class SetupDateFragment extends Fragment {

    private Meeting mMeeting;
    private View mView;
    private Calendar mCalendar;
    private DatePicker mDatePicker;


    public SetupDateFragment() {
// Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get arguments from SetupSubjectFragment and put them into new meeting (mMeeting)
        Bundle args = getArguments();
        assert args != null;
        mMeeting = args.getParcelable("newMeeting");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_setup_date, container, false);
        setupWidget();

        return mView;
    }




    private void setupWidget() {
        //setup next button and datePicker
        mDatePicker = mView.findViewById(R.id.fragment_setup_date_date_picker);
        Button button = mView.findViewById(R.id.fragment_setup_date_next_button);
        button.setTransformationMethod(null); //remove all caps
        button.setOnClickListener(v -> {
            mCalendar = new GregorianCalendar();
            mCalendar.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
            setupNewMeeting(mCalendar);

            configureAndShowSetupTimeFragment();

        });
    }

    private void setupNewMeeting(Calendar calendar) {
        //add start time into meeting (mMeeting)
        mMeeting.setStartCalendar(calendar);
    }

    private void configureAndShowSetupTimeFragment() {
        //configure SetupTimeFragment and put the new meeting  into arguments
        SetupTimeFragment setupTimeFragment;
        setupTimeFragment = new SetupTimeFragment();
        Bundle args = new Bundle();
        args.putParcelable("newMeeting", mMeeting);
        setupTimeFragment.setArguments(args);
        getParentFragmentManager().beginTransaction().replace(R.id.add_activity_container, setupTimeFragment).commit();

    }
}