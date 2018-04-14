package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class POST_QR implements Serializable {

    @SerializedName("busNumber")
    public String busNumber;
    @SerializedName("seatNumber")
    public String seatNumber;
    @SerializedName("code")
    public String code;
    @SerializedName("expirationdate")
    public String expirationdate;
    @SerializedName("Rate")
    public String Rate;
    @SerializedName("rebookexpiredate")
    public String rebookexpiredate;

}
