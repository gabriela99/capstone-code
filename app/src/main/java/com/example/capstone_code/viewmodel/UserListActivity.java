package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
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

public class UserListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

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
        String userInput = newText.toLowerCase();
        List<User> newList = new ArrayList<>();
        List<User> colleagues = new ArrayList<>();

        // Loop through all the colleagues
        for (User colleague : colleagues) {
            // if the colleagues name contains the queried text, add it to the results
            if(colleague.getName().toLowerCase().contains(userInput)) {
                newList.add(colleague);
            }
        }

        UsersAdapter usersAdapter = new UsersAdapter(this, colleagues);
        usersAdapter.updateColleagueList(newList);

        // Tells searchview to handle onQueryTextChange instead of onQueryTextSubmit
        return true;
    }
}

