package com.example.capstone_code;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_code.model.User;
import com.example.capstone_code.viewmodel.ColleagueProfileActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RecyclerView_Config {
    private Context mContext;
    private UsersAdapter mUsersAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<User> users, List<String> keys) {
        mContext = context;
        mUsersAdapter = new UsersAdapter(users, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUsersAdapter);
    }


    class UserItemView extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mRole;
        private TextView mSkills;
        private String key;
        private RelativeLayout parentLayout;
        CircleImageView image;



        public UserItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
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
    class UsersAdapter extends RecyclerView.Adapter<UserItemView> {
        private List<User> mUserList;
        private List<String> mKeys;
        private ArrayList<String> mImages = new ArrayList<>();


        public UsersAdapter(List<User> mUserList, List<String> mKeys) {
            this.mUserList = mUserList;
            this.mKeys = mKeys;
        }

        /**
         * Holds memory of each individual image in recycler
         */
        @NonNull
        @Override
        public UserItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserItemView(parent);
        }

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

                    Intent intent = new Intent(mContext, ColleagueProfileActivity.class);
                    intent.putExtra("colleague_name", mUserList.get(position).getName());
                    intent.putExtra("colleague_role", mUserList.get(position).getRole());
                    intent.putExtra("colleague_skills", mUserList.get(position).getSkills());

                    mContext.startActivity(intent);
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
    }
}
