package com.thealienobserver.nikhil.travon;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import com.thealienobserver.nikhil.travon.adapters.RecommendedFragmentPagerAdapter;
import com.thealienobserver.nikhil.travon.controllers.MainActivity;
import com.thealienobserver.nikhil.travon.controllers.MainMenuActivity;
import com.thealienobserver.nikhil.travon.controllers.NewsActivity;
import com.thealienobserver.nikhil.travon.controllers.RecommendedPlacesActivity;
import com.thealienobserver.nikhil.travon.fragments.RecommendedPlacesFragment;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertThat;

public class PlacesActivityInstumentedTest {

    @Rule
    public ActivityTestRule<RecommendedPlacesActivity> recommendedPlacesActivityActivityTestRule
            = new ActivityTestRule<RecommendedPlacesActivity>(RecommendedPlacesActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, RecommendedPlacesActivity.class);
            result.putExtra(MainMenuActivity.CITY, "Halifax");
            return result;
        }
    };
    private String tabTitles[] = new String[]{"Attractions", "Hospitals", "Universities", "Restaurants"};
    private RecommendedPlacesActivity recommendedPlacesActivity;

    @Before
    public void setup() {
        this.recommendedPlacesActivity = recommendedPlacesActivityActivityTestRule.getActivity();
    }

    @Test
    public void placesTabsCheck() {
        for(int tabIdx = 0; tabIdx<tabTitles.length; tabIdx++) {
            // Select the tabs one by one
            onView(allOf(withText(tabTitles[tabIdx]), isDescendantOfA(withId(R.id.sliding_tabs))))
                    .perform(click())
                    .check(matches(isDisplayed()));

            // Check current fragment title is as expected
            ViewPager slidingTabs = recommendedPlacesActivity.findViewById(R.id.viewpager);
            String title = slidingTabs.getAdapter().getPageTitle(slidingTabs.getCurrentItem()).toString();
            assertThat(title, Matchers.equalTo(tabTitles[tabIdx]));
        }
    }
}
