package com.example.capstone_code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<userDetails> {

    public arrayAdapter(Context context, int resourceId, List<userDetails> items){
        super(context, resourceId, items);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        userDetails userItem = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_profile, parent, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.etUserName);

        userName.setText(userItem.getName());

        return convertView;

    }
}
