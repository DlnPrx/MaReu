package com.dylanprioux.mareu.services;

import com.dylanprioux.mareu.model.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dummy participants for POC use
 * methode generateParticipantList() return a list of fake participants
 */

public class DummyParticipantGenerator {
    static List<Participant> DUMMY_PARTICIPANT = Arrays.asList(
            new Participant("user1@mail.com"),
            new Participant("user2@mail.com"),
            new Participant("user3@mail.com"),
            new Participant("user4@mail.com"),
            new Participant("user5@mail.com"));


    public static List<Participant> generateParticipantList() {


        return new ArrayList<>(DUMMY_PARTICIPANT);
    }


}
