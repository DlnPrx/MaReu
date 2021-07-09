package com.dylanprioux.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model object representing a Room
 */
public class Room implements Parcelable {


    private String mName;
    private int mImage;

    public Room(String name, int image) {
        mName = name;
        mImage = image;
    }


    protected Room(Parcel in) {
        mName = in.readString();
        mImage = in.readInt();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImage() {
        return mImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mImage);
    }


}
