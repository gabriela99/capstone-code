package com.example.capstone_code.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_code.R;
import com.example.capstone_code.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

class UserItemView extends RecyclerView.ViewHolder {
    private TextView mName;
    private TextView mRole;
    private TextView mSkills;
    private String key;
    RelativeLayout parentLayout;
    CircleImageView image;

    public RelativeLayout getParentLayout() {
        return parentLayout;
    }

    public UserItemView(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.user_list_item, parent, false));

        image = itemView.findViewById(R.id.ivImage);
        mName = itemView.findViewById(R.id.tvName);
        mRole = itemView.findViewById(R.id.tvRole);
        mSkills = itemView.findViewById(R.id.tvSkills);
        parentLayout = itemView.findViewById(R.id.parent_layout);

    }

    public void bind(User user, String key) {
        mName.setText(user.getName());
        mRole.setText(user.getRole());
        mSkills.setText(user.getSkills());
        this.key = key;
    }
}
