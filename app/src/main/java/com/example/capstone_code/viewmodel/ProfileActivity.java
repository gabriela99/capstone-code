package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_code.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *
 */
public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mUser;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to xml
        setContentView(R.layout.activity_profile);

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

    /**
     * Triggered when the user clicks on the "logout" button (id/btnLogout)
     * Logs the user out from the Firebase account associated with the current activity
     * Redirects the now logged out user from the profile page to the login page
     * @param view the View it is associated with the logoutUser button
     */
    public void logoutUser(View view) {
        // Sign user out
        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseAuth.signOut();

        // Send user from profile to login page
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);

//        try {
//            Object o = null;
//            Log.e("TEST", "causing an error on purpose");
//            o.equals(null);
//        } catch(Exception e) {
//            e.printStackTrace();
//            Log.e("TEST","error",e);
//        }
        finish();
    }

    /**
     * Triggered when the user clicks on the "user list" button (id/btnGoToList)
     * Redirects the user from the profile page to the settings page
     * @param view the View it is associated with the goToList button
     */
    public void goToList(View view) {
        // Send user from profile to user list page
        Intent intent = new Intent(ProfileActivity.this, UserListActivity.class);
        startActivity(intent);
    }
}