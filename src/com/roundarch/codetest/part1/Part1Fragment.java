package com.roundarch.codetest.part1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.roundarch.codetest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.StrictMath.abs;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment implements  SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "Part1Fragment";
    private static final int SWITCH_1 = 0;
    private static final int SWITCH_2 = 1;

    @BindView(R.id.seekBar1) SeekBar seekBar1;

    @BindView(R.id.seekBar2) SeekBar seekBar2;

    @BindView(R.id.switch1) Switch switch1;

    @BindView(R.id.tvDifference) TextView tvDifference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1, null);
        ButterKnife.bind(this, view);
        seekBar1 = (SeekBar) view.findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) view.findViewById(R.id.seekBar2);
        tvDifference = (TextView) view.findViewById(R.id.tvDifference);
        Log.i(TAG, tvDifference.toString());

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        updateDifference(0, switch1.isChecked());

        // TODO - hook up any event listeners that make sense for the task

        return view;
    }

    private void updateDifference(int sliderId, boolean valuesSynced) {
        int difference = valuesSynced ? 0 : abs(seekBar1.getProgress() - seekBar2.getProgress());
        if(valuesSynced) {
            if (sliderId == R.id.seekBar1) {
                seekBar2.setProgress(seekBar1.getProgress());
            } else {
                seekBar1.setProgress(seekBar2.getProgress());
            }
        }
        Log.i(TAG, "difference: "+difference);
        tvDifference.setText(String.valueOf(difference));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        updateDifference(seekBar.getId(), switch1.isChecked());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
