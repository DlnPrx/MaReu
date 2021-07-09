package com.dylanprioux.mareu.ui.add;

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

import java.util.ArrayList;
import java.util.List;

/**
 * SetupRoomRecyclerViewAdapter
 * recycler view adapter for setupRoomFragment
 */

public class SetupRoomRecyclerViewAdapter extends RecyclerView.Adapter<SetupRoomRecyclerViewAdapter.ViewHolder> {


    private final List<Room> mRoomList;
    private final MeetingApiService mMeetingApiService = DI.getMeetingApiService();


    public SetupRoomRecyclerViewAdapter(ArrayList<Room> items) {
        mRoomList = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_room_item, parent, false);

        v.setOnClickListener(v1 -> {
            String roomName;
            roomName = ((TextView) v1.findViewById(R.id.item_room_name)).getText().toString();
            mMeetingApiService.getRoomWithName(roomName);


        });
        return new ViewHolder(v, mListener);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Room currentRoom = mRoomList.get(position);

        holder.mNameTextView.setText(currentRoom.getName());
        holder.mColorImageView.setImageResource(currentRoom.getImage());


    }

    @Override
    public int getItemCount() {
        return mRoomList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameTextView;
        public ImageView mColorImageView;


        public ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.item_room_name);
            mColorImageView = itemView.findViewById(R.id.item_room_color);
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

        }

    }

    //interface click on item
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
