package com.example.capstone_code.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.capstone_code.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Take information from adapter and pass into xml fields for colleagues
 * in order to see relevant information when colleague profile is viewed
 */
public class ColleagueProfileActivity extends AppCompatActivity {

    private static final String TAG = "ColleagueProfileActivit";
    private Toolbar mToolbar;

    /**
     * Takes current state and applies corresponding XML
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleague_profile);
        Log.d(TAG, "onCreate: started");

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
//            String colleagueEmail = getIntent().getStringExtra("colleague_email");

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

    public void contactColleague(View view) {
//        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//        emailIntent.setData(Uri.parse("mailto:abc@xyz.com"));
//        startActivity(Intent.createChooser(emailIntent, "Send feedback"));

        Intent intent = new Intent(ColleagueProfileActivity.this, UserListActivity.class);
        startActivity(intent);
    }

    /**
     * Fill the toolbar with the menu from the XML file and the search view
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_profile_menu, menu);
        return true;
    }

    /**
     * When profile is selected, user sent to profile
     * When logout is selected, user is logged out
     * @param item selected field from menu list
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.users_icon:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout_icon:
                FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
                mFirebaseAuth.signOut();
                Intent intent_logout = new Intent(this, LoginActivity.class);
                startActivity(intent_logout);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
