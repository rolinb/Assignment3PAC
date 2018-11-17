package com.example.android.assignment3_pac;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CameraTest {

  @Rule
  public ActivityTestRule<Login> mLoginRule = new ActivityTestRule<>(Login.class);

  @Test
  public void cameraInfoTest() {

    //onView(withId(R.id.create_username_field)).perform(typeText("user"));
    //onView(withId(R.id.create_password_field)).perform(typeText("pass"));
    //closeSoftKeyboard();

    onView(withId(R.id.user_login_button)).perform(click());
    onView(withId(R.id.camera_button)).perform(click());

    onView(withContentDescription("Button 0")).perform(click());
    onView(withId(R.id.cameraSees)).check(matches(isDisplayed()));
    onView(withId(R.id.cameraOnOff)).check(matches(isDisplayed()));
    onView(withId(R.id.Record)).check(matches(isDisplayed()));
    onView(withId(R.id.DeviceInformation)).check(matches(isDisplayed()));
  }

  @Test
  public void checkToggle() {

    //onView(withId(R.id.create_username_field)).perform(typeText("user"));
    //onView(withId(R.id.create_password_field)).perform(typeText("pass"));
    //closeSoftKeyboard();

    onView(withId(R.id.user_login_button)).perform(click());
    onView(withId(R.id.camera_button)).perform(click());

    onView(withContentDescription("Button 0")).perform(click());

    onView(withId(R.id.Record)).perform(click());

    mainController.controller.getCameras().get(0).setStatus(Status.NORMAL);

    assert (mainController.controller.getCameras().get(0).getIsRecording() == true);

    onView(withId(R.id.Record)).perform(click());

    assert (mainController.controller.getCameras().get(0).getIsRecording() == false);

    onView(withId(R.id.cameraOnOff)).perform(click());

    assert (mainController.controller.getCameras().get(0).getStatus() == Status.OFF);
  }

}