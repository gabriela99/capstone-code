package com.example.capstone_code.viewmodel;

import android.os.Bundle;
import android.util.Log;

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
    }

}
