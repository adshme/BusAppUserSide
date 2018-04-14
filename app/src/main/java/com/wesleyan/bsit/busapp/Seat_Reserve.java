package com.wesleyan.bsit.busapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.wesleyan.bsit.busapp.Config.Config;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Seat_Reserve extends AppCompatActivity implements Serializable{


    TextView title, price,warn,details,titleDialog;
    String tripPrice,seatvar,tovar,fromvar,passState;
    EditText username,password;
    Button login,signup,cancel,reserving,cancelres,cancelrq,paymentOK;
    Spinner options,places;

    int statecheck,TripPlace;
    Button s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,
            s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,
            s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,
            s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41;
    String se1,se2,se3,se4,se5,se6,se7,se8,se9,se10,
            se11,se12,se13,se14,se15,se16,se17,se18,se19,se20,
            se21,se22,se23,se24,se25,se26,se27,se28,se29,se30,
            se31,se32,se33,se34,se35,se36,se37,se38,se39,se40,se41;
    HashMap postRes;
    String gettheBus;
    View mView, mView2,mView3,mView4;
    AlertDialog.Builder mBuilder,mBuilder2,mBuilder3,mBuilder4;
    Bundle nameEmp;
    String user,pass,id,fname;
    ListView qrcodes;
    ArrayList<POST_QR> qrs;
    ProgressDialog dialog12;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public static final String mypreference = "logprefs";
    public static final String userid = "userid";
    public static final String userusername = "userusername";
    public static final String userpassword = "userpassword";
    public static final String userfullname = "userfullname";
    public static final String usercode = "usercode";



    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat__reserve);


        dialog12 = new ProgressDialog(Seat_Reserve.this);
        title = (TextView)findViewById(R.id.bustitle);
        s1 = (Button)findViewById(R.id.s1);
        s2 = (Button)findViewById(R.id.s2);
        s3 = (Button)findViewById(R.id.s3);
        s4 = (Button)findViewById(R.id.s4);
        s5 = (Button)findViewById(R.id.s5);
        s6 = (Button)findViewById(R.id.s6);
        s7 = (Button)findViewById(R.id.s7);
        s8 = (Button)findViewById(R.id.s8);
        s9 = (Button)findViewById(R.id.s9);
        s10 = (Button)findViewById(R.id.s10);
        s11 = (Button)findViewById(R.id.s11);
        s12 = (Button)findViewById(R.id.s12);
        s13 = (Button)findViewById(R.id.s13);
        s14 = (Button)findViewById(R.id.s14);
        s15 = (Button)findViewById(R.id.s15);
        s16 = (Button)findViewById(R.id.s16);
        s17 = (Button)findViewById(R.id.s17);
        s18 = (Button)findViewById(R.id.s18);
        s19 = (Button)findViewById(R.id.s19);
        s20 = (Button)findViewById(R.id.s20);
        s21 = (Button)findViewById(R.id.s21);
        s22 = (Button)findViewById(R.id.s22);
        s23 = (Button)findViewById(R.id.s23);
        s24 = (Button)findViewById(R.id.s24);
        s25 = (Button)findViewById(R.id.s25);
        s26 = (Button)findViewById(R.id.s26);
        s27 = (Button)findViewById(R.id.s27);
        s28 = (Button)findViewById(R.id.s28);
        s29 = (Button)findViewById(R.id.s29);
        s30 = (Button)findViewById(R.id.s30);
        s31 = (Button)findViewById(R.id.s31);
        s32 = (Button)findViewById(R.id.s32);
        s33 = (Button)findViewById(R.id.s33);
        s34 = (Button)findViewById(R.id.s34);
        s35 = (Button)findViewById(R.id.s35);
        s36 = (Button)findViewById(R.id.s36);
        s37 = (Button)findViewById(R.id.s37);
        s38 = (Button)findViewById(R.id.s38);
        s39 = (Button)findViewById(R.id.s39);
        s40 = (Button)findViewById(R.id.s40);
        s41 = (Button)findViewById(R.id.s41);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        mView = getLayoutInflater().inflate(R.layout.login, null);
        mBuilder = new AlertDialog.Builder(Seat_Reserve.this);
        mView2 = getLayoutInflater().inflate(R.layout.reserve_options, null);
        mBuilder2 = new AlertDialog.Builder(Seat_Reserve.this);
        mView3 = getLayoutInflater().inflate(R.layout.qrcodelist, null);
        mBuilder3 = new AlertDialog.Builder(Seat_Reserve.this);
        mView4 = getLayoutInflater().inflate(R.layout.paymentsuccess, null);
        mBuilder4 = new AlertDialog.Builder(Seat_Reserve.this);

        details = (TextView)mView4.findViewById(R.id.txtdetails);
        paymentOK = (Button)mView4.findViewById(R.id.btnok);

        titleDialog = (TextView)mView3.findViewById(R.id.txttitlesDialog);
        cancelrq = (Button)mView3.findViewById(R.id.cancelbtn);
        qrcodes = (ListView)mView3.findViewById(R.id.listQR);
        warn = (TextView) mView3.findViewById(R.id.warning);

        options = (Spinner)mView2.findViewById(R.id.soption);
        places = (Spinner)mView2.findViewById(R.id.splaces);
        price = (TextView) mView2.findViewById(R.id.tvprice);
        cancelres = (Button)mView2.findViewById(R.id.btncan);
        reserving = (Button)mView2.findViewById(R.id.btnres);


        username = (EditText)mView.findViewById(R.id.username);
        password = (EditText)mView.findViewById(R.id.password);
        login = (Button)mView.findViewById(R.id.btnLogin);
        signup = (Button)mView.findViewById(R.id.signBTN);
        cancel = (Button)mView.findViewById(R.id.cancel);
        postRes = new HashMap();



        Intent i  =new Intent(this,PayPalService.class);
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(i);


        if (sharedpreferences.contains("userusername")) {
            getSupportActionBar().setTitle("Hello, " + sharedpreferences.getString("userfullname", "") );
        }else{
            getSupportActionBar().setTitle("Hello, Commuter");
        }







        if(isConnectingToInternet(Seat_Reserve.this)){
            POST_BUSLIST businfo = (POST_BUSLIST) getIntent().getSerializableExtra("bus2");

            if(businfo != null) {

                title.setText("" + businfo.busNumber);
                gettheBus = businfo.busNumber;

            }else if (getIntent().getExtras() != null){

                nameEmp = getIntent().getExtras();
                gettheBus = nameEmp.getString("title");
                title.setText("" + gettheBus);

            }

            String url = "https://jeraldjoemagno11.000webhostapp.com/app/trip_loc.php";

            StringRequest showRoute = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("No Results Found.")){
                        Toast.makeText(Seat_Reserve.this, "Error", Toast.LENGTH_SHORT).show();
                    }else{
                        ArrayList<POST_BUSLIST> trip = new JsonConverter<POST_BUSLIST>().toArrayList(response, POST_BUSLIST.class);
                        for (POST_BUSLIST value : trip) {
                            fromvar = value.from;
                            tovar = value.to;
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
                    postData.put("busNumber",gettheBus);
                    return postData;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showRoute);



            String url1 = "https://jeraldjoemagno11.000webhostapp.com/app/seats.php";

            StringRequest showSeatStatus = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equals("No Results Found.")) {
                        error();
                    } else {
                        ArrayList<POST_Seats> seatinfo = new JsonConverter<POST_Seats>().toArrayList(response, POST_Seats.class);
                        for (POST_Seats value : seatinfo) {
                            se1 = value.s1;
                            se2 = value.s2;
                            se3 = value.s3;
                            se4 = value.s4;
                            se5 = value.s5;
                            se6 = value.s6;
                            se7 = value.s7;
                            se8 = value.s8;
                            se9 = value.s9;
                            se10 = value.s10;
                            se11 = value.s11;
                            se12 = value.s12;
                            se13 = value.s13;
                            se14 = value.s14;
                            se15 = value.s15;
                            se16 = value.s16;
                            se17 = value.s17;
                            se18 = value.s18;
                            se19 = value.s19;
                            se20 = value.s20;
                            se21 = value.s21;
                            se22 = value.s22;
                            se23 = value.s23;
                            se24 = value.s24;
                            se25 = value.s25;
                            se26 = value.s26;
                            se27 = value.s27;
                            se28 = value.s28;
                            se29 = value.s29;
                            se30 = value.s30;
                            se31 = value.s31;
                            se32 = value.s32;
                            se33 = value.s33;
                            se34 = value.s34;
                            se35 = value.s35;
                            se36 = value.s36;
                            se37 = value.s37;
                            se38 = value.s38;
                            se39 = value.s39;
                            se40 = value.s40;
                            se41 = value.s41;
                        }

                    }


            if(se1.equals("Occupied")){
                s1.setEnabled(false);
                s1.setTextColor(Color.WHITE);
                s1.setBackgroundColor(Color.BLUE);
            }else if(se1.equals("Reserved")){
                s1.setEnabled(false);
                s1.setTextColor(Color.WHITE);
                s1.setBackgroundColor(Color.RED);
            }else{
                s1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s1");
                            seatvar = "s1";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se2.equals("Occupied")){
                s2.setEnabled(false);
                s2.setTextColor(Color.WHITE);
                s2.setBackgroundColor(Color.BLUE);
            }else if(se2.equals("Reserved")){
                s2.setEnabled(false);
                s2.setTextColor(Color.WHITE);
                s2.setBackgroundColor(Color.RED);
            }else{
                s2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s2");
                            seatvar = "s2";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se3.equals("Occupied")){
                s3.setEnabled(false);
                s3.setTextColor(Color.WHITE);
                s3.setBackgroundColor(Color.BLUE);
            }else if(se3.equals("Reserved")){
                s3.setEnabled(false);
                s3.setTextColor(Color.WHITE);
                s3.setBackgroundColor(Color.RED);
            }else{
                s3.setEnabled(true);
                s3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s3");
                            seatvar = "s3";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se4.equals("Occupied")){
                s4.setEnabled(false);
                s4.setTextColor(Color.WHITE);
                s4.setBackgroundColor(Color.BLUE);
            }else if(se4.equals("Reserved")){
                s4.setEnabled(false);
                s4.setTextColor(Color.WHITE);
                s4.setBackgroundColor(Color.RED);
            }else{
                s4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s4");
                            seatvar = "s4";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se5.equals("Occupied")){
                s5.setEnabled(false);
                s5.setTextColor(Color.WHITE);
                s5.setBackgroundColor(Color.BLUE);
            }else if(se5.equals("Reserved")){
                s5.setEnabled(false);
                s5.setTextColor(Color.WHITE);
                s5.setBackgroundColor(Color.RED);
            }else{
                s5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s5");
                            seatvar = "s5";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se6.equals("Occupied")){
                s6.setEnabled(false);
                s6.setTextColor(Color.WHITE);
                s6.setBackgroundColor(Color.BLUE);
            }else if(se6.equals("Reserved")){
                s6.setEnabled(false);
                s6.setTextColor(Color.WHITE);
                s6.setBackgroundColor(Color.RED);
            }else{
                s6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s6");
                            seatvar = "s6";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se7.equals("Occupied")){
                s7.setEnabled(false);
                s7.setTextColor(Color.WHITE);
                s7.setBackgroundColor(Color.BLUE);
            }else if(se7.equals("Reserved")){
                s7.setEnabled(false);
                s7.setTextColor(Color.WHITE);
                s7.setBackgroundColor(Color.RED);
            }else{
                s7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s7");
                            seatvar = "s7";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se8.equals("Occupied")){
                s8.setEnabled(false);
                s8.setTextColor(Color.WHITE);
                s8.setBackgroundColor(Color.BLUE);
            }else if(se8.equals("Reserved")){
                s8.setEnabled(false);
                s8.setTextColor(Color.WHITE);
                s8.setBackgroundColor(Color.RED);
            }else{
                s8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s8");
                            seatvar = "s8";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se9.equals("Occupied")){
                s9.setEnabled(false);
                s9.setTextColor(Color.WHITE);
                s9.setBackgroundColor(Color.BLUE);
            }else if(se9.equals("Reserved")){
                s9.setEnabled(false);
                s9.setTextColor(Color.WHITE);
                s9.setBackgroundColor(Color.RED);
            }else{
                s9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s9");
                            seatvar = "s9";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se10.equals("Occupied")){
                s10.setEnabled(false);
                s10.setTextColor(Color.WHITE);
                s10.setBackgroundColor(Color.BLUE);
            }else if(se10.equals("Reserved")){
                s10.setEnabled(false);
                s10.setTextColor(Color.WHITE);
                s10.setBackgroundColor(Color.RED);
            }else{
                s10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s10");
                            seatvar = "s10";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se11.equals("Occupied")){
                s11.setEnabled(false);
                s11.setTextColor(Color.WHITE);
                s11.setBackgroundColor(Color.BLUE);
            }else if(se11.equals("Reserved")){
                s11.setEnabled(false);
                s11.setTextColor(Color.WHITE);
                s11.setBackgroundColor(Color.RED);
            }else{
                s11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s11");
                            seatvar = "s11";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se12.equals("Occupied")){
                s12.setEnabled(false);
                s12.setTextColor(Color.WHITE);
                s12.setBackgroundColor(Color.BLUE);
            }else if(se12.equals("Reserved")){
                s12.setEnabled(false);
                s12.setTextColor(Color.WHITE);
                s12.setBackgroundColor(Color.RED);
            }else{
                s12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s12");
                            seatvar = "s12";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se13.equals("Occupied")){
                s13.setEnabled(false);
                s13.setTextColor(Color.WHITE);
                s13.setBackgroundColor(Color.BLUE);
            }else if(se13.equals("Reserved")){
                s13.setEnabled(false);
                s13.setTextColor(Color.WHITE);
                s13.setBackgroundColor(Color.RED);
            }else{
                s13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s13");
                            seatvar = "s13";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se14.equals("Occupied")){
                s14.setEnabled(false);
                s14.setTextColor(Color.WHITE);
                s14.setBackgroundColor(Color.BLUE);
            }else if(se14.equals("Reserved")){
                s14.setEnabled(false);
                s14.setTextColor(Color.WHITE);
                s14.setBackgroundColor(Color.RED);
            }else{
                s14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s14");
                            seatvar = "s14";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se15.equals("Occupied")){
                s15.setEnabled(false);
                s15.setTextColor(Color.WHITE);
                s15.setBackgroundColor(Color.BLUE);
            }else if(se15.equals("Reserved")){
                s15.setEnabled(false);
                s15.setTextColor(Color.WHITE);
                s15.setBackgroundColor(Color.RED);
            }else{
                s15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s15");
                            seatvar = "s15";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se16.equals("Occupied")){
                s16.setEnabled(false);
                s16.setTextColor(Color.WHITE);
                s16.setBackgroundColor(Color.BLUE);
            }else if(se16.equals("Reserved")){
                s16.setEnabled(false);
                s16.setTextColor(Color.WHITE);
                s16.setBackgroundColor(Color.RED);
            }else{
                s16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s16");
                            seatvar = "s16";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se17.equals("Occupied")){
                s17.setEnabled(false);
                s17.setTextColor(Color.WHITE);
                s17.setBackgroundColor(Color.BLUE);
            }else if(se17.equals("Reserved")){
                s17.setEnabled(false);
                s17.setTextColor(Color.WHITE);
                s17.setBackgroundColor(Color.RED);
            }else{
                s17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s17");
                            seatvar = "s17";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se18.equals("Occupied")){
                s18.setEnabled(false);
                s18.setTextColor(Color.WHITE);
                s18.setBackgroundColor(Color.BLUE);
            }else if(se18.equals("Reserved")){
                s18.setEnabled(false);
                s18.setTextColor(Color.WHITE);
                s18.setBackgroundColor(Color.RED);
            }else{
                s18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s18");
                            seatvar = "s18";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se19.equals("Occupied")){
                s19.setEnabled(false);
                s19.setTextColor(Color.WHITE);
                s19.setBackgroundColor(Color.BLUE);
            }else if(se19.equals("Reserved")){
                s19.setEnabled(false);
                s19.setTextColor(Color.WHITE);
                s19.setBackgroundColor(Color.RED);
            }else{
                s19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s19");
                            seatvar = "s19";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se20.equals("Occupied")){
                s20.setEnabled(false);
                s20.setTextColor(Color.WHITE);
                s20.setBackgroundColor(Color.BLUE);
            }else if(se20.equals("Reserved")){
                s20.setEnabled(false);
                s20.setTextColor(Color.WHITE);
                s20.setBackgroundColor(Color.RED);
            }else{
                s20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s20");
                            seatvar = "s20";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se21.equals("Occupied")){
                s21.setEnabled(false);
                s21.setTextColor(Color.WHITE);
                s21.setBackgroundColor(Color.BLUE);
            }else if(se21.equals("Reserved")){
                s21.setEnabled(false);
                s21.setTextColor(Color.WHITE);
                s21.setBackgroundColor(Color.RED);
            }else{
                s21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s21");
                            seatvar = "s21";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se22.equals("Occupied")){
                s22.setEnabled(false);
                s22.setTextColor(Color.WHITE);
                s22.setBackgroundColor(Color.BLUE);
            }else if(se22.equals("Reserved")){
                s22.setEnabled(false);
                s22.setTextColor(Color.WHITE);
                s22.setBackgroundColor(Color.RED);
            }else{
                s22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s22");
                            seatvar = "s22";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se23.equals("Occupied")){
                s23.setEnabled(false);
                s23.setTextColor(Color.WHITE);
                s23.setBackgroundColor(Color.BLUE);
            }else if(se23.equals("Reserved")){
                s23.setEnabled(false);
                s23.setTextColor(Color.WHITE);
                s23.setBackgroundColor(Color.RED);
            }else{
                s23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s23");
                            seatvar = "s23";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se24.equals("Occupied")){
                s24.setEnabled(false);
                s24.setTextColor(Color.WHITE);
                s24.setBackgroundColor(Color.BLUE);
            }else if(se24.equals("Reserved")){
                s24.setEnabled(false);
                s24.setTextColor(Color.WHITE);
                s24.setBackgroundColor(Color.RED);
            }else{
                s24.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s24");
                            seatvar = "s24";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }


            if(se25.equals("Occupied")){
                s25.setEnabled(false);
                s25.setTextColor(Color.WHITE);
                s25.setBackgroundColor(Color.BLUE);
            }else if(se25.equals("Reserved")){
                s25.setEnabled(false);
                s25.setTextColor(Color.WHITE);
                s25.setBackgroundColor(Color.RED);
            }else{
                s25.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s25");
                            seatvar = "s25";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se26.equals("Occupied")){
                s26.setEnabled(false);
                s26.setTextColor(Color.WHITE);
                s26.setBackgroundColor(Color.BLUE);
            }else if(se26.equals("Reserved")){
                s26.setEnabled(false);
                s26.setTextColor(Color.WHITE);
                s26.setBackgroundColor(Color.RED);
            }else{
                s26.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s26");
                            seatvar = "s26";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se27.equals("Occupied")){
                s27.setEnabled(false);
                s27.setTextColor(Color.WHITE);
                s27.setBackgroundColor(Color.BLUE);
            }else if(se27.equals("Reserved")){
                s27.setEnabled(false);
                s27.setTextColor(Color.WHITE);
                s27.setBackgroundColor(Color.RED);
            }else{
                s27.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s27");
                            seatvar = "s27";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se28.equals("Occupied")){
                s28.setEnabled(false);
                s28.setTextColor(Color.WHITE);
                s28.setBackgroundColor(Color.BLUE);
            }else if(se28.equals("Reserved")){
                s28.setEnabled(false);
                s28.setTextColor(Color.WHITE);
                s28.setBackgroundColor(Color.RED);
            }else{
                s28.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s28");
                            seatvar = "s28";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se29.equals("Occupied")){
                s29.setEnabled(false);
                s29.setTextColor(Color.WHITE);
                s29.setBackgroundColor(Color.BLUE);
            }else if(se29.equals("Reserved")){
                s29.setEnabled(false);
                s29.setTextColor(Color.WHITE);
                s29.setBackgroundColor(Color.RED);
            }else{
                s29.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s29");
                            seatvar = "s29";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se30.equals("Occupied")){
                s30.setEnabled(false);
                s30.setTextColor(Color.WHITE);
                s30.setBackgroundColor(Color.BLUE);
            }else if(se30.equals("Reserved")){
                s30.setEnabled(false);
                s30.setTextColor(Color.WHITE);
                s30.setBackgroundColor(Color.RED);
            }else{
                s30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s30");
                            seatvar = "s30";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se31.equals("Occupied")){
                s31.setEnabled(false);
                s31.setTextColor(Color.WHITE);
                s31.setBackgroundColor(Color.BLUE);
            }else if(se31.equals("Reserved")){
                s31.setEnabled(false);
                s31.setTextColor(Color.WHITE);
                s31.setBackgroundColor(Color.RED);
            }else{
                s31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s31");
                            seatvar = "s31";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se32.equals("Occupied")){
                s32.setEnabled(false);
                s32.setTextColor(Color.WHITE);
                s32.setBackgroundColor(Color.BLUE);
            }else if(se32.equals("Reserved")){
                s32.setEnabled(false);
                s32.setTextColor(Color.WHITE);
                s32.setBackgroundColor(Color.RED);
            }else{
                s32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s32");
                            seatvar = "s32";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se33.equals("Occupied")){
                s33.setEnabled(false);
                s33.setTextColor(Color.WHITE);
                s33.setBackgroundColor(Color.BLUE);
            }else if(se33.equals("Reserved")){
                s33.setEnabled(false);
                s33.setTextColor(Color.WHITE);
                s33.setBackgroundColor(Color.RED);
            }else{
                s33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s33");
                            seatvar = "s33";
                            Reserving();
                           }else {
                            Loging();
                        }
                    }
                });
            }



            if(se34.equals("Occupied")){
                s34.setEnabled(false);
                s34.setTextColor(Color.WHITE);
                s34.setBackgroundColor(Color.BLUE);
            }else if(se34.equals("Reserved")){
                s34.setEnabled(false);
                s34.setTextColor(Color.WHITE);
                s34.setBackgroundColor(Color.RED);
            }else{
                s34.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s34");
                            seatvar = "s34";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se35.equals("Occupied")){
                s35.setEnabled(false);
                s35.setTextColor(Color.WHITE);
                s35.setBackgroundColor(Color.BLUE);
            }else if(se35.equals("Reserved")){
                s35.setEnabled(false);
                s35.setTextColor(Color.WHITE);
                s35.setBackgroundColor(Color.RED);
            }else{
                s35.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s35");
                            seatvar = "s35";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se36.equals("Occupied")){
                s36.setEnabled(false);
                s36.setTextColor(Color.WHITE);
                s36.setBackgroundColor(Color.BLUE);
            }else if(se36.equals("Reserved")){
                s36.setEnabled(false);
                s36.setTextColor(Color.WHITE);
                s36.setBackgroundColor(Color.RED);
            }else{
                s36.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s36");
                            seatvar = "s36";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se37.equals("Occupied")){
                s37.setEnabled(false);
                s37.setTextColor(Color.WHITE);
                s37.setBackgroundColor(Color.BLUE);
            }else if(se37.equals("Reserved")){
                s37.setEnabled(false);
                s37.setTextColor(Color.WHITE);
                s37.setBackgroundColor(Color.RED);
            }else{
                s37.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s37");
                            seatvar = "s37";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se38.equals("Occupied")){
                s38.setEnabled(false);
                s38.setTextColor(Color.WHITE);
                s38.setBackgroundColor(Color.BLUE);
            }else if(se38.equals("Reserved")){
                s38.setEnabled(false);
                s38.setTextColor(Color.WHITE);
                s38.setBackgroundColor(Color.RED);
            }else{
                s38.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s38");
                            seatvar = "s38";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }



            if(se39.equals("Occupied")){
                s39.setEnabled(false);
                s39.setTextColor(Color.WHITE);
                s39.setBackgroundColor(Color.BLUE);
            }else if(se39.equals("Reserved")){
                s39.setEnabled(false);
                s39.setTextColor(Color.WHITE);
                s39.setBackgroundColor(Color.RED);
            }else{
                s39.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s39");
                            seatvar = "s39";
                            Reserving();
                             }else {
                            Loging();
                        }
                    }
                });
            }



            if(se40.equals("Occupied")){
                s40.setEnabled(false);
                s40.setTextColor(Color.WHITE);
                s40.setBackgroundColor(Color.BLUE);
            }else if(se40.equals("Reserved")){
                s40.setEnabled(false);
                s40.setTextColor(Color.WHITE);
                s40.setBackgroundColor(Color.RED);
            }else{
                s40.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s40");
                            seatvar = "s40";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
            }


            if(se41.equals("Occupied")){
                s41.setEnabled(false);
                s41.setTextColor(Color.WHITE);
                s41.setBackgroundColor(Color.BLUE);
            }else if(se41.equals("Reserved")){
                s41.setEnabled(false);
                s41.setTextColor(Color.WHITE);
                s41.setBackgroundColor(Color.RED);
            }else{
                s41.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sharedpreferences.contains(userusername)) {
                            postRes.put("seatNo","s41");
                            seatvar = "s41";
                            Reserving();
                            }else {
                            Loging();
                        }
                    }
                });
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
                    postData.put("busNumber",gettheBus);
                    return postData;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showSeatStatus);


                }else{
        offlineMODE();
    }
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
    private void Timeout(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Seat_Reserve.this).create();
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
    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Seat_Reserve.this).create();
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
    private void notMatch(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Seat_Reserve.this).create();
        alertDialog.setTitle("Not Match");
        alertDialog.setMessage("Username or Password not Exist");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void error(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Seat_Reserve.this).create();
        alertDialog.setTitle("Error");
        alertDialog.setMessage("Please Try Again Later");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void already(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Seat_Reserve.this).create();
        alertDialog.setTitle("Reserved");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("The sit that you are reserving is already reserved");
        alertDialog.setIcon(R.drawable.ic_clear_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                Intent ref = new Intent(Seat_Reserve.this,Seat_Reserve.class);
                ref.putExtra("title",title.getText().toString());
                startActivity(ref);
                finish();
            }
        });
        alertDialog.show();
    }



    public void Loging(){

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.setCancelable(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String url = "https://jeraldjoemagno11.000webhostapp.com/app/login.php";

                StringRequest showloc = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog12.dismiss();
                        if (response.equals("No Result")) {
                            notMatch();
                        } else {
                            ArrayList<POST_Login> empinfo = new JsonConverter<POST_Login>().toArrayList(response, POST_Login.class);
                            for (POST_Login value : empinfo) {
                                id = value.CusID;
                                user = value.Username;
                                pass = value.Password;
                                fname = value.fullName;
                            }


                            editor.putString(userid, id);
                            editor.putString(userusername, user);
                            editor.putString(userpassword, pass);
                            editor.putString(userfullname, fname);
                            editor.commit();

                            Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            Intent ref = new Intent(Seat_Reserve.this, Seat_Reserve.class);
                            ref.putExtra("title", title.getText().toString());
                            startActivity(ref);
                            Seat_Reserve.this.finish();
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
                        Map<String ,String> postDatalog = new HashMap<>();
                        postDatalog.put("username",username.getText().toString());
                        postDatalog.put("password",password.getText().toString());
                        return postDatalog;
                    }
                };
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showloc);
                dialog12.setTitle("Logging In");
                dialog12.setCancelable(false);
                dialog12.setMessage("Please Wait");
                dialog12.show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = new Intent(Seat_Reserve.this,reg_user.class);
                sign.putExtra("title",title.getText().toString());
                startActivity(sign);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent ref = new Intent(Seat_Reserve.this,Seat_Reserve.class);
                ref.putExtra("title",title.getText().toString());
                startActivity(ref);
                finish();

            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.seatmenu, menu);
        if (sharedpreferences.contains(userusername)) {
            menu.findItem(R.id.logout).setTitle("Logout");
            menu.findItem(R.id.code).setVisible(true);
        }else{
            menu.findItem(R.id.logout).setTitle("Log In");
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                Intent ref = new Intent(Seat_Reserve.this,Seat_Reserve.class);
                ref.putExtra("title",title.getText().toString());
                startActivity(ref);
                finish();
                return true;
            case R.id.logout:
                if (item.getTitle().equals("Log In")){
                    Loging();

                }else {
                    if(isConnectingToInternet(Seat_Reserve.this)) {
                        editor.clear();
                        editor.commit();
                        Intent log = new Intent(Seat_Reserve.this, Seat_Reserve.class);
                        log.putExtra("title", title.getText().toString());
                        startActivity(log);
                        finish();
                    }else{
                        offlineMODE();
                    }
                }
                return true;

            case R.id.code:
                qrlist();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void Reserving(){
        String url = "https://jeraldjoemagno11.000webhostapp.com/app/seat_checker.php";

        StringRequest checkOnce = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Available")) {
                    mBuilder2.setView(mView2);
                    final AlertDialog dialog = mBuilder2.create();
                    dialog.setCancelable(false);


                    if (fromvar.equals("Cubao") && tovar.equals("San Jose City")) {
                        tripCubao();
                    } else if (fromvar.equals("San Jose City") && tovar.equals("Cubao")) {
                        tripSanJose();
                    } else if (fromvar.equals("Cubao") && tovar.equals("Cabanatuan City")) {
                        tripCcabanatuan();
                    } else {
                        tripcabanatuan();
                    }

                    reserving.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CheckAgain();
                        }
                    });
                    cancelres.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            Intent ref = new Intent(Seat_Reserve.this, Seat_Reserve.class);
                            ref.putExtra("title", title.getText().toString());
                            startActivity(ref);
                            finish();
                        }
                    });
                    dialog.show();
                } else {
                    already();
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
                Map<String ,String> postRes = new HashMap<>();
                postRes.put("busNumber",title.getText().toString());
                postRes.put("seatNo", seatvar);
                return postRes;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(checkOnce);

    }

    public void CheckAgain(){
        String url = "https://jeraldjoemagno11.000webhostapp.com/app/seat_checker.php";

        StringRequest checkAgain = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Available")) {
                    processPayment();
                } else {
                    already();
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
                Map<String ,String> postRes = new HashMap<>();
                postRes.put("busNumber",title.getText().toString());
                postRes.put("seatNo", seatvar);
                return postRes;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(checkAgain);
    }


    public void tripCubao(){
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this, R.array.actions, android.R.layout.simple_list_item_1);

        ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(this, R.array.cubao, android.R.layout.simple_list_item_1);

        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapters);
        places.setAdapter(pAdapter);
        places.setEnabled(false);

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    reserving.setEnabled(false);
                }else if (i==1){
                    statecheck = 1;
                    postRes.put("state","Regular");
                    passState = "Regular";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "102";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "211";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "240";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        places.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    reserving.setEnabled(false);
                    TripPlace = 0;
                }else if (i==1){
                    TripPlace = 1;
                    if (statecheck==1){
                        tripPrice= "102";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    TripPlace = 2;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    TripPlace = 3;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    TripPlace = 4;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    TripPlace = 5;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    TripPlace = 6;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    TripPlace = 7;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==8){
                    TripPlace = 8;
                    if (statecheck==1){
                        tripPrice= "211";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==9){
                    TripPlace = 9;
                    if (statecheck==1){
                        tripPrice= "240";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==10){
                    TripPlace = 10;
                    if (statecheck==1){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "209";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void tripCcabanatuan(){
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this, R.array.actions, android.R.layout.simple_list_item_1);

        ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(this, R.array.ccubao, android.R.layout.simple_list_item_1);

        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapters);
        places.setAdapter(pAdapter);
        places.setEnabled(false);

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    reserving.setEnabled(false);
                }else if (i==1){
                    statecheck = 1;
                    postRes.put("state","Regular");
                    passState = "Regular";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                    price.setText("");
                }else if (TripPlace == 1){
                        tripPrice= "102";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }else if (TripPlace == 2){
                        tripPrice= "136";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }else if (TripPlace == 3){
                        tripPrice= "136";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }else if (TripPlace == 4){
                        tripPrice= "172";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }else if (TripPlace == 5){
                        tripPrice= "172";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }else if (TripPlace == 6){
                        tripPrice= "185";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }else if (TripPlace == 7){
                        tripPrice= "185";
                    price.setText("₱ "+tripPrice);
                    price.setTextColor(Color.GREEN);
                }

                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        places.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    reserving.setEnabled(false);
                    TripPlace = 0;
                }else if (i==1){
                    TripPlace = 1;
                    if (statecheck==1){
                        tripPrice= "102";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    TripPlace = 2;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    TripPlace = 3;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    TripPlace = 4;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    TripPlace = 5;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    TripPlace = 6;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    TripPlace = 7;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void tripcabanatuan(){
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this, R.array.actions, android.R.layout.simple_list_item_1);

        ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(this, R.array.cabanatuan, android.R.layout.simple_list_item_1);

        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapters);
        places.setAdapter(pAdapter);
        places.setEnabled(false);

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    reserving.setEnabled(false);

                }else if (i==1){
                    statecheck = 1;
                    postRes.put("state","Regular");
                    passState = "Regular";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "50";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "40";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "40";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "40";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        places.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    reserving.setEnabled(false);
                    TripPlace = 0;
                }else if (i==1){
                    TripPlace = 1;
                    if (statecheck==1){
                        tripPrice= "50";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "40";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    TripPlace = 2;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    TripPlace = 3;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    TripPlace = 4;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    TripPlace = 5;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    TripPlace = 6;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    TripPlace = 7;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void tripSanJose(){
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this, R.array.actions, android.R.layout.simple_list_item_1);

        ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(this, R.array.sanjose, android.R.layout.simple_list_item_1);

        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapters);
        places.setAdapter(pAdapter);
        places.setEnabled(false);

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    reserving.setEnabled(false);
                }else if (i==1){
                    statecheck = 1;
                    postRes.put("state","Regular");
                    passState = "Regular";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "102";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "211";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "240";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "209";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "209";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
                    if (TripPlace == 0){
                        price.setText("");
                    }else if (TripPlace == 1){
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 2){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 3){
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 4){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 5){
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 6){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 7){
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 8){
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 9){
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }else if (TripPlace == 10){
                        tripPrice= "209";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        places.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    reserving.setEnabled(false);
                    TripPlace = 0;
                }else if (i==1){
                    TripPlace = 1;
                    if (statecheck==1){
                        tripPrice= "102";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "82";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    TripPlace = 2;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    TripPlace = 3;
                    if (statecheck==1){
                        tripPrice= "136";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "109";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    TripPlace = 4;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    TripPlace = 5;
                    if (statecheck==1){
                        tripPrice= "172";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "137";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    TripPlace = 6;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    TripPlace = 7;
                    if (statecheck==1){
                        tripPrice= "185";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "148";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==8){
                    TripPlace = 8;
                    if (statecheck==1){
                        tripPrice= "211";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "169";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==9){
                    TripPlace = 9;
                    if (statecheck==1){
                        tripPrice= "240";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "192";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==10){
                    TripPlace = 10;
                    if (statecheck==1){
                        tripPrice= "262";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "209";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Select option...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void qrlist(){
        mBuilder3.setView(mView3);
        final AlertDialog dialog = mBuilder3.create();
        dialog.setCancelable(false);
        titleDialog.setText("QR Codes");


        String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_QR.php";

        StringRequest showloc = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Result Found.")){
                    warn.setVisibility(View.VISIBLE);
                }else{
                    qrs = new JsonConverter<POST_QR>().toArrayList(response, POST_QR.class);
                    ArrayList<String> seats = new ArrayList<String>();
                    for (POST_QR value : qrs) {
                        seats.add(value.seatNumber);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Seat_Reserve.this, android.R.layout.simple_list_item_1, seats);
                    qrcodes.setAdapter(adapter);
                    qrcodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            POST_QR qrco = qrs.get(i);
                            Intent next = new Intent(Seat_Reserve.this, qrcode.class);
                            next.putExtra("qr", (Serializable) qrco);
                            startActivity(next);
                        }
                    });
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
                Map<String ,String> postRes = new HashMap<>();
                postRes.put("userid", sharedpreferences.getString(userid, ""));
                return postRes;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showloc);

        cancelrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent ref = new Intent(Seat_Reserve.this,Seat_Reserve.class);
                ref.putExtra("title",title.getText().toString());
                startActivity(ref);
                Seat_Reserve.this.finish();

            }
        });
        dialog.show();
    }

    public void processPayment(){
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(tripPrice)),"PHP",
        "Payment",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null){


                    String url = "https://jeraldjoemagno11.000webhostapp.com/app/insert_reserved_seat.php";

                    StringRequest reserving = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Success")) {
                                paymentSuccess();
                                Toast.makeText(getApplicationContext(), "Seat Successfully Reserved", Toast.LENGTH_LONG).show();
                            } else if (response.equals("Try Again")) {
                                Toast.makeText(getApplicationContext(), "Error in Reserving a Seat", Toast.LENGTH_LONG).show();
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
                            Map<String ,String> postRes = new HashMap<>();
                            postRes.put("busNumber", title.getText().toString());
                            postRes.put("userid", sharedpreferences.getString("userid", ""));
                            postRes.put("fullname", sharedpreferences.getString("userfullname", ""));
                            postRes.put("rate", tripPrice);
                            postRes.put("seatNo", seatvar);
                            postRes.put("state",passState);
                            postRes.put("status", "Reserved");
                            postRes.put("code", title.getText().toString() + seatvar);
                            return postRes;
                        }
                    };
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(reserving);

                     }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();

        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();

    }
    public void paymentSuccess(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Seat_Reserve.this).create();
        alertDialog.setTitle("Success");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Thank You For reserving "+ seatvar+ " of Bus "+title.getText().toString());
        alertDialog.setIcon(R.drawable.ic_done_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                scheduleNotification(getNotification("Your Qr code to Bus "+title.getText().toString() +" has Expired"),86400000 );
                Intent ref = new Intent(Seat_Reserve.this, Seat_Reserve.class);
                ref.putExtra("title", title.getText().toString());
                startActivity(ref);
                finish();
            }
        });
        alertDialog.show();
    }

    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Expired");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.appicon);
        return builder.build();
    }
}
