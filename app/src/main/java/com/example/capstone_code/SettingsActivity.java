package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

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
        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("name")!=null){
                        String name = map.get("name").toString();
                        mUserName.setText(name);
                    }
                    if(map.get("role")!=null){
                        String role = map.get("role").toString();
                        mRole.setText(role);
                    }
                    if(map.get("skills")!=null){
                        String skills = map.get("skills").toString();
                        mSkills.setText(skills);
                    }
                    Glide.with(mProfileImage).clear(mProfileImage);
                    if(map.get("profileImageUrl")!=null){
                        String profileImageUrl = map.get("profileImageUrl").toString();
                        switch(profileImageUrl){
                            case "default":
                                Glide.with(getApplication()).load(R.mipmap.ic_launcher).into(mProfileImage);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImageUrl).into(mProfileImage);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    private void saveUserInformation() {

    }
}
