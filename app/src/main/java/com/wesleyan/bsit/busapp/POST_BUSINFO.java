package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class POST_BUSINFO implements Serializable {

    @SerializedName("manageBusID")
    public int busID;
    @SerializedName("busNumber")
    public String busNumber;
    @SerializedName("plateNumber")
    public String plateNumber;
    @SerializedName("driver")
    public String driver;
    @SerializedName("conductor")
    public String conductor;
    @SerializedName("driverID")
    public String driverID;
    @SerializedName("conductorID")
    public String conductorID;
}
