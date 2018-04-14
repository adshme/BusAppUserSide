package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class POST_Location implements Serializable {

    @SerializedName("ID")
    public int ID;
    @SerializedName("BusNumber")
    public String BusNumber;
    @SerializedName("Longitude")
    public double Longitude;
    @SerializedName("Latitude")
    public double Latitude;
}
