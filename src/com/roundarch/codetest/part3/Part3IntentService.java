package com.roundarch.codetest.part3;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.roundarch.codetest.network_utils.API_Constants;
import com.roundarch.codetest.network_utils.SchedulerProvider;
import com.roundarch.codetest.network_utils.ServerConnection;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Samuel on 09/12/2017.
 */

public class Part3IntentService extends IntentService{

    private CompositeDisposable mCompositeDisposable;
    private SchedulerProvider mScheduleProvider;

    private static final String TAG = "Part3IntentService";

    // Zero argument constructor required for instantiation on Intent
    public Part3IntentService() {
        // Name thread onHandleIntent will be executed on.
        super(Part3IntentService.class.getName());
        // Restarts IS, with duplicate Intent if killed before onHandleIntent completes.
        setIntentRedelivery(true);

        mCompositeDisposable = new CompositeDisposable();
        mScheduleProvider = new SchedulerProvider();
    }

    public Part3IntentService(String name) {
        // Name thread onHandleIntent will be executed on.
        super(Part3IntentService.class.getName());
        // Restarts IS, with duplicate Intent if killed before onHandleIntent completes.
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "Intent received by Part3IntentService");

        // Send Intent to BroadcastReceiver
        Intent i = new Intent(Part3Fragment.DATA_RECEIVED);
        LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(i);

        mCompositeDisposable.add(
                ServerConnection.getConnection()
                        .getZipcodeList1("geo/usa/zipcode/state/NJ")
                        .observeOn(mScheduleProvider.ui())
                        .subscribeOn(mScheduleProvider.io())
                        .subscribe(pointModels -> {
                            Log.i(TAG, "API_Data received");
                        }, throwable -> {
                            throwable.printStackTrace();
                        }));
    }
}
