package com.roundarch.codetest.part2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import com.roundarch.codetest.ProgressDialogFragment;
import com.roundarch.codetest.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EditFragment extends Fragment {
    public static final int RESULT_SAVE = 1;
    public static final String EXTRA_MODEL = "extra_model";

    private static final String TAG = "EditFragment";

    private DataModel mModel; // TODONE - needs to be provided from original Activity/Fragment
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;

    // TODONE - This fragment should allow you to edit the fields of DataModel
    // TODONE - Then when you click the save button, it should get the DataModel back to the prior activity
    // TODONE - so it's up to date
    @Override
    public View
            onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container);
        view.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_save();
            }
        });

        edit1 = (EditText)view.findViewById(R.id.editText1);
        edit2 = (EditText)view.findViewById(R.id.editText2);
        edit3 = (EditText)view.findViewById(R.id.editText3);

        setRetainInstance(true);

        return view;
    }

    private void modifyModelOperation(final DataModel model) {
        showLoadingDialog();
        refreshModelFromViews();

        // TODONE - you need to implement swapText
        swapText(model);

        // TODONE - the BlackBox simulates a slow operation, so you will need to update
        // TODONE - this code to prevent it from blocking the main thread
        Observable.just(BlackBox.doMagic(model.getText3()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(value -> {
                    model.setText3(value);
                    editComplete();
                });
//        double newValue = BlackBox.doMagic(model.getText3();
//        model.setText3(newValue);
    }

    private void editComplete() {
        // TODONE - once the model has been updated, you need to find a good way to
        // TODONE - to provide it back to Part2Fragment in the MainActivity
        EditActivity activity = (EditActivity) getActivity();
        activity.editComplete(mModel);
    }

    private void showLoadingDialog() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        ProgressDialogFragment editNameDialog = new ProgressDialogFragment();
        editNameDialog.show(fm, "progress_dialog_fragment");
    }

    private void refreshModelFromViews() {
        // TODONE - update our model from the views in our layout
        mModel.setText1(edit1.getText().toString());
        mModel.setText2(edit2.getText().toString());
        mModel.setText3(Double.valueOf(edit3.getText().toString()));
    }

    // Modifies the data model to swap the values in text1 and text2
    private void swapText(DataModel model) {
        // TODONE - swap the text1 and text2 fields on the data model
        String temp = model.getText1();
        model.setText1(model.getText2());
        model.setText2(temp);
    }

    public void onClick_save() {
        modifyModelOperation(mModel);
    }

    // TODONE - use this method from the Activity to set the model and update
    // TODONE - the views in the layout.  You will need to implement the refreshViewsFromModel()
    // TODONE - method as well
    public void setModel(DataModel model) {
        mModel = model;
        refreshViewsFromModel();
    }

    private void refreshViewsFromModel() {
        if (mModel == null) {
            Log.wtf(TAG, "DataModel is null! EditActivity closing.");
            getActivity().finish();
        }

        // TODONE - update our views based on the model's state
        edit1.setText(mModel.getText1());
        edit2.setText(mModel.getText2());
        edit3.setText(String.valueOf(mModel.getText3()));
    }
}
