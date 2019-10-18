package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        mRecyclerView = findViewById(R.id.recyclerview_users);
        new FirebaseDatabaseHelper().readUsers(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, UserListActivity.this, users, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    public void goToProfile() {
        // Send user from user list to profile
        Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
        startActivity(intent);
        return;
    }
}

