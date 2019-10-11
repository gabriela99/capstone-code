package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout;
    Button btnSettings;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        displayUserInformation();

        btnLogout = findViewById(R.id.logout);
        btnSettings = findViewById(R.id.settings);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance();
                Intent intToMain = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intToMain);
            }
        });
    }
    private void displayUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        TextView email = (TextView)findViewById(R.id.email);
        email.setText(user.getEmail());

        TextView name = (TextView)findViewById(R.id.etUserName);
        name.setText(user.getUid());

        //        TextView role = (TextView)findViewById(R.id.etRole);
//        role.setText(mFirebaseAuth.getInstance().getCurrentUser().getRole());
//
//        TextView skills = (TextView)findViewById(R.id.etSkills);
//        skills.setText(mFirebaseAuth.getInstance().getCurrentUser().getSkills());
    }
}
