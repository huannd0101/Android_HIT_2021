package com.example.buoi_4;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private String name;
    private int age;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
