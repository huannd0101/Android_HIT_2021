package com.example.tablayout_demo;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {
    private String country_Region;
    private String confirmed;
    private String deaths;
    private String recovered;

    public Country(String country_Region, String confirmed, String deaths, String recovered) {
        this.country_Region = country_Region;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    protected Country(Parcel in) {
        country_Region = in.readString();
        confirmed = in.readString();
        deaths = in.readString();
        recovered = in.readString();
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

    public String getCountry_Region() {
        return country_Region;
    }

    public void setCountry_Region(String country_Region) {
        this.country_Region = country_Region;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_Region='" + country_Region + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", deaths='" + deaths + '\'' +
                ", recovered='" + recovered + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country_Region);
        dest.writeString(confirmed);
        dest.writeString(deaths);
        dest.writeString(recovered);
    }
}
