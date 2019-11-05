package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_code.R;
import com.example.capstone_code.model.User;
import com.example.capstone_code.utils.FirebaseDatabaseHelper;
import com.example.capstone_code.view.UsersAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mRecyclerView = findViewById(R.id.recyclerview_users);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new FirebaseDatabaseHelper().readUsers(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                UsersAdapter mUsersAdapter = new UsersAdapter(users, keys);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));
                mRecyclerView.setAdapter(mUsersAdapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_icon:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout_icon:
                FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseAuth.signOut();
                Intent intent_logout = new Intent(this, LoginActivity.class);
                startActivity(intent_logout);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

//    /**
//     * Triggered when the user clicks on the "profile" button (id/btnGoToProfile)
//     * Redirects the user from the user list page to the profile page of the active user
//     * @param view the View it is associated with the goToProfile button
//     */
//    public void goToProfile(View view) {
//        Intent intent = new Intent(UserListActivity.this, ProfileActivity.class);
//        startActivity(intent);
//    }
}

