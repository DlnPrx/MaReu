package com.dylanprioux.mareu.ui.add;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.model.Participant;
import com.dylanprioux.mareu.services.MeetingApiService;
import com.dylanprioux.mareu.ui.list.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * SetupParticipantFragment
 * Fragment for add the list of participants
 */

public class SetupParticipantFragment extends Fragment {
    private Meeting mMeeting;
    private View mView;
    private MeetingApiService mMeetingApiService;
    private List<Participant> mParticipantList;


    public SetupParticipantFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get meeting from arguments and put it into class member (mMeeting)
        Bundle args = getArguments();
        assert args != null;
        mMeeting = args.getParcelable("newMeeting");
        mMeetingApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_setup_participant, container, false);
        configureRecyclerView();
        setupWidget();

        return mView;
    }

    private void setupWidget() {
        Button validateButton = mView.findViewById(R.id.fragment_setup_participant_button_validate);
        validateButton.setTransformationMethod(null); //remove all caps
        validateButton.setOnClickListener(v -> {
            createParticipantListChecked();
            finaliseMeeting();
            returnToMainActivity();
        });


    }


    private void returnToMainActivity() {

        Intent intent = new Intent(mView.getContext(), MainActivity.class);
        startActivity(intent);
    }

    private void configureRecyclerView() {


        mParticipantList = mMeetingApiService.getParticipantList();
        RecyclerView recyclerView = mView.findViewById(R.id.fragment_setup_participant_room_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        SetupParticipantRecyclerViewAdapter adapter = new SetupParticipantRecyclerViewAdapter(mParticipantList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    private void createParticipantListChecked() {
        //add participants into meeting (mMeeting)
        ArrayList<Participant> participantListChecked = new ArrayList<>();
        for (Participant elem : mParticipantList) {

            if (elem.getChecked()) {
                participantListChecked.add(elem);
            }


        }
        mMeeting.setParticipantsList(participantListChecked);
    }

    private void finaliseMeeting() {
        //final step, generate new meeting(mMeeting)
        mMeetingApiService.generateMeeting(mMeeting);
    }


}