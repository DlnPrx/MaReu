package com.dylanprioux.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Model object representing a Participant
 */
public class Participant implements Parcelable {
    private final String mMail;
    private Boolean mIsChecked;

    public Participant(String mail) {
        mMail = mail;
    }

    protected Participant(Parcel in) {
        mMail = in.readString();
    }

    public static final Creator<Participant> CREATOR = new Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel in) {
            return new Participant(in);
        }

        @Override
        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };

    public String getMail() {
        return mMail;
    }

    public Boolean getChecked() {
        return mIsChecked;
    }

    public void setChecked(Boolean checked) {
        mIsChecked = checked;
    }

    @NonNull
    @Override
    public String toString() {
        return mMail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mMail);
    }
}
