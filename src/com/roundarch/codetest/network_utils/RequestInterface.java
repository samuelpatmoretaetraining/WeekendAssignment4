package com.roundarch.codetest.network_utils;

import com.roundarch.codetest.part3.ZipCodeResultModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Samuel on 10/12/2017.
 */

public interface RequestInterface {

    @GET("json.php?fds=geo/usa/zipcode/state/NJ")
    Observable<ZipCodeResultModel> getZipcodeList0();


    @GET("json.php")
    Observable<ZipCodeResultModel> getZipcodeList1(@Query("fds") String geo);


    @GET("json.php?fds=geo/usa/zipcode/state/NJ")
    Observable<ZipCodeResultModel> getZipcodeList2();

}
