package com.example.rucha.loginact.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.rucha.loginact.R;

/**
 * Created by rucha on 04-03-2018.
 */

public class Country implements Parcelable{

    private int id;
    private String countryName;
    private LineType lineType;
    private int imgId;

    public Country(int id, String countryName, LineType lineType, int imgId){
        this.id = id;
        this.countryName = countryName;
        this.lineType = lineType;
        this.imgId = imgId;
    }

    @Override
    public String toString(){return countryName;}

    public int getId(){return id;}

    public int getImgId() {
        return imgId;
    }

    public static class Builder{
        private int id;
        private String countryName;
        private LineType lineType;

        public void setLineType(String lineType){
            this.lineType = LineType.getLineType(lineType);
        }

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Country build(String countryName){
            this.countryName = countryName;
            return new Country(this.id, this.countryName, this.lineType, R.drawable.landscape);
        }
    }

    public LineType getLineType(){
        return lineType;
    }

    public String getCountry(){
        return countryName;
    }

    protected Country(Parcel in) {
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(countryName);
    }
}
