package com.roundarch.codetest.part3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.roundarch.codetest.R;

/**
 * Created by Samuel on 09/12/2017.
 */

public class Part3ArrayAdapter extends ArrayAdapter<ZipCodeModel> {
    private final Context context;
    private final ZipCodeModel[] values;

    public Part3ArrayAdapter(Context context, ZipCodeModel[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.part3_listview_item, parent, false);

        TextView tvZipcode = (TextView) rowView.findViewById(R.id.tvZipcode);
        TextView tvCoordinates = (TextView) rowView.findViewById(R.id.tvCoordinates);
        TextView tvCountryState = (TextView) rowView.findViewById(R.id.tvCountryState);

        ZipCodeModel value = values[position];

        tvZipcode.setText(value.getZipcode());
        tvCoordinates.setText(
                String.valueOf(value.getLatitude() + ", " +
                String.valueOf(value.getLongitude())));
        tvCountryState.setText(value.getCounty() + ", " + value.getState());

        return rowView;
    }
}