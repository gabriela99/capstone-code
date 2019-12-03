package com.example.capstone_code.utils;

import android.content.Context;
import android.os.Handler;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

/**
 * Creates dropdown list with autocomplete options
 */
public class autoCompleteText {
    private String[] listOptions;
    private EditText mField;
    private Context mContext;

    /**
     *
     * @param listOptions
     * @param mField
     */
    public void autoCompleteText(String[] listOptions, EditText mField, Context context) {
        //Create the instance of ArrayAdapter containing list of roles
        ArrayAdapter<String> adapterRoles = new ArrayAdapter<>(context,android.R.layout.select_dialog_singlechoice, listOptions);

        //Find TextView control
        final AutoCompleteTextView acTextViewRoles = (AutoCompleteTextView) mField;

        //Set the number of characters the user must type before the drop down list is shown
        acTextViewRoles.setThreshold(0);

        //Set the adapter
        acTextViewRoles.setAdapter(adapterRoles);

        // When the user first focuses on the field, show all options
        acTextViewRoles.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            /**
             *
             * @param textView
             * @param hasFocus
             */
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

            /**
             * After the text is done changing, check the value of the text view
             * @param s
             */
            @Override
            public void afterTextChanged (android.text.Editable s) {
                // If the length of the string equals 0 (so an empty input), call
                // again the showDropdown() function
                if (acTextViewRoles.getText().toString().length() == 0) {
                    // Handler to run after delay https://developer.android.com/reference/android/os/Handler
                    final Handler handler = new Handler();
                    // Waits 100 milliseconds before executing showDropDown()
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            acTextViewRoles.showDropDown();
                        }
                    }, 100);
                }
            }
        });
    }
}
