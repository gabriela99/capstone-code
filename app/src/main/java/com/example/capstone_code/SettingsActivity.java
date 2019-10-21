package com.example.capstone_code;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private Button confirmSettings, backToProfile;
    private FirebaseAuth mAuth;
    private DatabaseReference mCustomerDatabase;
    private String userId, name, role, skills, profileImageUrl;

    private Uri resultUri;
    String[] roleOptions = { "Interaction Designer", "Software Engineer", "Product Manager" };
    String[] skillOptions = { "Design thinking", "Agile", "Interpersonal skills", "Java" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        mUserName = (EditText) findViewById(R.id.etUserName);
        mRole = (EditText) findViewById(R.id.etRole);
        mSkills = (EditText) findViewById(R.id.etSkills);
        confirmSettings = (Button) findViewById(R.id.btnConfirmSettings);
        backToProfile = (Button) findViewById(R.id.btnBackToProfile);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        getUserInfo();

        // Allow the user to type in a character and have autocomplete options provided from the options lists
        autoCompleteText(roleOptions, mRole);
        autoCompleteText(skillOptions, mSkills);

        confirmSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the inputs are valid before saving
                boolean isInputValid = validateInput();
                if (isInputValid) {
                    saveUserInformation();
                } else {
                    // Show an error message if the inputs are not valid
                    Toast.makeText(SettingsActivity.this,"Invalid Input! Please check that selected role and skills match the available options.",Toast.LENGTH_SHORT).show();
                }
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
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if (map.get("name") != null) {
                        name = map.get("name").toString();
                        mUserName.setText(name);
                    }
                    if (map.get("role") != null) {
                        String role = map.get("role").toString();
                        mRole.setText(role);
                    }
                    if (map.get("skills") != null) {
                        String skills = map.get("skills").toString();
                        mSkills.setText(skills);
                    }
                    Glide.with(mProfileImage).clear(mProfileImage);
                    if (map.get("profileImageUrl") != null) {
                        profileImageUrl = map.get("profileImageUrl").toString();
                        switch (profileImageUrl) {
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
    }

    private void autoCompleteText(String[] listOptions, EditText mField) {
        //Create the instance of ArrayAdapter containing list of roles
        ArrayAdapter<String> adapterRoles = new ArrayAdapter<>(this,android.R.layout.select_dialog_singlechoice, listOptions);

        //Find TextView control
        final AutoCompleteTextView acTextViewRoles = (AutoCompleteTextView) mField;

        //Set the number of characters the user must type before the drop down list is shown
        acTextViewRoles.setThreshold(0);

        //Set the adapter
        acTextViewRoles.setAdapter(adapterRoles);

        // When the user first focuses on the field, show all options
        acTextViewRoles.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View textView, boolean hasFocus) {
                // If the view is focused, show the dropdown
                if(hasFocus){
                    acTextViewRoles.showDropDown();
                }
            }
        });

        // When the user deletes a character, the options list is shown again if
        // it matches an option(s) in the list or if there is no longer any
        // characters in the field
        acTextViewRoles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged (android.text.Editable s) {
                // After the text is done changing, check the value of the text view
                // If the length of the string equals 0 (so an empty input), call
                // again the showDropdown() function
                if(acTextViewRoles.getText().toString() == ""){
                    acTextViewRoles.showDropDown();
                }
            }
        });
    }

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

    // Checks that user input conforms to the list of options: true if valid input, false if invalid
    private boolean validateInput() {
        // Retrieve what the user inputted into the fields
        role = mRole.getText().toString();
        skills = mSkills.getText().toString();
        // Do the check for both fields
        boolean isRoleValid = isStringInList(roleOptions, role);
        boolean isSkillsValid = isStringInList(skillOptions, skills);
        // If both role and skills are from the predefined list, return true for valid settings
        if (isRoleValid && isSkillsValid) {
            return true;
        } else {
            return false;
        }
    }


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            mProfileImage.setImageURI(resultUri);
        }
    }
}
