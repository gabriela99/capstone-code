package com.example.capstone_code;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect to xml
        setContentView(R.layout.activity_profile);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = mUser.getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("Users").child(uid);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String mUserName = dataSnapshot.child("name").getValue().toString();
                String mRole = dataSnapshot.child("role").getValue().toString();
                String mSkills = dataSnapshot.child("skills").getValue().toString();
                Log.d("TAG", mUserName + " / " + mRole + " / " + mSkills);

                TextView email = findViewById(R.id.tvEmailProfile);
                TextView name = findViewById(R.id.tvNameProfile);
                TextView role = findViewById(R.id.tvRoleProfile);
                TextView skills = findViewById(R.id.tvSkillsProfile);

                email.setText(mUser.getEmail());
                name.setText(mUserName);
                role.setText(mRole);
                skills.setText(mSkills);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void editSettings(View view) {
        // Instantiate Firebase
        FirebaseAuth.getInstance();

        // Send user from profile to settings
        Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
        startActivity(intent);
        return;
    }

    public void logoutUser(View view) {
        // Sign user out

        mFirebaseAuth.signOut();

        // Send user from profile to login page
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    public void goToList(View view) {
        // Send user from profile to user list page
        Intent intent = new Intent(ProfileActivity.this, UserListActivity.class);
        startActivity(intent);
        return;
    }
}
