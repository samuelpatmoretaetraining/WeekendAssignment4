package com.roundarch.codetest.part3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Samuel on 09/12/2017.
 */

public class ZipCodeResultModel {

    @SerializedName("result")
    @Expose
    private List<ZipCodeModel> result = null;

    public List<ZipCodeModel> getResult() {
        return result;
    }

    public void setResult(List<ZipCodeModel> result) {
        this.result = result;
    }

}