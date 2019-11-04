package com.example.capstone_code.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_code.R;

public class ColleagueProfileActivity extends AppCompatActivity {

    private static final String TAG = "ColleagueProfileActivit";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleague_profile);
        Log.d(TAG, "onCreate: started");

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // Ensure the intent has extras to prevent app from crashing if it does not
        if(getIntent().hasExtra("colleague_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

//            String imageUrl =  getIntent().getStringExtra("image_url");
            String colleagueName = getIntent().getStringExtra("colleague_name");
            String colleagueRole = getIntent().getStringExtra("colleague_role");
            String colleagueSkills = getIntent().getStringExtra("colleague_skills");


            setInfo(colleagueName, colleagueRole, colleagueSkills);
        }
    }

    private void setInfo(String colleagueName, String colleagueRole, String colleagueSkills) {
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView name = findViewById(R.id.tvNameColleague);
        TextView role = findViewById(R.id.tvRoleColleague);
        TextView skills = findViewById(R.id.tvSkillsColleague);

        name.setText(colleagueName);
        role.setText(colleagueRole);
        skills.setText(colleagueSkills);

//        ImageView image = findViewById(R.id.ivProfileColleague);

//        Glide.with(this)
//                .asBitmap()
//                .load(imageUrl)
//                .into(image);
    }
}
