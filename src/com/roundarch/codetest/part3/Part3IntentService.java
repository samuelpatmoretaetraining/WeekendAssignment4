package com.roundarch.codetest.part3;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Samuel on 09/12/2017.
 */

public class Part3IntentService extends IntentService{

    private static final String TAG = "Part3IntentService";

    public Part3IntentService() {
        super(Part3IntentService.class.getName());
    }

    public Part3IntentService(String name) {
        super(Part3IntentService.class.getName());
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "Intent received by Part3IntentService");

        try {
            Request request = new Request.Builder()
                    .url("http://gomashup.com/json.php?fds=geo/usa/zipcode/state/VT")
                    .header("Accept", "application/json")
                    .get()
                    .build();
            Response response = new OkHttpClient().newCall(request).execute();
            String body = response.body().string().replace("(", "").replace(")", "");

            Log.i(TAG, "Json data length " + body.length() + " chars.");

//            ZipCodeResultModel zipCodeResultModel = new Gson().fromJson(body, ZipCodeResultModel.class);
            Observable.just(new Gson().fromJson(body, ZipCodeResultModel.class))
                    .subscribe(result -> {
                        Log.i(TAG, "Json data entries " + result.getResult().size() + " items.");
                        formatData(result);
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formatData(ZipCodeResultModel rawData) {

        ArrayList<ZipCodeModel> rawList = new ArrayList<>(rawData.getResult());
        ArrayList<ZipCodeDisplayModel> returnList = new ArrayList<>();
        for (ZipCodeModel model : rawList) {
            returnList.add(new ZipCodeDisplayModel(model));
        }

        Log.i(TAG, "models converted: "+returnList.size() );

        serviceComplete(returnList);
    }

    private void serviceComplete(ArrayList<ZipCodeDisplayModel> returnList) {
        // Send Intent to BroadcastReceiver

        Intent returnIntent = new Intent();
        returnIntent = new Intent(Part3Fragment.DATA_RECEIVED);
        returnIntent.putParcelableArrayListExtra("data_list", new ArrayList<>(returnList));
        LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(returnIntent);
    }

}
