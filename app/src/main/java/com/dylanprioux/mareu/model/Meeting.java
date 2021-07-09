package com.dylanprioux.mareu.model;


import android.os.Parcel;
import android.os.Parcelable;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


/**
 * Model object representing a Meeting
 */

public class Meeting implements Parcelable {


    /**
     * Name
     */
    private String mName;

    /**
     * Start Time
     */
    private Calendar mStartCalendar;
    /**
     * End Time
     */
    private Calendar mEndCalendar;

    /**
     * Room
     */
    private Room mRoom;

    /**
     * Participants
     */
    private List<Participant> mParticipantsList;

    /**
     * Duration
     */
    private int mDuration;

    public Meeting(String name, Calendar startCalendar, Calendar endCalendar, int duration, Room room, List<Participant> participants) {

        this.mName = name;
        this.mStartCalendar = startCalendar;
        this.mRoom = room;
        this.mParticipantsList = participants;
        this.mEndCalendar = endCalendar;
        this.mDuration = duration;

    }

    public Meeting() {
    }

    protected Meeting(Parcel in) {

        mName = in.readString();
        mRoom = in.readParcelable(Room.class.getClassLoader());
        mParticipantsList = in.createTypedArrayList(Participant.CREATOR);
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public Room getRoom() {
        return mRoom;
    }

    public void setRoom(Room room) {
        mRoom = room;
    }

    public List<Participant> getParticipantsList() {
        return mParticipantsList;
    }

    public void setParticipantsList(ArrayList<Participant> participantsList) {
        mParticipantsList = participantsList;
    }

    public Calendar getStartCalendar() {
        return mStartCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        mStartCalendar = startCalendar;
    }

    public Calendar getEndCalendar() {
        return mEndCalendar;
    }

    public void setEndCalendar(Calendar endCalendar) {
        mEndCalendar = endCalendar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mStartCalendar, mRoom, mParticipantsList, mEndCalendar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mName);
        dest.writeParcelable(mRoom, flags);
        dest.writeTypedList(mParticipantsList);
        dest.writeInt(mDuration);
    }
}
