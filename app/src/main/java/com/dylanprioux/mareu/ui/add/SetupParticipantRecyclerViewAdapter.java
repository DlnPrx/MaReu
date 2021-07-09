package com.dylanprioux.mareu.ui.add;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.model.Participant;

import java.util.List;

/**
 * SetupParticipantRecyclerViewAdapter
 * recycler view adapter for SetupParticipantFragment
 */

public class SetupParticipantRecyclerViewAdapter extends RecyclerView.Adapter<SetupParticipantRecyclerViewAdapter.ViewHolder> {

    private final List<Participant> mParticipantList;

    public SetupParticipantRecyclerViewAdapter(List<Participant> participantList) {

        mParticipantList = participantList;
    }


    @NonNull
    @Override
    public SetupParticipantRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_participant_item, parent, false);
        setParticipantListCheckToFalse();

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SetupParticipantRecyclerViewAdapter.ViewHolder holder, int position) {
        setParticipantListCheckToFalse();
        Participant currentParticipant = mParticipantList.get(position);
        holder.mMailAddress.setText(currentParticipant.getMail());
        holder.mCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> currentParticipant.setChecked(isChecked));
    }

    @Override
    public int getItemCount() {
        return mParticipantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mMailAddress;
        public CheckBox mCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            mMailAddress = itemView.findViewById(R.id.item_participant_adress);
            mCheckBox = itemView.findViewById(R.id.item_participant_checkbox);


        }
    }

    private void setParticipantListCheckToFalse() {
        for (Participant elem : mParticipantList) {

            elem.setChecked(false);


        }
    }

}

