package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class POST_Login implements Serializable {

    @SerializedName("CusID")
    public String CusID;
    @SerializedName("Username")
    public String Username;
    @SerializedName("Password")
    public String Password;
    @SerializedName("Fullname")
    public String fullName;

}
