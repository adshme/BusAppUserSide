package com.wesleyan.bsit.busapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by FOXCONN on 11/4/2017.
 */

public class POST_Personel implements Serializable {

    @SerializedName("IDno")
    public int IDno;
    @SerializedName("fname")
    public String fname;
    @SerializedName("mname")
    public String mname;
    @SerializedName("lname")
    public String lname;
    @SerializedName("age")
    public String age;
    @SerializedName("bday")
    public String bday;
    @SerializedName("gender")
    public String gender;
    @SerializedName("address")
    public String address;
    @SerializedName("contact_no")
    public String contact_no;
    @SerializedName("position")
    public String position;
    @SerializedName("Status")
    public String Status;
    @SerializedName("image_path")
    public String image_path;
}
