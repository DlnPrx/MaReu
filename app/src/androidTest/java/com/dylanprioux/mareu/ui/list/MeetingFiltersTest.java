package com.dylanprioux.mareu.ui.list;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dylanprioux.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeetingFiltersTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void meetingFiltersTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.meeting_list_add_button), withContentDescription(R.string.add_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meeting_list_fragment),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.setup_subject_fragment_edit_text_subject),
                        childAtPosition(
                                allOf(withId(R.id.setup_subject_fragment),
                                        childAtPosition(
                                                withId(R.id.add_activity_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.setup_subject_fragment_button_next), withText(R.string.next),
                        childAtPosition(
                                allOf(withId(R.id.setup_subject_fragment),
                                        childAtPosition(
                                                withId(R.id.add_activity_container),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.fragment_setup_date_next_button), withText(R.string.next),
                        childAtPosition(
                                allOf(withId(R.id.layout_setup_date_fragment),
                                        childAtPosition(
                                                withId(R.id.add_activity_container),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.fragment_setup_time_button_next), withText(R.string.next),
                        childAtPosition(
                                allOf(withId(R.id.layout_setup_time_fragment),
                                        childAtPosition(
                                                withId(R.id.add_activity_container),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radio_button_60), withText(R.string._60_minutes),
                        childAtPosition(
                                allOf(withId(R.id.fragment_setup_duration_radio_group),
                                        childAtPosition(
                                                withId(R.id.layout_setup_duration_fragment),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.fragment_setup_duration_next_button), withText(R.string.next),
                        childAtPosition(
                                allOf(withId(R.id.layout_setup_duration_fragment),
                                        childAtPosition(
                                                withId(R.id.add_activity_container),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.fragment_setup_participant_room_recycler_view),
                        childAtPosition(
                                withId(R.id.layout_setup_room_fragment),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.fragment_setup_participant_button_validate), withText(R.string.validate),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_setup_participant_fragment),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription(R.string.more_option),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_filter),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText(R.string.filter_by_room),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.fragment_room_filter_recycler_view),
                        childAtPosition(
                                withId(R.id.room_filter_fragment),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_meeting_name), withText("test"),
                        withParent(withParent(withId(R.id.meeting_recycler_view))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));



        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription(R.string.more_option),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_filter),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText(R.string.filter_by_room),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.fragment_room_filter_recycler_view),
                        childAtPosition(
                                withId(R.id.room_filter_fragment),
                                0)));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_meeting_name), withText("test"),
                        withParent(withParent(withId(R.id.meeting_recycler_view))),
                        isDisplayed()));
        textView2.check(doesNotExist());

        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription(R.string.more_option),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar_filter),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.title), withText(R.string.filter_by_time),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.fragment_time_filter_button_validate), withText(R.string.validate),
                        childAtPosition(
                                allOf(withId(R.id.time_filter_fragment),
                                        childAtPosition(
                                                withId(R.id.main_activity_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_meeting_name), withText("test"),
                        withParent(withParent(withId(R.id.meeting_recycler_view))),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.item_meeting_delete_image), withContentDescription(R.string.delete_image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.meeting_recycler_view),
                                        0),
                                5),
                        isDisplayed()));
        appCompatImageView.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
