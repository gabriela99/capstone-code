package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    private ImageView mProfileImage;
    private EditText mUserName, mEmail, mRole, mSkills;
    private Button confirmSettings, backToProfile;
    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;


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

        mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        getUserInfo();

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        confirmSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });

        backToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });



    }

    private void getUserInfo() {
    }

    private void saveUserInformation() {
        
    }
}
