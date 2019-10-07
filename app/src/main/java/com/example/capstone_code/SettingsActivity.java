package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private ImageView mProfileImage;
    private EditText mUserName, mEmail, mRole, mSkills;
    private Button confirmSettings, backToProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        mUserName = (EditText) findViewById(R.id.etUserName);
        mEmail = (EditText) findViewById(R.id.etEmail);
        mRole = (EditText) findViewById(R.id.etRole);
        mSkills = (EditText) findViewById(R.id.etSkills);
        confirmSettings = (Button) findViewById(R.id.btnConfirmSettings);
        backToProfile = (Button) findViewById(R.id.btnBackToProfile);


        backToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });



    }
}
