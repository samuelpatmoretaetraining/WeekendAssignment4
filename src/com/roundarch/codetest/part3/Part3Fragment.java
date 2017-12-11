package com.roundarch.codetest.part3;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.roundarch.codetest.R;

import java.util.ArrayList;

public class Part3Fragment extends Fragment {

    public static final String DATA_RECEIVED = "com.roundarch.codetest.part3.DATA_RECEIVED";
    private static final String TAG = "Part3Fragment";

    ListView listView;
    ArrayList<ZipCodeDisplayModel> listData;

    Part3BroadcastReceiver dataReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3, null);

        View emptyView = (View) view.findViewById(R.id.empty_textview);
        listView = (ListView) view.findViewById(R.id.part3_listview);

        listView.setEmptyView(emptyView);

        // create BroadcastReceiver
        dataReceiver = new Part3BroadcastReceiver();

        return view;
    }

    private void setListData(ArrayList<ZipCodeDisplayModel> data) {
        this.listData = data;
    }

    private void refreshListView() {
        final Part3ArrayAdapter adapter = new Part3ArrayAdapter(this.getContext(), listData);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register BroadcastReceiver
        LocalBroadcastManager.getInstance(this.getActivity())
                .registerReceiver(dataReceiver, new IntentFilter(DATA_RECEIVED));
        this.getActivity().registerReceiver(dataReceiver, new IntentFilter(DATA_RECEIVED));


        // Send intent to start IntentService (Part3IntentService)
        Intent fetchDataIntent = new Intent(this.getContext(), Part3IntentService.class);
        getActivity().startService(fetchDataIntent);


    }

    public void setData(ArrayList<ZipCodeDisplayModel> model) {
        Log.i(TAG, "Data received from BroadcastReceiver, size: " + model.size());
    }

    @Override
    public void onPause() {
        super.onPause();

        this.getActivity().unregisterReceiver(dataReceiver);
    }

    public class Part3BroadcastReceiver extends BroadcastReceiver {

        private static final String TAG = "Part3BroadcastReceiver";
        private Part3Fragment target;

        public Part3BroadcastReceiver() {
            Log.i(TAG, "Part3BroadcastReceiver created");
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Intent received by Part3BroadcastReceiver. Action: "+intent.getAction());
            Toast.makeText(context, "Intent received by Part3BroadcastReceiver. Action: "+intent.getAction(), Toast.LENGTH_SHORT).show();

            if (intent.getAction() == Part3Fragment.DATA_RECEIVED) {
                ArrayList<ZipCodeDisplayModel> sentData = intent.getParcelableArrayListExtra("data_list");
                if (sentData != null) {
                    Log.w(TAG, "List of "+sentData.size()+" items received by Part3Fragment");
                    setListData(sentData);
                    refreshListView();
                } else {
                    Log.w(TAG, "No data in intent received by Part3Fragment");
                }
            }
        }
    }
}
