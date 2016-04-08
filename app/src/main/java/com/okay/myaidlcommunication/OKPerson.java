package com.okay.myaidlcommunication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by panweijie on 16/4/7.
 */
public class OKPerson implements Parcelable {

    public OKPerson() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    private String name;
    private String telNumber;
    private int age;

    protected OKPerson(Parcel in) {
        name = in.readString();
        telNumber = in.readString();
        age = in.readInt();
    }

    public static final Creator<OKPerson> CREATOR = new Creator<OKPerson>() {
        @Override
        public OKPerson createFromParcel(Parcel in) {
            return new OKPerson(in);
        }

        @Override
        public OKPerson[] newArray(int size) {
            return new OKPerson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telNumber);
        dest.writeInt(age);

    }
}
