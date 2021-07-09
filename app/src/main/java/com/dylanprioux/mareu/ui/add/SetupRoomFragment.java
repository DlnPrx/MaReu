package com.dylanprioux.mareu.ui.add;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.model.Room;
import com.dylanprioux.mareu.services.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * SetupRoomFragment
 * Fragment for add the room of the meeting
 * only show rooms available at the time of the meeting
 */

public class SetupRoomFragment extends Fragment {


    private Meeting mMeeting;
    private View mView;
    private MeetingApiService mMeetingApiService;
    private List<Room> mRoomList;


    public SetupRoomFragment() {
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
        mView = inflater.inflate(R.layout.fragment_setup_room, container, false);

        configureRecyclerView();

        return mView;
    }


    private void configureAndShowSetupParticipantFragment() {
        SetupParticipantFragment setupParticipantFragment;
        setupParticipantFragment = new SetupParticipantFragment();
        Bundle args = new Bundle();
        args.putParcelable("newMeeting", mMeeting);
        setupParticipantFragment.setArguments(args);
        getParentFragmentManager().beginTransaction().replace(R.id.add_activity_container, setupParticipantFragment).commit();
    }

    private void configureRecyclerView() {

        mRoomList = mMeetingApiService.getAvailableRoomList(mMeeting.getStartCalendar(), mMeeting.getEndCalendar(), mMeeting.getDuration());
        RecyclerView recyclerView = mView.findViewById(R.id.fragment_setup_participant_room_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        SetupRoomRecyclerViewAdapter adapter = new SetupRoomRecyclerViewAdapter((ArrayList<Room>) mRoomList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //interface click on item
        adapter.setOnItemClickListener(position -> {

            mMeeting.setRoom(mMeetingApiService.getRoomWithName(mRoomList.get(position).getName()));
            configureAndShowSetupParticipantFragment();
        });

    }

}