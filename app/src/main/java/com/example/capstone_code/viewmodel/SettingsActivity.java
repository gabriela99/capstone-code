package com.example.capstone_code.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_code.R;
import com.example.capstone_code.utils.autoCompleteText;
import com.example.capstone_code.utils.getUserInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private ImageView mProfileImage;
    private EditText mUserName, mRole, mSkills;
    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;
    private String userId, name, role, skills, profileImageUrl;

    private Uri resultUri;
    private String[] roleOptions = { "Interaction Designer", "Software Engineer", "Product Manager" };
    private String[] skillOptions = { "Design thinking", "Agile", "Interpersonal skills", "Java" };

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findLayoutFields();
        instantiateFirebase();
        pickProfileImage();

        autoCompleteText autoCompleteText = new autoCompleteText();
        autoCompleteText.autoCompleteText(roleOptions, mRole, this);
        autoCompleteText.autoCompleteText(skillOptions, mSkills, this);

        getUserInfo getUserInfo = new getUserInfo();
        getUserInfo.getUserInfoFromDatabase(mUserName, mRole, mSkills);

    }

    /**
     * Find the corresponding xml fields
     */
    private void findLayoutFields() {
        mProfileImage = findViewById(R.id.ivProfileImage);
        mUserName = findViewById(R.id.etUserName);
        mRole = findViewById(R.id.etRole);
        mSkills = findViewById(R.id.etSkills);
    }

    private void instantiateFirebase() {
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
    }

    private void pickProfileImage() {
        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }


    /**
     *
     * @param listOptions
     * @param userInput
     * @return
     */
    private boolean isStringInList(String[] listOptions, String userInput) {
        boolean elementFound = false;
        // Check an item is in an array https://www.geeksforgeeks.org/check-if-a-value-is-present-in-an-array-in-java/
        for (String validItem : listOptions) {
            if (userInput.equalsIgnoreCase(validItem)) {
                elementFound = true;
            }
        }
        return elementFound;
    }

    /**
     * Checks that user input conforms to the list of options or is empty
     * @return  true if valid input (item from role or skill list or empty), false if invalid
     */
    private boolean validateInput() {
        // Retrieve what the user inputted into the fields and trim to remove whitespace
        name = mUserName.getText().toString().trim();
        role = mRole.getText().toString().trim();
        skills = mSkills.getText().toString().trim();
        
        // Do the check for both fields if role or skill in list or not yet filled
        boolean isNameValid = name.length() >= 1;
        boolean isRoleValid = isStringInList(roleOptions, role) || role.equals("");
        boolean isSkillsValid = isStringInList(skillOptions, skills) || skills.equals("");

        // If both role and skills are from the predefined list, return true for valid settings
        if (isNameValid && isRoleValid && isSkillsValid) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    private void saveUserInformation() {
        name = mUserName.getText().toString();
        role = mRole.getText().toString();
        skills = mSkills.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name", name);
        userInfo.put("role", role);
        userInfo.put("skills", skills);

        mCustomerDatabase.updateChildren(userInfo);
        if(resultUri != null){
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profileImages").child(userId);
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finish();
                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    Map userInfo = new HashMap();
                    userInfo.put("profileImageUrl", downloadUrl.toString());
                    mCustomerDatabase.updateChildren(userInfo);

                    finish();
                    return;
                }
            });
        }else{
            finish();
        }
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            mProfileImage.setImageURI(resultUri);
        }
    }

    /**
     * Calls save user information if valid input, and gives error if not
     * @param view current state
     */
    public void saveSettings(View view) {
        boolean isInputValid = validateInput();
        if (isInputValid) {
            saveUserInformation();
        } else {
            Toast.makeText(SettingsActivity.this,"Invalid Input! Please check that selected role and skills match the available options.",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Redirects user from current page to profile page
     * @param view current state
     */
    public void backToProfile(View view) {
        Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
