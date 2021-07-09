package com.dylanprioux.mareu.di;


import com.dylanprioux.mareu.services.DummyMeetingApiService;
import com.dylanprioux.mareu.services.MeetingApiService;

/**
 * Dependency injector to get instance of services
 */

public class DI {

    private static final MeetingApiService service = new DummyMeetingApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     * @return service
     */
    public static MeetingApiService getMeetingApiService() {

        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     * @return new DummyMeetingApiService
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}