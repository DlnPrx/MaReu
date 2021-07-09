package com.dylanprioux.mareu.ui.add;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.model.Meeting;


/**
 * SetupSubjectFragment
 * Fragment for add the subject of the meeting
 */

public class SetupSubjectFragment extends Fragment {
    private EditText mEditTextSubject;
    private Button mButtonNext;
    private View mView;
    private Meeting mMeeting;

    public SetupSubjectFragment() {
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
        mView = inflater.inflate(R.layout.fragment_setup_subject, container, false);

        setupWidget();

        return mView;
    }

    private void setupWidget() {
        mEditTextSubject = mView.findViewById(R.id.setup_subject_fragment_edit_text_subject);
        mButtonNext = mView.findViewById(R.id.setup_subject_fragment_button_next);
        mButtonNext.setEnabled(false);
        mButtonNext.setTransformationMethod(null); //remove all caps
        mEditTextSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    mButtonNext.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mButtonNext.setOnClickListener(v -> {

            setupNewEmptyMeeting(mEditTextSubject.getText().toString());
            configureAndShowSetupDateFragment();
        });
    }

    private void setupNewEmptyMeeting(String subject) {
        mMeeting = new Meeting();
        mMeeting.setName(subject);
    }


    private void configureAndShowSetupDateFragment() {
        SetupDateFragment setupDateFragment;
        setupDateFragment = new SetupDateFragment();
        Bundle args = new Bundle();
        args.putParcelable("newMeeting", mMeeting);
        setupDateFragment.setArguments(args);
        getParentFragmentManager().beginTransaction().replace(R.id.add_activity_container, setupDateFragment).commit();

    }
}