package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class POST_Bus implements Serializable {

    @SerializedName("busNumber")
    public String busNumber;
}
