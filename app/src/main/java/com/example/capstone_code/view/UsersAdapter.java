package com.example.capstone_code.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_code.model.User;
import com.example.capstone_code.viewmodel.ColleagueProfileActivity;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Takes information from User model and UserItemView's XML fields and connects them
 */
public class UsersAdapter extends RecyclerView.Adapter<UserItemView> {
    private List<User> mUserList;
    private List<String> mKeys;
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;


    public UsersAdapter(List<User> mUserList, List<String> mKeys) {
        this.mUserList = mUserList;
        this.mKeys = mKeys;
    }

    public List<User> getmUserList() {
        return mUserList;
    }

    /**
     * Holds memory of each individual user in recycler
     */
    @NonNull
    @Override
    public UserItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserItemView(parent);
    }

    /**
     * Gets colleague info from model and applies it to field, and
     * sends current user to said colleague when their card is clicked
     * @param holder fields to be filled from UserItemView
     * @param position the colleague whose field is to be filled in the user list
     */
    @Override
    public void onBindViewHolder(@NonNull UserItemView holder, final int position) {
//            Glide.with(mContext)
//                    .asBitmap()
//                    .load(mImages.get(position))
//                    .into(holder.image);


        holder.bind(mUserList.get(position), mKeys.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            /**
             * When item in recycler view clicked, go to page of colleague
             * @param view
             */
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mUserList.get(position));

                Intent intent = new Intent(view.getContext(), ColleagueProfileActivity.class);
                intent.putExtra("colleague_name", mUserList.get(position).getName());
                intent.putExtra("colleague_role", mUserList.get(position).getRole());
                intent.putExtra("colleague_skills", mUserList.get(position).getSkills());
//                intent.putExtra("colleague_email", mUserList.get(position).getEmail());

                view.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Tells list adapter how many names are in the list
     */
    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    /**
     * Update user list with the new filtered list
     * @param newList
     */
    public void updateColleagueList(List<User> newList) {
        this.mUserList = newList;
        notifyDataSetChanged();

        Log.d(TAG, "updateColleagueList");

    }
}
