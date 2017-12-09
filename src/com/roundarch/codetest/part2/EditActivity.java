package com.roundarch.codetest.part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.roundarch.codetest.R;

public class EditActivity extends FragmentActivity {

    private static final String TAG = "EditActivity";
    EditFragment mEditFragment;
    DataModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_edit);

        // TODONE - you will need to obtain the model object provided to this activity and provide it to the EditFragment
        mEditFragment = (EditFragment) getSupportFragmentManager().findFragmentById(R.id.edit_fragment);
        mModel = (DataModel) getIntent().getParcelableExtra("datamodel_parcel");
        if (mModel != null) {
            mEditFragment.setModel(mModel);
        } else {
            Log.wtf(TAG, "No DataModel supplied to EditActivity!");
            finish();
        }
    }

    public void editComplete(DataModel model) {
        Intent intent = new Intent();

        if(! model.getText1().equals("")) {
            Log.i(TAG, "edit successful. Ending EditActivity.");
            intent.putExtra("datamodel_parcel", mModel);
            setResult(Activity.RESULT_OK, intent);
        } else {
            setResult(Activity.RESULT_CANCELED, intent);
            Log.i(TAG, "edit failed. Ending EditActivity.");
        }

        finish();
    }
}
