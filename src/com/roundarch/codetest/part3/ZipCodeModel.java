package com.roundarch.codetest.part3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Samuel on 09/12/2017.
 */

public class ZipCodeModel {

    @SerializedName("Zipcode")
    @Expose
    private int zipcode;

    @SerializedName("ZipClass")
    @Expose
    private String zipClass;

    @SerializedName("State")
    @Expose
    private String state;

    @SerializedName("City")
    @Expose
    private String city;

    @SerializedName("County")
    @Expose
    private String county;

    @SerializedName("Latitude")
    @Expose
    private Double latitude;

    @SerializedName("Longitude")
    @Expose
    private Double longitude;


    public ZipCodeModel() {
    }

    public ZipCodeModel(int zipcode,
                        String zipClass,
                        String state,
                        String city,
                        String county,
                        Double latitude,
                        Double longitude) {
        this.zipcode = zipcode;
        this.zipClass = zipClass;
        this.state = state;
        this.city = city;
        this.county = county;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getZipClass() {
        return zipClass;
    }

    public void setZipClass(String zipClass) {
        this.zipClass = zipClass;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
