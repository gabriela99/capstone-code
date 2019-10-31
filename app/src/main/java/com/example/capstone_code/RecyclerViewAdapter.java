package com.example.capstone_code;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mColleagueNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mColleagueNames, ArrayList<String> mImages) {
        this.mColleagueNames = mColleagueNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.colleagueName.setText(mColleagueNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mColleagueNames.get(position));

                Toast.makeText(mContext, mColleagueNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Tells list adapter how many names are in the list
     */

    @Override
    public int getItemCount() {
        return mColleagueNames.size();
    }

    /**
     * Holds memory of each individual image in recycler
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView colleagueName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            colleagueName = itemView.findViewById(R.id.tvColleagueName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
