package com.example.capstone_code.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_code.R;

/**
 * Take information from adapter and pass into xml fields for colleagues
 * in order to see relevant information when colleague profile is viewed
 */
public class ColleagueProfileActivity extends AppCompatActivity {

    private static final String TAG = "ColleagueProfileActivit";

    /**
     * Takes current state and applies corresponding XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleague_profile);
        Log.d(TAG, "onCreate: started");

        getIncomingIntent();
    }

    /**
     * Gets information from adapter to pass into XML fields
     */
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // Ensure the intent has extras to prevent app from crashing if it does not
        if(getIntent().hasExtra("colleague_name")
                && getIntent().hasExtra("colleague_role")
                && getIntent().hasExtra("colleague_skills")){

            Log.d(TAG, "getIncomingIntent: found intent extras.");

            // Get field information for colleague from adapter (putExtra)
//            String imageUrl =  getIntent().getStringExtra("image_url");
            String colleagueName = getIntent().getStringExtra("colleague_name");
            String colleagueRole = getIntent().getStringExtra("colleague_role");
            String colleagueSkills = getIntent().getStringExtra("colleague_skills");

            // Pass in information from adapter into corresponding XML fields
            setInfo(colleagueName, colleagueRole, colleagueSkills);
        }
    }

    /**
     * Fills the colleague fields with the corresponding passed in information
     * @param colleagueName name to be added to the view
     * @param colleagueRole role to be added to the view
     * @param colleagueSkills skills to be added to the view
     */
    private void setInfo(String colleagueName, String colleagueRole, String colleagueSkills) {
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        // Find XML fields
        TextView name = findViewById(R.id.tvNameColleague);
        TextView role = findViewById(R.id.tvRoleColleague);
        TextView skills = findViewById(R.id.tvSkillsColleague);

        // Fill XML fields with provided parameters
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
