package com.example.capstone_code.utils;

import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class getUserInfo {
    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;
    private String userId, name, role, skills, profileImageUrl;

    public void getUserInfoFromDatabase(final EditText mUserName, final EditText mRole, final EditText mSkills) {
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                    Object name = map.get("name");
                    Object role = map.get("role");
                    Object skills = map.get("skills");

                    String cleanName = cleanMapField(name);
                    String cleanRole = cleanMapField(role);
                    String cleanSkills = cleanMapField(skills);

                    mUserName.setText(cleanName);
                    mRole.setText(cleanRole);
                    mSkills.setText(cleanSkills);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                return;
            }
        });

    }

    private String cleanMapField(Object field) {
        if (field != null) {
            return field.toString().trim();
        }
        return null;
    }
}
