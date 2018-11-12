package com.example.android.assignment3_pac;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> mLoginRule = new ActivityTestRule<>(Login.class);

    @Test
    public void loginPageExists(){
        onView(withText("Login")).check(matches(isDisplayed()));
    }
/*
    @Test
    public void adminLogin(){
        try {
            onView(withId(R.id.username_field)).perform(typeText("admin"));
            onView(withId(R.id.password_field)).perform(typeText("hunter2"));
            closeSoftKeyboard();

            onView(withId(R.id.login_button)).perform(click());

            onView(withText("AdminHome")).check(matches(isDisplayed()));
        }
        catch(Exception e){

        }
    }

    @Test
    public void createUserAccount(){
        try {
            onView(withId(R.id.username_field)).perform(typeText("admin"));
            onView(withId(R.id.password_field)).perform(typeText("hunter2"));
            closeSoftKeyboard();

            onView(withId(R.id.login_button)).perform(click());

            onView(withId(R.id.account_create)).perform(click());

            onView(withId(R.id.create_username_field)).perform(typeText("user"));
            onView(withId(R.id.create_password_field)).perform(typeText("pass"));
            closeSoftKeyboard();

            onView(withId(R.id.create_account_button)).perform(click());

            onView(withText("Success")).check(matches(isDisplayed()));
        }
        catch (Exception e){

        }
    }
*/
}
