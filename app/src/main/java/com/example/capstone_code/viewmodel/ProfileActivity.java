package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.capstone_code.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Displays current user information, toolbar, and allows user to redirect to edit page
 */
public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mUser;
    private Toolbar mToolbar;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Connect to Firebase
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("Users").child(uid);


        ValueEventListener valueEventListener = new ValueEventListener() {

            /**
             * Display the users email, name, role and skills by fetching information
             * from Firebase and connecting to the XML by finding each field by their id's
             * @param dataSnapshot snapshot of current user information
             */
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Fetch information from Firebase for the user with the data snapshot
                Object dbUserName = dataSnapshot.child("name").getValue();
                Object dbRole = dataSnapshot.child("role").getValue();
                Object dbSkills = dataSnapshot.child("skills").getValue();

                // Ensure the user data is not null, if it is then have an empty string
                String mUserName = dbUserName != null ? dbUserName.toString() : "";
                String mRole = dbRole != null ? dbRole.toString() : "";
                String mSkills = dbSkills != null ? dbSkills.toString() : "";

                // Test to see if working
                Log.d("TAG", mUserName + " / " + mRole + " / " + mSkills);

                // Connect to XML by finding each id
                TextView email = findViewById(R.id.tvEmailProfile);
                TextView name = findViewById(R.id.tvNameProfile);
                TextView role = findViewById(R.id.tvRoleProfile);
                TextView skills = findViewById(R.id.tvSkillsProfile);

                // Fill XML fields with data from Firebase
                email.setText(mUser.getEmail());
                name.setText(mUserName);
                role.setText(mRole);
                skills.setText(mSkills);

            }

            /**
             * If there is a firebase error, then return a pop up error message
             * @param databaseError value listener returns an error
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        uidRef.addValueEventListener(valueEventListener);
    }

    // Edit //
    /**
     * Triggered when the user clicks on the "edit" button (id/btnSettings)
     * Redirects the user from the profile page to the settings page
     * @param view the View it is associated with the editSettings button
     */
    public void editSettings(View view) {
        // Instantiate Firebase
        FirebaseAuth.getInstance();

        // Send user from profile to settings
        Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    // Toolbar //
    /**
     * Takes user to previous page
     * @return true if back button is pressed
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Fill the toolbar with the menu from the XML file and the search view
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_profile_menu, menu);
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
            case R.id.users_icon:
                Intent intent = new Intent(this, UserListActivity.class);
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
}