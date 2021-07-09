package com.dylanprioux.mareu.services;

import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.model.Participant;

import com.dylanprioux.mareu.model.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * DummyMeetingApiService for POC use
 */

public class DummyMeetingApiService implements com.dylanprioux.mareu.services.MeetingApiService {

    private final List<Meeting> mMeetingList = DummyMeetingGenerator.generateDummyMeetings();


    @Override
    public List<Meeting> getMeetings() {
        return mMeetingList;
    }

    @Override
    public void generateMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }


    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

    @Override
    public List<Room> getRoomList() {
        return DummyRoomGenerator.generateDummyRoomList();
    }

    @Override
    public ArrayList<Room> getAvailableRoomList(Calendar startTime, Calendar endTime, int duration) {

        List<Room> roomList = getRoomList();
        List<Meeting> meetingList = getMeetings();

        ArrayList<Room> availableRoom;
        availableRoom = (ArrayList<Room>) roomList;

        for (Meeting elem : meetingList) {

            if (!startTime.after(elem.getEndCalendar())) {
                if (!endTime.before(elem.getStartCalendar())) {

                    availableRoom.remove(elem.getRoom());
                }
            }
        }
        return availableRoom;
    }


    @Override
    public List<Participant> getParticipantList() {
        return DummyParticipantGenerator.generateParticipantList();
    }

    @Override
    public Room getRoomWithName(String roomName) {
        List<Room> roomList = getRoomList();


        for (Room elem : roomList) {
            if (elem.getName().equals(roomName)) {
                return elem;
            }
        }
        return null;
    }

    @Override
    public List<Meeting> getFilteredRoomList(Room room) {
        ArrayList<Meeting> meetingList = (ArrayList<Meeting>) getMeetings();
        ArrayList<Meeting> listOfFilteredMeetings = new ArrayList<>();
        for (Meeting elem : meetingList) {
            if (elem.getRoom().equals(room)) {
                listOfFilteredMeetings.add(elem);

            }

        }
        return listOfFilteredMeetings;
    }

    @Override
    public List<Meeting> getFilteredTimeList(GregorianCalendar calendar) {

        ArrayList<Meeting> meetingList = (ArrayList<Meeting>) getMeetings();
        ArrayList<Meeting> listOfFilteredMeetings = new ArrayList<>();
        for (Meeting elem : meetingList) {
            if (elem.getStartCalendar().get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                listOfFilteredMeetings.add(elem);

            }

        }
        return listOfFilteredMeetings;

    }
}
