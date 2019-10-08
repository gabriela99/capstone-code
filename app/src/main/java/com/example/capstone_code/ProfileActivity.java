package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout;
    Button btnSettings;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.logout);
        btnSettings = findViewById(R.id.settings);

        TextView email = (TextView)findViewById(R.id.email);
        email.setText(mFirebaseAuth.getInstance().getCurrentUser().getEmail());

//        TextView name = (TextView)findViewById(R.id.etUserName);
//        name.setText(mFirebaseAuth.getInstance().getCurrentUser().getDisplayName());

//        TextView role = (TextView)findViewById(R.id.etRole);
//        role.setText(mFirebaseAuth.getInstance().getCurrentUser().get???());
//
//        TextView skills = (TextView)findViewById(R.id.etSkills);
//        skills.setText(mFirebaseAuth.getInstance().getCurrentUser().get???());

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
}
