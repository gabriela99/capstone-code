package com.example.capstone_code.cardview;

import android.app.Activity;
import android.os.Bundle;

import com.example.capstone_code.R;


/**
 * Launcher Activity for the CardView sample app.
 */

public class ColleagueCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleague_card);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, ColleagueCardFragment.newInstance())
                    .commit();
        }
    }
}
