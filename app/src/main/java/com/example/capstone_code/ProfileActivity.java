package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout;
    Button btnSettings;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference reference;
    private ImageView mProfileImage;
    private TextView mEmail, mUserName, mRole, mSkills;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to xml
        setContentView(R.layout.activity_profile);

        // Connect to buttons in xml
        btnLogout = findViewById(R.id.btnLogout);
        btnSettings = findViewById(R.id.btnSettings);

        // Display information
//        getUserInfo();


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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.etEmail);
        mUserName = findViewById(R.id.etUserName);
        mRole = findViewById(R.id.etRole);
        mSkills = findViewById(R.id.etSkills);
        mUser = mFirebaseAuth.getCurrentUser();

//        mEmail.setText(mUser.getEmail());

        reference = FirebaseDatabase.getInstance().getReference().child(mUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

//                String usernameFire = dataSnapshot.child("name").getValue().toString();
//                String roleFire = dataSnapshot.child("role").getValue().toString();
//                String skillsFire = dataSnapshot.child("skills").getValue().toString();
//                String usernameFire = map.get("name").toString();
//
//                mUserName.setText(usernameFire);
//                mRole.setText(roleFire);
//                mSkills.setText(skillsFire);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    private void getUserInfo() {
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mEmail = findViewById(R.id.etEmail);
//        mUserName = findViewById(R.id.etUserName);
//        mRole = findViewById(R.id.etRole);
//        mSkills = findViewById(R.id.etSkills);
//        mUser = mFirebaseAuth.getCurrentUser();
//
////        mEmail.setText(mUser.getEmail());
//
//        reference = FirebaseDatabase.getInstance().getReference().child(mUser.getUid());
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String usernameFire = dataSnapshot.child("name").getValue().toString();
//                String roleFire = dataSnapshot.child("role").getValue().toString();
//                String skillsFire = dataSnapshot.child("skills").getValue().toString();
//
//                Toast.makeText(getApplicationContext(), usernameFire, Toast.LENGTH_LONG).show();
////                mUserName.setText(usernameFire);
////                mRole.setText(roleFire);
////                mSkills.setText(skillsFire);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });












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

