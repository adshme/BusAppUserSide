package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

public class POST {

    @SerializedName("departureTime")
    public String departureTime;
    @SerializedName("busNumber")
    public String busNumber;
    @SerializedName("fromWhere")
    public String fromWhere;
    @SerializedName("toWhere")
    public String toWhere;
}
