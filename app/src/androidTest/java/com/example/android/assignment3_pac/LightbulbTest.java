package com.example.android.assignment3_pac;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import com.example.android.assignment3_pac.assn2.mainController;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LightbulbTest {

    @Rule
    public ActivityTestRule<Login> mLoginRule = new ActivityTestRule<>(Login.class);

    @Test
    public void checkToggle(){

        //onView(withId(R.id.create_username_field)).perform(typeText("user"));
        //onView(withId(R.id.create_password_field)).perform(typeText("pass"));
        //closeSoftKeyboard();

        onView(withId(R.id.user_login_button)).perform(click());
        onView(withId(R.id.lightbulb_button)).perform(click());

        onView(withContentDescription("Button 0")).perform(click());

        assert(mainController.controller.getLightbulbs().get(0).getCondition() == true);

        onView(withContentDescription("Button 0")).perform(click());

        assert(mainController.controller.getSmartPlugs().get(0).getCondition() == false);
    }

}