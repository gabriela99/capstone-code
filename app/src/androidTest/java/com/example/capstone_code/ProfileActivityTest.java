package com.example.capstone_code;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.example.capstone_code.viewmodel.ProfileActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<ProfileActivity> mProfileTestRule = new ActivityTestRule <ProfileActivity>(ProfileActivity.class);

//    private String mEmail = "nala@puppy.com";
//    private String mName = "Nala";
//    private String mRole = "Interaction Design";
//    private String mSkills = "Agile";


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreateTest() {
//        // Display user information
//        Espresso.onView(withId(R.id.tvEmailProfile)).check(matches(withText(mEmail)));
//        Espresso.onView(withId(R.id.tvNameProfile)).check(matches(withText(mName)));
//        Espresso.onView(withId(R.id.tvRoleProfile)).check(matches(withText(mRole)));
//        Espresso.onView(withId(R.id.tvSkillsProfile)).check(matches(withText(mSkills)));
    }

    @Test
    public void editSettingsTest() {
        // Perform button click
        Espresso.onView(withId(R.id.btnSettings)).perform(click());

    }

    @Test
    public void logoutUserTest() {
    }

    @Test
    public void goToListTest() {
        // Perform button click
        Espresso.onView(withId(R.id.btnGoToList)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
    }
}