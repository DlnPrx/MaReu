package com.dylanprioux.mareu.services;

import com.dylanprioux.mareu.R;
import com.dylanprioux.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dummy Room for POC use
 * methode generateDummyRoomList return a list of fake rooms
 */

public class DummyRoomGenerator {

    static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Dublin", R.drawable.dublin),
            new Room("Lisbonne", R.drawable.lisbonne),
            new Room("Londres", R.drawable.londres),
            new Room("Paris", R.drawable.paris),
            new Room("Bruxelles", R.drawable.bruxelle),
            new Room("Madrid", R.drawable.madrid),
            new Room("Copenhague", R.drawable.copenhague),
            new Room("Berlin", R.drawable.berlin),
            new Room("Rome", R.drawable.rome),
            new Room("Varsovie", R.drawable.varsovie)
    );

    public static List<Room> generateDummyRoomList() {
        return new ArrayList<>(DUMMY_ROOMS);
    }
}
