package com.dylanprioux.mareu.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanprioux.mareu.R;

import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.model.Room;
import com.dylanprioux.mareu.services.MeetingApiService;

import com.dylanprioux.mareu.ui.add.MeetingAddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * MeetingListFragment
 * Show the list of meetings
 */

public class MeetingListFragment extends Fragment {
    private List<Meeting> mMeetingList;
    private MeetingApiService mMeetingApiService;
    private FloatingActionButton mAddButton;
    private View mView;
    private Room mFilterRoom;
    private GregorianCalendar mFilterTime;
    private final Boolean mFilterRoomSelected;
    private final Boolean mFilterTimeSelected;


    public MeetingListFragment() {
        //constructor for empty filter
        mFilterTimeSelected = false;
        mFilterRoomSelected = false;
    }

    public MeetingListFragment(Room room) {
        //constructor for room filter
        mFilterRoom = room;
        mFilterTimeSelected = false;
        mFilterRoomSelected = true;
    }

    public MeetingListFragment(GregorianCalendar time) {
        //constructor for time filter
        mFilterTime = time;
        mFilterTimeSelected = true;
        mFilterRoomSelected = false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        mView = view;
        configureAddButton();
        selectListWithFilterSetup();
        configureRecyclerView();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    private void configureAddButton() {

        mAddButton = mView.findViewById(R.id.meeting_list_add_button);
        Context context = mView.getContext();
        mAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, MeetingAddActivity.class);
            startActivity(intent);
        });
    }

    private void selectListWithFilterSetup() {
        //select the list according to the choice of filter
        if (mFilterRoomSelected) {
            mMeetingList = mMeetingApiService.getFilteredRoomList(mMeetingApiService.getRoomWithName(mFilterRoom.getName()));
            mAddButton.hide();
        } else if (mFilterTimeSelected) {
            mMeetingList = mMeetingApiService.getFilteredTimeList(mFilterTime);
            mAddButton.hide();

        } else {
            mMeetingList = mMeetingApiService.getMeetings();
            mAddButton.show();
        }
    }

    private void configureRecyclerView() {

        RecyclerView recyclerView = mView.findViewById(R.id.meeting_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> adapter = new MeetingRecyclerViewAdapter(mMeetingList);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));


    }
    @Override
    public void onResume() {
        super.onResume();
    }


}