package com.example.capstone_code.viewmodel;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.rule.ActivityTestRule;

import com.example.capstone_code.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserListActivityTest {
    private UserListActivity mActivity;

    public ActivityTestRule<UserListActivity> rule = new ActivityTestRule<>(UserListActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mActivity = rule.launchActivity(intent);
    }

    @Test
    public void onQueryTextChange() {
        RecyclerView mRecyclerView;

        assertNotNull(mActivity);
        mActivity.onQueryTextChange("Software");
        mRecyclerView = mActivity.findViewById(R.id.recyclerview_users);

        assertNotNull(mRecyclerView);
        assertEquals(1, mRecyclerView.getChildCount());

    }

    @After
    public void tearDown() throws Exception {
    }
}