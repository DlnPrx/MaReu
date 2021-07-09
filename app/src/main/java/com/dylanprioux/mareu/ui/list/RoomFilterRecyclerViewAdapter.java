package com.dylanprioux.mareu.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Room;
import com.dylanprioux.mareu.services.MeetingApiService;

import java.util.List;

/**
 * RoomFilterRecyclerViewAdapter
 * Fragment for add the meeting room
 */

public class RoomFilterRecyclerViewAdapter extends RecyclerView.Adapter<RoomFilterRecyclerViewAdapter.ViewHolder> {
    private final List<Room> mRoomList;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();

    RoomFilterRecyclerViewAdapter(List<Room> items) {
        mRoomList = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_room_item, parent, false);
        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(RoomFilterRecyclerViewAdapter.ViewHolder holder, int position) {
        Room currentRoom = mMeetingApiService.getRoomList().get(position);
        holder.mNameTextView.setText(currentRoom.getName());
        holder.mImageView.setImageResource(currentRoom.getImage());
    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public ImageView mImageView;

        public ViewHolder(View itemView, OnRoomClickListener listener) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.item_room_name);
            mImageView = itemView.findViewById(R.id.item_room_color);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onRoomClick(position);
                    }
                }
            });
        }
    }

    private OnRoomClickListener mListener;

    public interface OnRoomClickListener {
        void onRoomClick(int position);
    }

    public void setOnRoomClickListener(OnRoomClickListener listener) {
        mListener = listener;
    }
}
