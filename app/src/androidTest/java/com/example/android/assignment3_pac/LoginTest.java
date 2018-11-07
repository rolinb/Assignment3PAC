package com.example.android.assignment3_pac;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> mLoginRule = new ActivityTestRule<>(Login.class);

    @Test
    public void verifyButtonsExist(){

        Espresso.onView(withId(R.id.admin_login_button)).perform((click()));


    }

}
