package com.example.capstone_code.cardview;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.capstone_code.R;

/**
 * Fragment that demonstrates how to use CardView.
 */

public class ColleagueCardFragment extends Fragment {

    // the fragment initialization parameter
    private static final String TAG = ColleagueCardFragment.class.getSimpleName();

    /** The CardView widget. */
    //@VisibleForTesting
    CardView mCardView;

    /**
     * SeekBar that changes the cornerRadius attribute for the {@link #mCardView} widget.
     */
    //@VisibleForTesting
    SeekBar mRadiusSeekBar;

    /**
     * SeekBar that changes the Elevation attribute for the {@link #mCardView} widget.
     */
    //@VisibleForTesting
    SeekBar mElevationSeekBar;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NotificationFragment.
     */
    public static ColleagueCardFragment newInstance() {
        ColleagueCardFragment fragment = new ColleagueCardFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public ColleagueCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_colleague_card, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCardView = (CardView) view.findViewById(R.id.cardview);
        mRadiusSeekBar = (SeekBar) view.findViewById(R.id.cardview_radius_seekbar);
        mRadiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, String.format("SeekBar Radius progress : %d", progress));
                mCardView.setRadius(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }
        });

        mElevationSeekBar = (SeekBar) view.findViewById(R.id.cardview_elevation_seekbar);
        mElevationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, String.format("SeekBar Elevation progress : %d", progress));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mCardView.setElevation(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }
        });
    }

}

