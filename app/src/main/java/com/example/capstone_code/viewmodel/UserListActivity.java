package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_code.R;
import com.example.capstone_code.model.User;
import com.example.capstone_code.utils.FirebaseDatabaseHelper;
import com.example.capstone_code.view.UsersAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Generate user list as a recycler view from firebase data
 * Allow users to filter user list with search function within toolbar
 */
public class UserListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    /**
     * Initialize toolbar and recyclerview of users list
     * @param savedInstanceState continue to work with current state - logged in user
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        // Connect to XML
        mRecyclerView = findViewById(R.id.recyclerview_users);
        mToolbar = findViewById(R.id.toolbar);

        // Ensure smooth scrolling: https://stackoverflow.com/questions/31249252/how-to-make-recyclerview-scroll-smoothly
        mRecyclerView.setNestedScrollingEnabled(false);


        setSupportActionBar(mToolbar);

        new FirebaseDatabaseHelper().readUsers(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {
                UsersAdapter mUsersAdapter = new UsersAdapter(users, keys);

                // Layout Manager defines how recycler view (user list) will look
                mRecyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));

                // Fills the recycler view (user list) with content from database via adapter
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

    /**
     * Fill the toolbar with the menu from the XML file and the search view
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * When profile is selected, user sent to profile
     * When logout is selected, user is logged out
     * @param item selected field from menu list
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_icon:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.sensor_icon:
                Intent sensor_intent = new Intent(this, SensorActivity.class);
                startActivity(sensor_intent);
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

    /**
     * For use when query is to be made only after clicking on the search button
     * @param query entire query input from user
     * @return false since onQueryTextChange is used
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * For use when query is to be made with each character typed
     * @param newText each new character input from user
     * @return true
     */
    @Override
    public boolean onQueryTextChange(String newText) {

        // See user input in logcat
        Log.d(TAG, "User input is: " + newText);

        // Remove capitalization from user input
        final String userInput = newText.toLowerCase().trim();

        // Instantiate firebase database to see users found within the database
        new FirebaseDatabaseHelper().readUsers(new FirebaseDatabaseHelper.DataStatus() {

            /**
             * Upon text input from user and instatiation of firebase data,
             * the data is retrieved and filtered to show results matching the user input
             * @param colleagues empty list of users to be passed into adapter
             * @param keys empty list of keys to be passed into adapter
             */
            @Override
            public void DataIsLoaded(List<User> colleagues, List<String> keys) {

                // Instantiation of UsersAdapter to use updateColleagueList
                UsersAdapter usersAdapter = new UsersAdapter(colleagues, keys);

                // Instantiation of empty array list
//                List<User> newList = new ArrayList<>();

                // Fill the user list from the users adapter
                colleagues = usersAdapter.getmUserList();

                Log.d(TAG, "All colleagues in unfiltered list: " + colleagues.toString());

                List<User> newList = searchUsers(colleagues, userInput);

                // Renew the recycler view with filtered list
                usersAdapter.updateColleagueList(newList);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));
                mRecyclerView.setAdapter(usersAdapter);
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
        return true;

    }

    /**
     * Ensure user input from onQueryTextChange remains upon resume, eg rotate screen
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        

    }

    public List<User> searchUsers(List<User> colleagues, String userInput) {
        List<User> newList = new ArrayList<>();

        for (User colleague : colleagues) {
            Log.d(TAG, colleague.getName());
            // if the colleagues name contains the queried text, add it to the results
            if (colleague.getName().toLowerCase().contains(userInput)
                    || colleague.getSkills().toLowerCase().contains(userInput)
                    || colleague.getRole().toLowerCase().contains(userInput)) {
                newList.add(colleague);
                Log.d(TAG, "All colleagues in filtered list: " + newList.toString());
            }
        }
        return newList;
    }
}


