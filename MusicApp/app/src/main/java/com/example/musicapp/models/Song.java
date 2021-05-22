package com.example.musicapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Song implements Serializable {
    private String nameOfSong;
    private String singleOfSong;
    private Integer image;
    private Integer resource;

    public Song() {
    }

    public Song(String nameOfSong, String singleOfSong, Integer image, Integer resource) {
        this.nameOfSong = nameOfSong;
        this.singleOfSong = singleOfSong;
        this.image = image;
        this.resource = resource;
    }

    protected Song(Parcel in) {
        nameOfSong = in.readString();
        singleOfSong = in.readString();
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
        if (in.readByte() == 0) {
            resource = null;
        } else {
            resource = in.readInt();
        }
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public void setNameOfSong(String nameOfSong) {
        this.nameOfSong = nameOfSong;
    }

    public String getSingleOfSong() {
        return singleOfSong;
    }

    public void setSingleOfSong(String singleOfSong) {
        this.singleOfSong = singleOfSong;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
    }

}
