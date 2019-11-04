package com.example.capstone_code.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.capstone_code.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ColleagueProfileActivity extends AppCompatActivity {

    private static final String TAG = "ColleagueProfileActivit";
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleague_profile);
        Log.d(TAG, "onCreate: started");


// Connect to Firebase
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("Users").child(uid);

        /**
         * Display the users email, name, role and skills by fetching information
         * from Firebase and connecting to the XML by finding each field by their id's
         * @param dataSnapshot snapshot of current user information
         */
        ValueEventListener valueEventListener = new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Fetch information from Firebase for the user with the data snapshot
                String mUserName = dataSnapshot.child("name").getValue().toString();
                String mRole = dataSnapshot.child("role").getValue().toString();
                String mSkills = dataSnapshot.child("skills").getValue().toString();
                Log.d("TAG", mUserName + " / " + mRole + " / " + mSkills);

                // Connect to XML by finding each id
                TextView name = findViewById(R.id.tvNameColleague);
                TextView role = findViewById(R.id.tvRoleColleague);
                TextView skills = findViewById(R.id.tvSkillsColleague);

                // Fill XML fields with data from Firebase
//                email.setText(mUser.getEmail());
                name.setText(mUserName);
                role.setText(mRole);
                skills.setText(mSkills);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        uidRef.addValueEventListener(valueEventListener);
    }
    

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        // Ensure the intent has extras to prevent app from crashing if it does not
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("colleague_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl =  getIntent().getStringExtra("image_url");
            String colleagueName = getIntent().getStringExtra("colleague_name");

            setImage(imageUrl, colleagueName);
        }
    }

    private void setImage(String imageUrl, String colleagueName) {
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView name = findViewById(R.id.tvNameColleague);
        name.setText(colleagueName);

        ImageView image = findViewById(R.id.ivProfileColleague);

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }
}
