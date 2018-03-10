package com.example.rucha.loginact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rucha.loginact.Classes.Country;
import com.example.rucha.loginact.Utils.AppUtils;
import java.util.List;

import static com.example.rucha.loginact.R.*;

/**
 * Created by rucha on 05-03-2018.
 */

class ShowCountryAdapter extends ArrayAdapter {

    private final Context context;
    private List<Country> lCountry;

    public ShowCountryAdapter(Context context, List<Country> countries) {
        super(context, -1, countries);
        this.context = context;
        this.lCountry = countries;
    }

    @Override
    public int getCount() {
        return lCountry.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = inflater.inflate(R.layout.country_row_layout, parent, false);
        Country country = lCountry.get(position);

        ImageView lineColor = rootView.findViewById(R.id.line_color);
        TextView countryInfo = rootView.findViewById(R.id.country_info);
        TextView lineInfo = rootView.findViewById(R.id.line_info);
        TextView countryId = rootView.findViewById(R.id.countryId);

        lineColor.setColorFilter(AppUtils.getColor(country.getLineType()));
        countryInfo.setText(country.getCountry());
        countryId.setText(String.valueOf(country.getId()));
//        country.getImgId();
//        Uri selectImageUri = data.getData();
        lineColor.setImageResource(country.getImgId());
        return rootView;
    }
}
