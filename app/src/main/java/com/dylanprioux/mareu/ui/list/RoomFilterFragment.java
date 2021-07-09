package com.dylanprioux.mareu.ui.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Room;
import com.dylanprioux.mareu.services.MeetingApiService;

import java.util.ArrayList;

import static com.dylanprioux.mareu.R.string.Select_one_room;


/**
 * RoomFilterFragment
 * displays the selection of rooms for the choice of filter
 */

public class RoomFilterFragment extends Fragment {

    private MeetingApiService mMeetingApiService;
    private ArrayList<Room> mRoomList;
    private View mView;
    private Room mRoomForFilter;

    public RoomFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_room_filter, container, false);
        configureRecyclerView();
        Toast.makeText(mView.getContext(), Select_one_room, Toast.LENGTH_SHORT).show();

        return mView;
    }

    private void configureRecyclerView() {

        mRoomList = (ArrayList<Room>) mMeetingApiService.getRoomList();
        RecyclerView recyclerView = mView.findViewById(R.id.fragment_room_filter_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        RoomFilterRecyclerViewAdapter adapter = new RoomFilterRecyclerViewAdapter(mRoomList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnRoomClickListener(position -> {
            mRoomForFilter = mMeetingApiService.getRoomWithName(mRoomList.get(position).getName());

            configureAndShowMeetingListWithRoomFilter();
        });
    }


    public void configureAndShowMeetingListWithRoomFilter() {
        MeetingListFragment meetingListFragment = (MeetingListFragment) getParentFragmentManager().findFragmentById(R.id.room_filter_fragment);
        if (meetingListFragment == null) {
            meetingListFragment = new MeetingListFragment(mRoomForFilter);
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_container, meetingListFragment).commit();

        }
    }
}