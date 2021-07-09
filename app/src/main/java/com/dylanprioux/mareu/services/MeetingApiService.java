package com.dylanprioux.mareu.services;


import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.model.Participant;

import com.dylanprioux.mareu.model.Room;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Meeting API client
 */
public interface MeetingApiService {

    /**
     * Get all my Meetings
     * return the list of meetings
     *
     * @return {@link List}
     */
    List<Meeting> getMeetings();


    /**
     * Generate a meeting
     * add a meeting into meetingList
     *
     * @param meeting
     */
    void generateMeeting(Meeting meeting);


    /**
     * Delete a meeting
     * remove a meeting from the meeting list
     *
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Get Room List
     * get the list of all rooms
     *
     * @return {@link List}
     */
    List<Room> getRoomList();

    /**
     * Get Room available
     * returns the list of rooms available at a given period
     *
     * @return {@link List}
     */
    ArrayList<Room> getAvailableRoomList(Calendar startTime, Calendar EndTime, int duration);

    /**
     * Get Participants List
     * get the list of participants
     *
     * @return {@link List}
     */
    List<Participant> getParticipantList();

    /**
     * Get room with name
     * get a room with its name
     *
     * @return {@link List}
     */
    Room getRoomWithName(String roomName);

    /**
     * Meeting room filter
     * get the list of meetings by room
     *
     * @return {@link List}
     */
    List<Meeting> getFilteredRoomList(Room room);

    /**
     * meeting day filter
     * get the list of meetings by day
     *
     * @return {@link List}
     */
    List<Meeting> getFilteredTimeList(GregorianCalendar calendar);

}
