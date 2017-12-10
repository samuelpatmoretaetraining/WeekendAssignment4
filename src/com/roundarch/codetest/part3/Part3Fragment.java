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
import com.roundarch.codetest.R;

import java.util.ArrayList;

public class Part3Fragment extends Fragment {

    public static final String DATA_RECEIVED = "com.roundarch.codetest.part3.DATA_RECEIVED";
    private static final String TAG = "Part3Fragment";

    ListView listView;
    ArrayList<ZipCodeModel> listData;

    Part3BroadcastReceiver dataReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part3, null);

        View emptyView = (View) view.findViewById(R.id.empty_textview);
        listView = (ListView) view.findViewById(R.id.part3_listview);
        // Default content
        listView.setEmptyView(emptyView);
        setListData(getDummyValues());
        refreshListView();

        // TODO - the listview will need to be provided with a source for data

        // TODO - (optional) you can set up handling to list item selection if you wish

        // create BroadcastReceiver
        dataReceiver = new Part3BroadcastReceiver();
        dataReceiver.setTarget(this);

        return view;
    }

    private ArrayList<ZipCodeModel> getDummyValues() {
        ArrayList<com.roundarch.codetest.part3.ZipCodeModel> dummyList = new ArrayList<>();
        com.roundarch.codetest.part3.ZipCodeModel item1 = new com.roundarch.codetest.part3.ZipCodeModel(7001, "STANDARD", "NJ", "AVENEL", "MIDDLESEX", +40.582845, -074.275240);
        com.roundarch.codetest.part3.ZipCodeModel item2 = new com.roundarch.codetest.part3.ZipCodeModel(7002, "STANDARD", "NY", "PLACE", "NOSEX", +38.958471, -075.097438);
        dummyList.add(item1);
        dummyList.add(item2);
        return dummyList;
    }

    private void setListData(ArrayList<ZipCodeModel> data) {
        this.listData = data;
    }

    private void refreshListView() {
        ZipCodeModel[] zipList = listData.toArray(new ZipCodeModel[listData.size()]);
        final Part3ArrayAdapter adapter = new Part3ArrayAdapter(this.getContext(), zipList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();


        // TODO - when the fragment resumes, it would be a good time to register to receieve broadcasts
        // TODO - from the service.  The broadcast will serve as a way to inform us that data is available
        // TODO - for consumption

        // TODO - this is also a good place to leverage the Service's IBinder interface to tell it you want
        // TODO - to refresh data

        // Register BroadcastReceiver
        LocalBroadcastManager.getInstance(this.getActivity())
                .registerReceiver(dataReceiver, new IntentFilter(DATA_RECEIVED));
        this.getActivity().registerReceiver(dataReceiver, new IntentFilter(DATA_RECEIVED));


        // Send intent to start IntentService (Part3IntentService)
        Intent fetchDataIntent = new Intent(this.getContext(), Part3IntentService.class);
        getActivity().startService(fetchDataIntent);


    }

    public void setDataReceived() {
        Log.i(TAG, "Data received from BroadcastReceiver");
    }

    @Override
    public void onPause() {
        super.onPause();


    }

    // TODO - our listView needs a source of data, and here might be a good place to create that

    // TODO - we also need a means of responding to the Broadcasts sent by our Service

}
