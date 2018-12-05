package com.thealienobserver.nikhil.travon;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import com.thealienobserver.nikhil.travon.controllers.RecommendedPlacesActivity;
import com.thealienobserver.nikhil.travon.controllers.WeatherActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class WeatherActivityInstrumentedTest {
    @Rule
    public ActivityTestRule<WeatherActivity> weatherActivityActivityTestRule
            = new ActivityTestRule<WeatherActivity>(WeatherActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, WeatherActivity.class);
            result.putExtra("LATITUDE", 44.65054250000001);
            result.putExtra("LONGITUDE", -63.606195099999994);
            return result;
        }
    };
    private WeatherActivity weatherActivity;

    @Before
    public void setup() {
        this.weatherActivity = weatherActivityActivityTestRule.getActivity();
    }

    @Test
    public void checkCityName() {
        onView(withId(R.id.city)).check(matches(withText("HALIFAX")));
    }

    @Test
    public void checkCountOfTempratures() {
        RecyclerView recyclerView = this.weatherActivity.findViewById(R.id.weatherRecyclerView);
        int count = recyclerView.getAdapter().getItemCount();
        assertThat(count, Matchers.equalTo(40));
    }
}
