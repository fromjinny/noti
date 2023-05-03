package com.example.noti;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CCustomer implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    protected CCustomer(Parcel in) {
        id = in.readString();
        username = in.readString();
        password = in.readString();
        fullname = in.readString();
        email =in.readString();
    }
    //new implement
    public static final Creator<CCustomer> CREATOR = new Creator<CCustomer>() {
        @Override
        public CCustomer createFromParcel(Parcel in) {
            return new CCustomer(in);
        }

        @Override
        public CCustomer[] newArray(int size) {
            return new CCustomer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(fullname);
        parcel.writeString(email);


    }
}