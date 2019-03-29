package io.supercharge.testingexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import io.supercharge.testingexample.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun refreshButton_shouldDisplayInUi() {
        onView(withId(R.id.refreshButton)).check(matches(isDisplayed()))
    }

    @Test
    fun noteList_shouldDisplayInUi() {
        onView(withId(R.id.refreshButton)).perform(click())

        onView(withId(R.id.noteList)).check(matches(isDisplayed()))
    }
}
