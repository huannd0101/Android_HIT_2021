package com.example.buoi7;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String name;
    private String nameOfLover;

    public Person() {
    }

    public Person(String name, String nameOfLover) {
        this.name = name;
        this.nameOfLover = nameOfLover;
    }

    protected Person(Parcel in) {
        name = in.readString();
        nameOfLover = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfLover() {
        return nameOfLover;
    }

    public void setNameOfLover(String nameOfLover) {
        this.nameOfLover = nameOfLover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nameOfLover);
    }
}
