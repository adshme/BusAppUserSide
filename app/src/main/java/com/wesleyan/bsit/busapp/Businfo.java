package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Businfo extends AppCompatActivity implements Serializable {
    TextView title, drivertv, conductortv, fromtv, totv, platenotv;
    ImageView ivDriver, ivConductor;
    String gettheDriver, gettheConductor, imgPath, gettheBus;
    String busid;
    ProgressDialog dialog12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businfo);

        dialog12 = new ProgressDialog(Businfo.this);
        title = (TextView)findViewById(R.id.title);
        ivDriver = (ImageView)findViewById(R.id.driverPic);
        drivertv = (TextView)findViewById(R.id.tvDriver);
        ivConductor = (ImageView)findViewById(R.id.condPic);
        conductortv =(TextView)findViewById(R.id.tvConductor);
        fromtv = (TextView)findViewById(R.id.tvFrom);
        totv = (TextView)findViewById(R.id.tvTo);
        platenotv = (TextView)findViewById(R.id.tvPlateNo);




        if(isConnectingToInternet(Businfo.this)){
            POST_BUSLIST businfo = (POST_BUSLIST) getIntent().getSerializableExtra("buslist");

            if(businfo != null) {
                busid = businfo.busNumber;
                title.setText("Bus "+businfo.busNumber);
                fromtv.setText("" +businfo.from);
                totv.setText("" +businfo.to);

                gettheBus = businfo.busNumber;
                getSupportActionBar().setTitle(title.getText().toString());
            }


            String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_businfo.php";


            StringRequest businfor = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    dialog12.dismiss();
                    if (response.equals("No Results Found.")) {
                        offlineMODE();
                    } else {
                        ArrayList<POST_BUSINFO> businfo = new JsonConverter<POST_BUSINFO>().toArrayList(response, POST_BUSINFO.class);
                        for (POST_BUSINFO value : businfo) {
                            drivertv.setText("" + value.driver);
                            conductortv.setText("" + value.conductor);
                            platenotv.setText("" + value.plateNumber);

                            gettheDriver = value.driverID;
                            gettheConductor = value.conductorID;
                        }
                        loadPics();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dialog12.dismiss();
                    if (error instanceof TimeoutError){
                        Timeout();
                    }else if (error instanceof NoConnectionError){
                        offlineMODE();
                    }
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> postData = new HashMap<>();
                    postData.put("busNumber",gettheBus);
                    return postData;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(businfor);
            dialog12.setTitle("Loading Bus Information");
            dialog12.setCancelable(false);
            dialog12.setMessage("Please Wait");
            dialog12.show();
        }else{
            offlineMODE();
        }
        ivDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectingToInternet(Businfo.this)) {
                    Intent trow = new Intent(Businfo.this, EmployeeInfo.class);
                    trow.putExtra("name", gettheDriver);
                    startActivity(trow);
                }else{
                    offlineMODE();
                }
            }
        });
        drivertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectingToInternet(Businfo.this)) {
                    Intent trow = new Intent(Businfo.this, EmployeeInfo.class);
                    trow.putExtra("name", gettheDriver);
                    startActivity(trow);
                }else{
                    offlineMODE();
                }
            }
        });
        ivConductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectingToInternet(Businfo.this)) {
                    Intent trow2 = new Intent(Businfo.this, EmployeeInfo.class);
                    trow2.putExtra("name", gettheConductor);
                    startActivity(trow2);
                }else{
                    offlineMODE();
                }
            }
        });
        conductortv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectingToInternet(Businfo.this)) {
                    Intent trow2 = new Intent(Businfo.this, EmployeeInfo.class);
                    trow2.putExtra("name", gettheConductor);
                    startActivity(trow2);
                }else{
                    offlineMODE();
                }
            }
        });

    }


    public void routeFunction(View v){
        Intent i = new Intent(getApplicationContext(),Route.class);
        i.putExtra("busid",busid);
        startActivity(i);
    }

    public void followFunction(View v){
        Intent i = new Intent(getApplicationContext(),Tracking.class);
        i.putExtra("busid",busid);
        startActivity(i);
    }

    public static boolean isConnectingToInternet(Context context)
    {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }
    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Businfo.this).create();
        alertDialog.setTitle("Offline");
        alertDialog.setMessage("Please Check your Internet Connection");
        alertDialog.setIcon(R.drawable.ic_perm_scan_wifi_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void Timeout(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Businfo.this).create();
        alertDialog.setTitle("Connection Timeout");
        alertDialog.setMessage("Please Check your Internet Connection");
        alertDialog.setIcon(R.drawable.ic_perm_scan_wifi_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void noImage(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Businfo.this).create();
        alertDialog.setTitle("No Image");
        alertDialog.setMessage("No Image, Please Try Again Later");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void loadPics(){
        String url1 = "https://jeraldjoemagno11.000webhostapp.com/app/load_employee.php";
        String url2 = "https://jeraldjoemagno11.000webhostapp.com/app/load_employee.php";

        StringRequest driverPicture = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")) {
                    noImage();
                } else {
                    ArrayList<POST_Personel> empinfo = new JsonConverter<POST_Personel>().toArrayList(response, POST_Personel.class);
                    for (POST_Personel value : empinfo) {

                        imgPath = value.image_path;
                        Picasso.with(Businfo.this)
                                .load(imgPath)
                                .resize(100, 100)
                                .centerCrop()
                                .into(ivDriver);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError){
                    Timeout();
                }else if (error instanceof NoConnectionError){
                    offlineMODE();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> postData = new HashMap<>();
                postData.put("ID",gettheDriver);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(driverPicture);



        StringRequest conductorPicture = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")) {
                    noImage();
                } else {
                    ArrayList<POST_Personel2> empinfo = new JsonConverter<POST_Personel2>().toArrayList(response, POST_Personel2.class);
                    for (POST_Personel2 value : empinfo) {

                        imgPath = value.image_path;
                        Picasso.with(Businfo.this)
                                .load(imgPath)
                                .resize(100, 100)
                                .centerCrop()
                                .into(ivConductor);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError){
                    Timeout();
                }else if (error instanceof NoConnectionError){
                    offlineMODE();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> postData = new HashMap<>();
                postData.put("ID",gettheConductor);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(conductorPicture);

    }

    public void SeatsInfor(View view) {
        Intent ref = new Intent(Businfo.this,SeatInfor.class);
        ref.putExtra("title",busid);
        startActivity(ref);

    }
}
