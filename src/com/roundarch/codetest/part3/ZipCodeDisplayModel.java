package com.roundarch.codetest.part3;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import com.roundarch.codetest.R;

/**
 * Created by Samuel on 10/12/2017.
 */

public class ZipCodeDisplayModel implements Parcelable {

    String zipcode, coordinates, countyState;

    public ZipCodeDisplayModel(String zipcode, String coordinates, String countyState) {
        this.zipcode = zipcode;
        this.coordinates = coordinates;
        this.countyState = countyState;
    }

    public ZipCodeDisplayModel(ZipCodeModel model) {
        this.zipcode = String.valueOf(model.getZipcode());
        this.coordinates = String.valueOf(model.getLatitude()) + ", " + String.valueOf(model.getLongitude());
        this.countyState = model.getCounty() + ", " + model.getState();
    }

    protected ZipCodeDisplayModel(Parcel in) {
        zipcode = in.readString();
        coordinates = in.readString();
        countyState = in.readString();
    }

    public static final Creator<ZipCodeDisplayModel> CREATOR = new Creator<ZipCodeDisplayModel>() {
        @Override
        public ZipCodeDisplayModel createFromParcel(Parcel in) {
            return new ZipCodeDisplayModel(in);
        }

        @Override
        public ZipCodeDisplayModel[] newArray(int size) {
            return new ZipCodeDisplayModel[size];
        }
    };

    public String getZipcode() {
        return zipcode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getCountyState() {
        return countyState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(zipcode);
        dest.writeString(coordinates);
        dest.writeString(countyState);
    }
}
