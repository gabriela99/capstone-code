package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout;
    Button btnSettings;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mCustomerDatabase;
    private ImageView mProfileImage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to xml
        setContentView(R.layout.activity_profile);

        // Connect to buttons in xml
        btnLogout = findViewById(R.id.btnLogout);
        btnSettings = findViewById(R.id.btnSettings);


        // Display information
        getUserInfo();

        // Logout button that brings user back to login page
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

        // Settings button that brings user to settings page
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance();
                Intent intToMain = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intToMain);
            }
        });
    }

    private void getUserInfo() {
//
//        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                String name = String.valueOf(dataSnapshot.child("name").getValue());
////                String data = dataSnapshot.child("NAME").getValue(String.class);
////
////                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////
////                final TextView mEmail = findViewById(R.id.email);
////                final TextView mUserName = findViewById(R.id.etUserName);
////                final TextView mRole = findViewById(R.id.etRole);
////                final TextView mSkills = findViewById(R.id.etSkills);
////
////                mEmail.setText(user.getEmail());
//
////                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
////                    Map<String, Object> map = (Map<String, Object>) postSnapshot.getValue();
////                    if (map.get("name") != null) {
////                        String name = map.get("name").toString();
////                        mUserName.setText(name);
////                    }
////                    if (map.get("role") != null) {
////                        String role = map.get("role").toString();
////                        mRole.setText(role);
////                    }
////                    if (map.get("skills") != null) {
////                        String skills = map.get("skills").toString();
////                        mSkills.setText(skills);
////                    }
////                    String name = map.get("name").toString();
////                    mUserName.setText(name);
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}


