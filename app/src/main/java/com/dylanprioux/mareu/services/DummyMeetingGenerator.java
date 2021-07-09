package com.dylanprioux.mareu.services;

import com.dylanprioux.mareu.model.Meeting;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Dummy meeting for POC use
 * methode generateDummyMeetings() return a list of fake meetings
 */
public class DummyMeetingGenerator {


    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Reunion 1", new GregorianCalendar(2021, Calendar.JUNE, 8, 12, 0), new GregorianCalendar(2021, Calendar.JUNE, 8, 13, 0), 60, DummyRoomGenerator.generateDummyRoomList().get(0), DummyParticipantGenerator.generateParticipantList()),
            new Meeting("Reunion 2", new GregorianCalendar(2021, Calendar.JUNE, 8, 17, 0), new GregorianCalendar(2021, Calendar.JUNE, 8, 18, 0), 60, DummyRoomGenerator.generateDummyRoomList().get(1), DummyParticipantGenerator.generateParticipantList()),
            new Meeting("Reunion 3", new GregorianCalendar(2021, Calendar.JUNE, 8, 17, 0), new GregorianCalendar(2021, Calendar.JUNE, 8, 18, 0), 60, DummyRoomGenerator.generateDummyRoomList().get(2), DummyParticipantGenerator.generateParticipantList()));


    public static List<Meeting> generateDummyMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }


}
