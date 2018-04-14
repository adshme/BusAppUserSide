package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class POST_BUSLIST implements Serializable {

    @SerializedName("tripID")
    public int tripID;
    @SerializedName("busNumber")
    public String busNumber;
    @SerializedName("fromWhere")
    public String from;
    @SerializedName("toWhere")
    public String to;


}
