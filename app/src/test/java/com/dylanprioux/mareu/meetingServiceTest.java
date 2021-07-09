package com.dylanprioux.mareu;

import com.dylanprioux.mareu.di.DI;
import com.dylanprioux.mareu.model.Meeting;
import com.dylanprioux.mareu.model.Participant;
import com.dylanprioux.mareu.model.Room;
import com.dylanprioux.mareu.services.DummyMeetingGenerator;
import com.dylanprioux.mareu.services.DummyParticipantGenerator;
import com.dylanprioux.mareu.services.DummyRoomGenerator;
import com.dylanprioux.mareu.services.MeetingApiService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests :
 * MeetingApiService
 * ---
 * Get all my Meetings
 * List<Meeting> getMeetings();
 * method : getMeetingWithSuccess()
 * compare the method getMeeting with the list expected
 * ---
 * Generate a meeting
 * void generateMeeting(Meeting meeting);
 * method : createMeetingWithSuccess()
 * ---
 * Delete a meeting
 * void deleteMeeting (Meeting meeting);
 * method : deleteMeetingWithSuccess()
 * ---
 * Get Room List
 * List<Room> getRoomList();
 * method :
 * ---
 * Get Room available
 * ArrayList<Room> getAvailableRoomList(Calendar startTime, Calendar EndTime, int duration);
 * method :
 * ---
 * Get Participants List
 * List<Participant> getParticipantList();
 * method :
 * ---
 * get the list of meetings by room
 * List<Meeting> getFilteredRoomList(Room room);
 * method : getFilteredRoomListWithSuccess
 * ---
 *get the list of meetings by day
 * List<Meeting> getFilteredTimeList(GregorianCalendar calendar);
 * method : getFilteredTimeListWithSuccess
 */
public class meetingServiceTest {
    MeetingApiService mMeetingApiService;


    @Before
    public void setup() {

        mMeetingApiService = DI.getNewInstanceApiService();

    }

    /**
     * * Get all my Meetings
     * List<Meeting> getMeetings();
     * method : getMeetingWithSuccess()
     * compare the method getMeeting with the list expected
     */

    @Test
    public void getMeetingWithSuccess() {

        List<Meeting> meetingExpected = DummyMeetingGenerator.DUMMY_MEETINGS;
        List<Meeting> meetings = mMeetingApiService.getMeetings();
        assertEquals(meetingExpected.get(1), meetings.get(1));

    }

    /**
     * * Generate a meeting
     * void generateMeeting(Meeting meeting);
     * method : createMeetingWithSuccess()
     */

    @Test
    public void generateMeetingWithSuccess() {
        Room roomTest = new Room("Red Room", R.drawable.ic_room_color_red);
        Calendar calendarTest = new GregorianCalendar(2021, 5, 15, 8, 45);
        List<Participant> participantListTest = Arrays.asList(new Participant("mail1@mail.com"), new Participant("mail2@mail.com"), new Participant("mail3@mail.com"));


        Meeting mMeetingToAdd = new Meeting("ReuTest", calendarTest, calendarTest, 60, roomTest, participantListTest);
        List<Meeting> meetingList = mMeetingApiService.getMeetings();
        int oldSize = meetingList.size();
        mMeetingApiService.generateMeeting(mMeetingToAdd);
        int newSize = meetingList.size();
        Assert.assertEquals(oldSize, newSize - 1);


    }


    /**
     * Delete a meeting
     * void deleteMeeting (Meeting meeting);
     * method : deleteMeetingWithSuccess()
     * compare the size of the list before and after deleting a meeting
     */

    @Test
    public void deleteMeetingWithSuccess() {
        mMeetingApiService = DI.getNewInstanceApiService();
        Meeting mMeetingToDelete = mMeetingApiService.getMeetings().get(0);
        List<Meeting> meetingList = mMeetingApiService.getMeetings();
        int oldSize = meetingList.size();
        mMeetingApiService.deleteMeeting(mMeetingToDelete);
        int newSize = meetingList.size();
        Assert.assertEquals(oldSize, newSize + 1);

    }

    /**
     * Get Room List
     * List<Room> getRoomList();
     * method : getRoomListWithSuccess()
     */

    @Test
    public void getRoomListWithSuccess() {
        List<Room> roomList = mMeetingApiService.getRoomList();
        List<Room> expectedRoomList = DummyRoomGenerator.generateDummyRoomList();

        assertArrayEquals(roomList.toArray(), expectedRoomList.toArray());

    }


    /**
     * Get Participants List
     * List<Participant> getParticipantList();
     * method :getParticipantListWithSuccess()
     */
    @Test
    public void getParticipantListWithSuccess() {
        List<Participant> expectedParticipantListTest = DummyParticipantGenerator.generateParticipantList();
        List<Participant> participantListTest = mMeetingApiService.getParticipantList();
        assertArrayEquals(expectedParticipantListTest.toArray(), participantListTest.toArray());

    }

    /**
     * Get room with name
     * Room getRoomWithName(String roomName);
     * method : getRoomWithNameWhithSucess()
     */

    @Test
    public void getRoomWithNameWithSuccess() {
        ArrayList<Room> roomList = (ArrayList<Room>) mMeetingApiService.getRoomList();

        //room = Dublin
        Room room = roomList.get(0);

        Room roomTest = mMeetingApiService.getRoomWithName("Dublin");
        assertEquals(room, roomTest);


    }


    /**
     * get the list of meetings by room
     * List<Meeting> getFilteredRoomList(Room room);
     * method : getFilteredRoomListWithSuccess
     */

@Test
    public void getFilteredRoomListWithSuccess()
{


    //room = Dublin
    ArrayList<Room> roomList = (ArrayList<Room>) mMeetingApiService.getRoomList();
    Room roomToBeTested = roomList.get(0);

    ArrayList<Meeting> meetingListFiltered = (ArrayList<Meeting>) mMeetingApiService.getFilteredRoomList(roomToBeTested);

    for (Meeting elem : meetingListFiltered) {
        assertEquals(elem.getRoom(), roomToBeTested);

        }

    }


    /**
     * get the list of meetings by day
     * List<Meeting> getFilteredTimeList(GregorianCalendar calendar);
     * method : getFilteredTimeListWithSuccess
     */
    @Test
    public void getFilteredTimeListWithSuccess()
    {
        boolean testOk = false;

        GregorianCalendar timeToBeTested = new GregorianCalendar(2021, Calendar.JUNE, 8);

        ArrayList<Meeting> meetingListFiltered = (ArrayList<Meeting>) mMeetingApiService.getFilteredTimeList(timeToBeTested);
        for (Meeting elem : meetingListFiltered) {
            if ((elem.getStartCalendar().get(Calendar.YEAR) == timeToBeTested.get(Calendar.YEAR)) && elem.getStartCalendar().get(Calendar.MONTH) == timeToBeTested.get(Calendar.MONTH) && elem.getStartCalendar().get(Calendar.DAY_OF_MONTH) == timeToBeTested.get(Calendar.DAY_OF_MONTH)){
                testOk = true;
            }
            else {
                testOk = false;
                break;

            }
        }
        assertTrue(testOk);
    }

}