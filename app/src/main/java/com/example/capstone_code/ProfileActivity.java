package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout;
    Button btnSettings;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference reference;
    private ImageView mProfileImage;
    private arrayAdapter arrayAdapter;
    List<userDetails> rowItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to xml
        setContentView(R.layout.activity_profile);

        // Connect to in xml
        btnLogout = findViewById(R.id.btnLogout);
        btnSettings = findViewById(R.id.btnSettings);

        // Create Firebase instance and get user
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser();

        // Display information
        rowItems = new ArrayList<userDetails>();
        arrayAdapter = new arrayAdapter(this, R.layout.activity_main, rowItems);
        displayUserInfo();
    }


    public void displayUserInfo() {
        // Set up reference to user collection in Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    userDetails userDetails = new userDetails(dataSnapshot.getKey(), dataSnapshot.child("name").getValue().toString());
                    rowItems.add(userDetails);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editSettings(View view) {
        // Instantiate Firebase
        FirebaseAuth.getInstance();

        // Send user from profile to settings
        Intent intToSettings = new Intent(ProfileActivity.this, SettingsActivity.class);
        startActivity(intToSettings);
        return;
    }

    public void logoutUser(View view) {
        // Sign user out
        mFirebaseAuth.signOut();

        // Send user from profile to login page
        Intent intToMain = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intToMain);
        finish();
        return;
    }
}
