package com.example.capstone_code.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.rule.ActivityTestRule;

import com.example.capstone_code.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)

public class UserListActivityTest {
    private UserListActivity mActivity;

    @Rule
    public ActivityTestRule<UserListActivity> rule = new ActivityTestRule<>(UserListActivity.class, true, true);

    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {
        Intent intent = new Intent();
        mActivity = rule.launchActivity(intent);
//        mActivity = mock(UserListActivity.class);
    }

    @Test
    public void onQueryTextChangeTest() {
        RecyclerView mRecyclerView;

        assertNotNull(mActivity);
        mActivity.onQueryTextChange("Software");
        mRecyclerView = mActivity.findViewById(R.id.recyclerview_users);

        assertNotNull(mRecyclerView);
//        assertEquals(1, mRecyclerView.getChildCount());

    }

    @After
    public void tearDown() throws Exception {
    }
}