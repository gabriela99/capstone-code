package com.example.capstone_code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_code.model.User;

import java.util.List;

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

        public UserItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.user_list_item, parent, false));

            mName = itemView.findViewById(R.id.tvName);
            mRole = itemView.findViewById(R.id.tvRole);
            mSkills = itemView.findViewById(R.id.tvSkills);
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

        public UsersAdapter(List<User> mUserList, List<String> mKeys) {
            this.mUserList = mUserList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public UserItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserItemView holder, int position) {
            holder.bind(mUserList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }
}
