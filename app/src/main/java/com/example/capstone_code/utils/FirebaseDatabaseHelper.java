package com.example.capstone_code.utils;

import androidx.annotation.NonNull;

import com.example.capstone_code.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceUsers;
    private List<User> users = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<User> users, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    /**
     * Instantiates Firebase and points to Users collection
     */
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceUsers = mDatabase.getReference("Users");
    }

    /**
     *
     * @param dataStatus
     */
    public void readUsers(final DataStatus dataStatus) {
        mReferenceUsers.addValueEventListener(new ValueEventListener() {

            /**
             *
             * @param dataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               users.clear();
               List<String> keys = new ArrayList<>();
               for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                   keys.add(keyNode.getKey());
                   User user = keyNode.getValue(User.class);
                   users.add(user);
               }
               dataStatus.DataIsLoaded(users, keys);
            }

            /**
             * If there is a Firebase error, then return a pop up error message
             * @param databaseError value listener returns an error
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


