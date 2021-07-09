package com.dylanprioux.mareu.ui.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dylanprioux.mareu.R;

import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.services.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * MeetingRecyclerViewAdapter
 * recycler view adapter for MeetingListFragment
 */

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> {


    private final List<Meeting> mMeetingList;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    public MeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetingList = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_meeting_item, parent, false);


        return new ViewHolder(v);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting currentMeeting = mMeetingList.get(position);

        holder.mNameTextView.setText(currentMeeting.getName());
        holder.mRoomTextView.setText(currentMeeting.getRoom().getName());

        //configuration time format
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy HH:mm");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
        holder.mTimeTextView.setText(dateFormat.format(currentMeeting.getStartCalendar().getTime()));

        holder.mDuration.setText("-" + dateFormat2.format(currentMeeting.getEndCalendar().getTime()));

        holder.mParticipantsTextView.setText(currentMeeting.getParticipantsList().toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")); //remove the left bracket);
        holder.mColorImageView.setImageResource(currentMeeting.getRoom().getImage());


        //configuration delete image
        holder.mDeleteImageView.setOnClickListener(v -> {
            mMeetingApiService.deleteMeeting(currentMeeting);
            mMeetingList.remove(currentMeeting);
            notifyItemRemoved(holder.getAdapterPosition());


        });

    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public TextView mRoomTextView;
        public TextView mTimeTextView;
        public TextView mParticipantsTextView;
        public ImageView mColorImageView;
        public ImageView mDeleteImageView;
        public TextView mDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.item_meeting_name);
            mRoomTextView = itemView.findViewById(R.id.item_meeting_room);
            mTimeTextView = itemView.findViewById(R.id.item_meeting_time);
            mParticipantsTextView = itemView.findViewById(R.id.item_meeting_participants);
            mColorImageView = itemView.findViewById(R.id.item_meeting_color);
            mDeleteImageView = itemView.findViewById(R.id.item_meeting_delete_image);
            mDuration = itemView.findViewById(R.id.item_meeting_duration);


        }
    }


}

