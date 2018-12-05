package com.thealienobserver.nikhil.travon;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import com.thealienobserver.nikhil.travon.controllers.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkButtonText() {
        onView(withId(R.id.newsButton)).check(matches(withText("News")));
        onView(withId(R.id.eventsButton)).check(matches(withText("Events")));
        onView(withId(R.id.weatherBtn)).check(matches(withText("Weather")));
    }

    @Test
    public void placeNameCheck() {
        onView(withId(R.id.place_autocomplete_fragment))
                .perform(click());
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        String location = "Delhi";
        KeyCharacterMap keyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
        KeyEvent[] events = keyCharacterMap.getEvents(location.toCharArray());
        for(int eventsIdx=0; eventsIdx < events.length; eventsIdx++){
            if(events[eventsIdx].getAction() == KeyEvent.ACTION_DOWN) {
                device.pressKeyCode(events[eventsIdx].getKeyCode());
            }
        }
        device.findObject(By.text("India")).click();
        onView(withId(R.id.placeName)).check(matches(withText("Delhi")));
    }
}
