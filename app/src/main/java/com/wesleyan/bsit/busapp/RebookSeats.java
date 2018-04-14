package com.wesleyan.bsit.busapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RebookSeats extends AppCompatActivity {

    TextView title, price, warn, details, titleDialog;
    String tripPrice, seatvar, tovar, fromvar, passState,rebus,reseat;
    Button reserving, cancelres, cancelrq, paymentOK;
    Spinner options, places;
    int statecheck;
    Button s1, s2, s3, s4, s5, s6, s7, s8, s9, s10,
            s11, s12, s13, s14, s15, s16, s17, s18, s19, s20,
            s21, s22, s23, s24, s25, s26, s27, s28, s29, s30,
            s31, s32, s33, s34, s35, s36, s37, s38, s39, s40, s41;
    String se1, se2, se3, se4, se5, se6, se7, se8, se9, se10,
            se11, se12, se13, se14, se15, se16, se17, se18, se19, se20,
            se21, se22, se23, se24, se25, se26, se27, se28, se29, se30,
            se31, se32, se33, se34, se35, se36, se37, se38, se39, se40, se41;
    HashMap  postRes;
    String gettheBus;
    View  mView2, mView3, mView4;
    AlertDialog.Builder  mBuilder2, mBuilder3, mBuilder4;
    Bundle nameEmp;
    String code;
    String id;
    ListView qrcodes;
    public static final String mypreference = "logprefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;


    public static final int PAYPAL_REQUEST_CODE = 7171;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebook_seats);


        title = (TextView) findViewById(R.id.bustitle);
        s1 = (Button) findViewById(R.id.s1);
        s2 = (Button) findViewById(R.id.s2);
        s3 = (Button) findViewById(R.id.s3);
        s4 = (Button) findViewById(R.id.s4);
        s5 = (Button) findViewById(R.id.s5);
        s6 = (Button) findViewById(R.id.s6);
        s7 = (Button) findViewById(R.id.s7);
        s8 = (Button) findViewById(R.id.s8);
        s9 = (Button) findViewById(R.id.s9);
        s10 = (Button) findViewById(R.id.s10);
        s11 = (Button) findViewById(R.id.s11);
        s12 = (Button) findViewById(R.id.s12);
        s13 = (Button) findViewById(R.id.s13);
        s14 = (Button) findViewById(R.id.s14);
        s15 = (Button) findViewById(R.id.s15);
        s16 = (Button) findViewById(R.id.s16);
        s17 = (Button) findViewById(R.id.s17);
        s18 = (Button) findViewById(R.id.s18);
        s19 = (Button) findViewById(R.id.s19);
        s20 = (Button) findViewById(R.id.s20);
        s21 = (Button) findViewById(R.id.s21);
        s22 = (Button) findViewById(R.id.s22);
        s23 = (Button) findViewById(R.id.s23);
        s24 = (Button) findViewById(R.id.s24);
        s25 = (Button) findViewById(R.id.s25);
        s26 = (Button) findViewById(R.id.s26);
        s27 = (Button) findViewById(R.id.s27);
        s28 = (Button) findViewById(R.id.s28);
        s29 = (Button) findViewById(R.id.s29);
        s30 = (Button) findViewById(R.id.s30);
        s31 = (Button) findViewById(R.id.s31);
        s32 = (Button) findViewById(R.id.s32);
        s33 = (Button) findViewById(R.id.s33);
        s34 = (Button) findViewById(R.id.s34);
        s35 = (Button) findViewById(R.id.s35);
        s36 = (Button) findViewById(R.id.s36);
        s37 = (Button) findViewById(R.id.s37);
        s38 = (Button) findViewById(R.id.s38);
        s39 = (Button) findViewById(R.id.s39);
        s40 = (Button) findViewById(R.id.s40);
        s41 = (Button) findViewById(R.id.s41);

        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();


        mView2 = getLayoutInflater().inflate(R.layout.reserve_options, null);
        mBuilder2 = new AlertDialog.Builder(RebookSeats.this);
        mView3 = getLayoutInflater().inflate(R.layout.qrcodelist, null);
        mBuilder3 = new AlertDialog.Builder(RebookSeats.this);
        mView4 = getLayoutInflater().inflate(R.layout.paymentsuccess, null);
        mBuilder4 = new AlertDialog.Builder(RebookSeats.this);

        details = (TextView) mView4.findViewById(R.id.txtdetails);
        paymentOK = (Button) mView4.findViewById(R.id.btnok);

        titleDialog = (TextView) mView3.findViewById(R.id.txttitlesDialog);
        cancelrq = (Button) mView3.findViewById(R.id.cancelbtn);
        qrcodes = (ListView) mView3.findViewById(R.id.listQR);
        warn = (TextView) mView3.findViewById(R.id.warning);

        options = (Spinner) mView2.findViewById(R.id.soption);
        places = (Spinner) mView2.findViewById(R.id.splaces);
        price = (TextView) mView2.findViewById(R.id.tvprice);
        cancelres = (Button) mView2.findViewById(R.id.btncan);
        reserving = (Button) mView2.findViewById(R.id.btnres);

        postRes = new HashMap();

        getSupportActionBar().setTitle("Rebook QR");
        Intent i = new Intent(this, PayPalService.class);
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(i);


        if (isConnectingToInternet(RebookSeats.this)) {
            POST_BusAvail businfo = (POST_BusAvail) getIntent().getSerializableExtra("busAvail");

            if (businfo != null) {
                nameEmp = getIntent().getExtras();
                code = nameEmp.getString("code");
                rebus = nameEmp.getString("rebus");
                reseat = nameEmp.getString("reseat");
                title.setText("" + businfo.busNumber);
                gettheBus = businfo.busNumber;

            } else if (getIntent().getExtras() != null) {

                nameEmp = getIntent().getExtras();
                gettheBus = nameEmp.getString("title");
                code = nameEmp.getString("code");
                rebus = nameEmp.getString("rebus");
                reseat = nameEmp.getString("reseat");
                title.setText("" + gettheBus);

            }

            String url = "https://jeraldjoemagno11.000webhostapp.com/app/trip_loc.php";

            StringRequest showRoute = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("No Results Found.")) {
                        Toast.makeText(RebookSeats.this, "Error", Toast.LENGTH_SHORT).show();
                    } else {
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
                    if (error instanceof TimeoutError) {
                        Timeout();
                    } else if (error instanceof NoConnectionError) {
                        offlineMODE();
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> postData = new HashMap<>();
                    postData.put("busNumber", gettheBus);
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


                    if (se1.equals("Occupied")) {
                        s1.setEnabled(false);
                        s1.setTextColor(Color.WHITE);
                        s1.setBackgroundColor(Color.BLUE);
                    } else if (se1.equals("Reserved")) {
                        s1.setEnabled(false);
                        s1.setTextColor(Color.WHITE);
                        s1.setBackgroundColor(Color.RED);
                    } else {
                        s1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s1");
                                seatvar = "s1";
                                Reserving();

                            }
                        });
                    }


                    if (se2.equals("Occupied")) {
                        s2.setEnabled(false);
                        s2.setTextColor(Color.WHITE);
                        s2.setBackgroundColor(Color.BLUE);
                    } else if (se2.equals("Reserved")) {
                        s2.setEnabled(false);
                        s2.setTextColor(Color.WHITE);
                        s2.setBackgroundColor(Color.RED);
                    } else {
                        s2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s2");
                                seatvar = "s2";
                                Reserving();

                            }
                        });
                    }


                    if (se3.equals("Occupied")) {
                        s3.setEnabled(false);
                        s3.setTextColor(Color.WHITE);
                        s3.setBackgroundColor(Color.BLUE);
                    } else if (se3.equals("Reserved")) {
                        s3.setEnabled(false);
                        s3.setTextColor(Color.WHITE);
                        s3.setBackgroundColor(Color.RED);
                    } else {
                        s3.setEnabled(true);
                        s3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s3");
                                seatvar = "s3";
                                Reserving();

                            }
                        });
                    }


                    if (se4.equals("Occupied")) {
                        s4.setEnabled(false);
                        s4.setTextColor(Color.WHITE);
                        s4.setBackgroundColor(Color.BLUE);
                    } else if (se4.equals("Reserved")) {
                        s4.setEnabled(false);
                        s4.setTextColor(Color.WHITE);
                        s4.setBackgroundColor(Color.RED);
                    } else {
                        s4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s4");
                                seatvar = "s4";
                                Reserving();
                            }
                        });
                    }


                    if (se5.equals("Occupied")) {
                        s5.setEnabled(false);
                        s5.setTextColor(Color.WHITE);
                        s5.setBackgroundColor(Color.BLUE);
                    } else if (se5.equals("Reserved")) {
                        s5.setEnabled(false);
                        s5.setTextColor(Color.WHITE);
                        s5.setBackgroundColor(Color.RED);
                    } else {
                        s5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s5");
                                seatvar = "s5";
                                Reserving();
                            }
                        });
                    }


                    if (se6.equals("Occupied")) {
                        s6.setEnabled(false);
                        s6.setTextColor(Color.WHITE);
                        s6.setBackgroundColor(Color.BLUE);
                    } else if (se6.equals("Reserved")) {
                        s6.setEnabled(false);
                        s6.setTextColor(Color.WHITE);
                        s6.setBackgroundColor(Color.RED);
                    } else {
                        s6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s6");
                                seatvar = "s6";
                                Reserving();
                            }
                        });
                    }


                    if (se7.equals("Occupied")) {
                        s7.setEnabled(false);
                        s7.setTextColor(Color.WHITE);
                        s7.setBackgroundColor(Color.BLUE);
                    } else if (se7.equals("Reserved")) {
                        s7.setEnabled(false);
                        s7.setTextColor(Color.WHITE);
                        s7.setBackgroundColor(Color.RED);
                    } else {
                        s7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s7");
                                seatvar = "s7";
                                Reserving();
                            }
                        });
                    }


                    if (se8.equals("Occupied")) {
                        s8.setEnabled(false);
                        s8.setTextColor(Color.WHITE);
                        s8.setBackgroundColor(Color.BLUE);
                    } else if (se8.equals("Reserved")) {
                        s8.setEnabled(false);
                        s8.setTextColor(Color.WHITE);
                        s8.setBackgroundColor(Color.RED);
                    } else {
                        s8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s8");
                                seatvar = "s8";
                                Reserving();
                            }
                        });
                    }


                    if (se9.equals("Occupied")) {
                        s9.setEnabled(false);
                        s9.setTextColor(Color.WHITE);
                        s9.setBackgroundColor(Color.BLUE);
                    } else if (se9.equals("Reserved")) {
                        s9.setEnabled(false);
                        s9.setTextColor(Color.WHITE);
                        s9.setBackgroundColor(Color.RED);
                    } else {
                        s9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s9");
                                seatvar = "s9";
                                Reserving();
                            }
                        });
                    }


                    if (se10.equals("Occupied")) {
                        s10.setEnabled(false);
                        s10.setTextColor(Color.WHITE);
                        s10.setBackgroundColor(Color.BLUE);
                    } else if (se10.equals("Reserved")) {
                        s10.setEnabled(false);
                        s10.setTextColor(Color.WHITE);
                        s10.setBackgroundColor(Color.RED);
                    } else {
                        s10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s10");
                                seatvar = "s10";
                                Reserving();
                            }
                        });
                    }


                    if (se11.equals("Occupied")) {
                        s11.setEnabled(false);
                        s11.setTextColor(Color.WHITE);
                        s11.setBackgroundColor(Color.BLUE);
                    } else if (se11.equals("Reserved")) {
                        s11.setEnabled(false);
                        s11.setTextColor(Color.WHITE);
                        s11.setBackgroundColor(Color.RED);
                    } else {
                        s11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s11");
                                seatvar = "s11";
                                Reserving();
                            }
                        });
                    }


                    if (se12.equals("Occupied")) {
                        s12.setEnabled(false);
                        s12.setTextColor(Color.WHITE);
                        s12.setBackgroundColor(Color.BLUE);
                    } else if (se12.equals("Reserved")) {
                        s12.setEnabled(false);
                        s12.setTextColor(Color.WHITE);
                        s12.setBackgroundColor(Color.RED);
                    } else {
                        s12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s12");
                                seatvar = "s12";
                                Reserving();
                            }
                        });
                    }


                    if (se13.equals("Occupied")) {
                        s13.setEnabled(false);
                        s13.setTextColor(Color.WHITE);
                        s13.setBackgroundColor(Color.BLUE);
                    } else if (se13.equals("Reserved")) {
                        s13.setEnabled(false);
                        s13.setTextColor(Color.WHITE);
                        s13.setBackgroundColor(Color.RED);
                    } else {
                        s13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s13");
                                seatvar = "s13";
                                Reserving();
                            }
                        });
                    }


                    if (se14.equals("Occupied")) {
                        s14.setEnabled(false);
                        s14.setTextColor(Color.WHITE);
                        s14.setBackgroundColor(Color.BLUE);
                    } else if (se14.equals("Reserved")) {
                        s14.setEnabled(false);
                        s14.setTextColor(Color.WHITE);
                        s14.setBackgroundColor(Color.RED);
                    } else {
                        s14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s14");
                                seatvar = "s14";
                                Reserving();
                            }
                        });
                    }


                    if (se15.equals("Occupied")) {
                        s15.setEnabled(false);
                        s15.setTextColor(Color.WHITE);
                        s15.setBackgroundColor(Color.BLUE);
                    } else if (se15.equals("Reserved")) {
                        s15.setEnabled(false);
                        s15.setTextColor(Color.WHITE);
                        s15.setBackgroundColor(Color.RED);
                    } else {
                        s15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s15");
                                seatvar = "s15";
                                Reserving();
                            }
                        });
                    }


                    if (se16.equals("Occupied")) {
                        s16.setEnabled(false);
                        s16.setTextColor(Color.WHITE);
                        s16.setBackgroundColor(Color.BLUE);
                    } else if (se16.equals("Reserved")) {
                        s16.setEnabled(false);
                        s16.setTextColor(Color.WHITE);
                        s16.setBackgroundColor(Color.RED);
                    } else {
                        s16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s16");
                                seatvar = "s16";
                                Reserving();
                            }
                        });
                    }


                    if (se17.equals("Occupied")) {
                        s17.setEnabled(false);
                        s17.setTextColor(Color.WHITE);
                        s17.setBackgroundColor(Color.BLUE);
                    } else if (se17.equals("Reserved")) {
                        s17.setEnabled(false);
                        s17.setTextColor(Color.WHITE);
                        s17.setBackgroundColor(Color.RED);
                    } else {
                        s17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s17");
                                seatvar = "s17";
                                Reserving();
                            }
                        });
                    }


                    if (se18.equals("Occupied")) {
                        s18.setEnabled(false);
                        s18.setTextColor(Color.WHITE);
                        s18.setBackgroundColor(Color.BLUE);
                    } else if (se18.equals("Reserved")) {
                        s18.setEnabled(false);
                        s18.setTextColor(Color.WHITE);
                        s18.setBackgroundColor(Color.RED);
                    } else {
                        s18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s18");
                                seatvar = "s18";
                                Reserving();

                            }
                        });
                    }


                    if (se19.equals("Occupied")) {
                        s19.setEnabled(false);
                        s19.setTextColor(Color.WHITE);
                        s19.setBackgroundColor(Color.BLUE);
                    } else if (se19.equals("Reserved")) {
                        s19.setEnabled(false);
                        s19.setTextColor(Color.WHITE);
                        s19.setBackgroundColor(Color.RED);
                    } else {
                        s19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s19");
                                seatvar = "s19";
                                Reserving();

                            }
                        });
                    }


                    if (se20.equals("Occupied")) {
                        s20.setEnabled(false);
                        s20.setTextColor(Color.WHITE);
                        s20.setBackgroundColor(Color.BLUE);
                    } else if (se20.equals("Reserved")) {
                        s20.setEnabled(false);
                        s20.setTextColor(Color.WHITE);
                        s20.setBackgroundColor(Color.RED);
                    } else {
                        s20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s20");
                                seatvar = "s20";
                                Reserving();

                            }
                        });
                    }


                    if (se21.equals("Occupied")) {
                        s21.setEnabled(false);
                        s21.setTextColor(Color.WHITE);
                        s21.setBackgroundColor(Color.BLUE);
                    } else if (se21.equals("Reserved")) {
                        s21.setEnabled(false);
                        s21.setTextColor(Color.WHITE);
                        s21.setBackgroundColor(Color.RED);
                    } else {
                        s21.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s21");
                                seatvar = "s21";
                                Reserving();
                            }
                        });
                    }


                    if (se22.equals("Occupied")) {
                        s22.setEnabled(false);
                        s22.setTextColor(Color.WHITE);
                        s22.setBackgroundColor(Color.BLUE);
                    } else if (se22.equals("Reserved")) {
                        s22.setEnabled(false);
                        s22.setTextColor(Color.WHITE);
                        s22.setBackgroundColor(Color.RED);
                    } else {
                        s22.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s22");
                                seatvar = "s22";
                                Reserving();
                            }
                        });
                    }


                    if (se23.equals("Occupied")) {
                        s23.setEnabled(false);
                        s23.setTextColor(Color.WHITE);
                        s23.setBackgroundColor(Color.BLUE);
                    } else if (se23.equals("Reserved")) {
                        s23.setEnabled(false);
                        s23.setTextColor(Color.WHITE);
                        s23.setBackgroundColor(Color.RED);
                    } else {
                        s23.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s23");
                                seatvar = "s23";
                                Reserving();
                            }
                        });
                    }


                    if (se24.equals("Occupied")) {
                        s24.setEnabled(false);
                        s24.setTextColor(Color.WHITE);
                        s24.setBackgroundColor(Color.BLUE);
                    } else if (se24.equals("Reserved")) {
                        s24.setEnabled(false);
                        s24.setTextColor(Color.WHITE);
                        s24.setBackgroundColor(Color.RED);
                    } else {
                        s24.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s24");
                                seatvar = "s24";
                                Reserving();
                            }
                        });
                    }


                    if (se25.equals("Occupied")) {
                        s25.setEnabled(false);
                        s25.setTextColor(Color.WHITE);
                        s25.setBackgroundColor(Color.BLUE);
                    } else if (se25.equals("Reserved")) {
                        s25.setEnabled(false);
                        s25.setTextColor(Color.WHITE);
                        s25.setBackgroundColor(Color.RED);
                    } else {
                        s25.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s25");
                                seatvar = "s25";
                                Reserving();
                            }
                        });
                    }


                    if (se26.equals("Occupied")) {
                        s26.setEnabled(false);
                        s26.setTextColor(Color.WHITE);
                        s26.setBackgroundColor(Color.BLUE);
                    } else if (se26.equals("Reserved")) {
                        s26.setEnabled(false);
                        s26.setTextColor(Color.WHITE);
                        s26.setBackgroundColor(Color.RED);
                    } else {
                        s26.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s26");
                                seatvar = "s26";
                                Reserving();
                            }
                        });
                    }


                    if (se27.equals("Occupied")) {
                        s27.setEnabled(false);
                        s27.setTextColor(Color.WHITE);
                        s27.setBackgroundColor(Color.BLUE);
                    } else if (se27.equals("Reserved")) {
                        s27.setEnabled(false);
                        s27.setTextColor(Color.WHITE);
                        s27.setBackgroundColor(Color.RED);
                    } else {
                        s27.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s27");
                                seatvar = "s27";
                                Reserving();
                            }
                        });
                    }


                    if (se28.equals("Occupied")) {
                        s28.setEnabled(false);
                        s28.setTextColor(Color.WHITE);
                        s28.setBackgroundColor(Color.BLUE);
                    } else if (se28.equals("Reserved")) {
                        s28.setEnabled(false);
                        s28.setTextColor(Color.WHITE);
                        s28.setBackgroundColor(Color.RED);
                    } else {
                        s28.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s28");
                                seatvar = "s28";
                                Reserving();
                            }
                        });
                    }


                    if (se29.equals("Occupied")) {
                        s29.setEnabled(false);
                        s29.setTextColor(Color.WHITE);
                        s29.setBackgroundColor(Color.BLUE);
                    } else if (se29.equals("Reserved")) {
                        s29.setEnabled(false);
                        s29.setTextColor(Color.WHITE);
                        s29.setBackgroundColor(Color.RED);
                    } else {
                        s29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s29");
                                seatvar = "s29";
                                Reserving();
                            }
                        });
                    }


                    if (se30.equals("Occupied")) {
                        s30.setEnabled(false);
                        s30.setTextColor(Color.WHITE);
                        s30.setBackgroundColor(Color.BLUE);
                    } else if (se30.equals("Reserved")) {
                        s30.setEnabled(false);
                        s30.setTextColor(Color.WHITE);
                        s30.setBackgroundColor(Color.RED);
                    } else {
                        s30.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s30");
                                seatvar = "s30";
                                Reserving();
                            }
                        });
                    }


                    if (se31.equals("Occupied")) {
                        s31.setEnabled(false);
                        s31.setTextColor(Color.WHITE);
                        s31.setBackgroundColor(Color.BLUE);
                    } else if (se31.equals("Reserved")) {
                        s31.setEnabled(false);
                        s31.setTextColor(Color.WHITE);
                        s31.setBackgroundColor(Color.RED);
                    } else {
                        s31.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s31");
                                seatvar = "s31";
                                Reserving();
                            }
                        });
                    }


                    if (se32.equals("Occupied")) {
                        s32.setEnabled(false);
                        s32.setTextColor(Color.WHITE);
                        s32.setBackgroundColor(Color.BLUE);
                    } else if (se32.equals("Reserved")) {
                        s32.setEnabled(false);
                        s32.setTextColor(Color.WHITE);
                        s32.setBackgroundColor(Color.RED);
                    } else {
                        s32.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s32");
                                seatvar = "s32";
                                Reserving();
                            }
                        });
                    }


                    if (se33.equals("Occupied")) {
                        s33.setEnabled(false);
                        s33.setTextColor(Color.WHITE);
                        s33.setBackgroundColor(Color.BLUE);
                    } else if (se33.equals("Reserved")) {
                        s33.setEnabled(false);
                        s33.setTextColor(Color.WHITE);
                        s33.setBackgroundColor(Color.RED);
                    } else {
                        s33.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s33");
                                seatvar = "s33";
                                Reserving();
                            }
                        });
                    }


                    if (se34.equals("Occupied")) {
                        s34.setEnabled(false);
                        s34.setTextColor(Color.WHITE);
                        s34.setBackgroundColor(Color.BLUE);
                    } else if (se34.equals("Reserved")) {
                        s34.setEnabled(false);
                        s34.setTextColor(Color.WHITE);
                        s34.setBackgroundColor(Color.RED);
                    } else {
                        s34.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s34");
                                seatvar = "s34";
                                Reserving();
                            }
                        });
                    }


                    if (se35.equals("Occupied")) {
                        s35.setEnabled(false);
                        s35.setTextColor(Color.WHITE);
                        s35.setBackgroundColor(Color.BLUE);
                    } else if (se35.equals("Reserved")) {
                        s35.setEnabled(false);
                        s35.setTextColor(Color.WHITE);
                        s35.setBackgroundColor(Color.RED);
                    } else {
                        s35.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s35");
                                seatvar = "s35";
                                Reserving();
                            }
                        });
                    }


                    if (se36.equals("Occupied")) {
                        s36.setEnabled(false);
                        s36.setTextColor(Color.WHITE);
                        s36.setBackgroundColor(Color.BLUE);
                    } else if (se36.equals("Reserved")) {
                        s36.setEnabled(false);
                        s36.setTextColor(Color.WHITE);
                        s36.setBackgroundColor(Color.RED);
                    } else {
                        s36.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s36");
                                seatvar = "s36";
                                Reserving();
                            }
                        });
                    }


                    if (se37.equals("Occupied")) {
                        s37.setEnabled(false);
                        s37.setTextColor(Color.WHITE);
                        s37.setBackgroundColor(Color.BLUE);
                    } else if (se37.equals("Reserved")) {
                        s37.setEnabled(false);
                        s37.setTextColor(Color.WHITE);
                        s37.setBackgroundColor(Color.RED);
                    } else {
                        s37.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s37");
                                seatvar = "s37";
                                Reserving();
                            }
                        });
                    }


                    if (se38.equals("Occupied")) {
                        s38.setEnabled(false);
                        s38.setTextColor(Color.WHITE);
                        s38.setBackgroundColor(Color.BLUE);
                    } else if (se38.equals("Reserved")) {
                        s38.setEnabled(false);
                        s38.setTextColor(Color.WHITE);
                        s38.setBackgroundColor(Color.RED);
                    } else {
                        s38.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s38");
                                seatvar = "s38";
                                Reserving();
                            }
                        });
                    }


                    if (se39.equals("Occupied")) {
                        s39.setEnabled(false);
                        s39.setTextColor(Color.WHITE);
                        s39.setBackgroundColor(Color.BLUE);
                    } else if (se39.equals("Reserved")) {
                        s39.setEnabled(false);
                        s39.setTextColor(Color.WHITE);
                        s39.setBackgroundColor(Color.RED);
                    } else {
                        s39.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s39");
                                seatvar = "s39";
                                Reserving();
                            }
                        });
                    }


                    if (se40.equals("Occupied")) {
                        s40.setEnabled(false);
                        s40.setTextColor(Color.WHITE);
                        s40.setBackgroundColor(Color.BLUE);
                    } else if (se40.equals("Reserved")) {
                        s40.setEnabled(false);
                        s40.setTextColor(Color.WHITE);
                        s40.setBackgroundColor(Color.RED);
                    } else {
                        s40.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s40");
                                seatvar = "s40";
                                Reserving();
                            }
                        });
                    }


                    if (se41.equals("Occupied")) {
                        s41.setEnabled(false);
                        s41.setTextColor(Color.WHITE);
                        s41.setBackgroundColor(Color.BLUE);
                    } else if (se41.equals("Reserved")) {
                        s41.setEnabled(false);
                        s41.setTextColor(Color.WHITE);
                        s41.setBackgroundColor(Color.RED);
                    } else {
                        s41.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                postRes.put("seatNo", "s41");
                                seatvar = "s41";
                                Reserving();
                            }
                        });
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError) {
                        Timeout();
                    } else if (error instanceof NoConnectionError) {
                        offlineMODE();
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> postData = new HashMap<>();
                    postData.put("busNumber", gettheBus);
                    return postData;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showSeatStatus);


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

    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(RebookSeats.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(RebookSeats.this).create();
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
    private void error(){
        final AlertDialog alertDialog = new AlertDialog.Builder(RebookSeats.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(RebookSeats.this).create();
        alertDialog.setTitle("Reserved");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("The sit that you are reserving is already reserved");
        alertDialog.setIcon(R.drawable.ic_clear_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                Intent ref = new Intent(RebookSeats.this,RebookSeats.class);
                ref.putExtra("title",title.getText().toString());
                ref.putExtra("code",code);
                ref.putExtra("rebus",rebus);
                ref.putExtra("reseat",reseat);
                startActivity(ref);
                finish();
            }
        });
        alertDialog.show();
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
                            Intent ref = new Intent(RebookSeats.this, RebookSeats.class);
                            ref.putExtra("title", title.getText().toString());
                            ref.putExtra("code",code);
                            ref.putExtra("rebus",rebus);
                            ref.putExtra("reseat",reseat);
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
                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
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
                }else if (i==1){
                    if (statecheck==1){
                        tripPrice= "20.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "16.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==8){
                    if (statecheck==1){
                        tripPrice= "42.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "33.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==9){
                    if (statecheck==1){
                        tripPrice= "48";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "38.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==10){
                    if (statecheck==1){
                        tripPrice= "52.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "41.8";
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
                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
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
                }else if (i==1){
                    if (statecheck==1){
                        tripPrice= "20.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "16.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
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
                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
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
                }else if (i==1){
                    if (statecheck==1){
                        tripPrice= "10";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
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
                }else if(i==2){
                    statecheck = 2;
                    postRes.put("state","Student");
                    passState = "Student";
                    places.setEnabled(true);
                }else if (i==3){
                    statecheck = 3;
                    postRes.put("state","Senior Citizen");
                    passState = "Senior Citizen";
                    places.setEnabled(true);
                }else if (i==4){
                    statecheck = 4;
                    postRes.put("state","PWD");
                    passState = "PWD";
                    places.setEnabled(true);
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
                }else if (i==1){
                    if (statecheck==1){
                        tripPrice= "20.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "16.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==2){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==3){
                    if (statecheck==1){
                        tripPrice= "27.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "21.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==4){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==5){
                    if (statecheck==1){
                        tripPrice= "34.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "27.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==6){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==7){
                    if (statecheck==1){
                        tripPrice= "37";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "29.6";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if(i==8){
                    if (statecheck==1){
                        tripPrice= "42.2";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "33.8";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==9){
                    if (statecheck==1){
                        tripPrice= "48";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "38.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);
                    }
                    reserving.setEnabled(true);
                }else if (i==10){
                    if (statecheck==1){
                        tripPrice= "52.4";
                        price.setText("₱ "+tripPrice);
                        price.setTextColor(Color.GREEN);

                    }else{
                        tripPrice= "41.8";
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
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null){


                    String url = "https://jeraldjoemagno11.000webhostapp.com/app/insert_rebook_seat.php";

                    StringRequest rebooking = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
                            postRes.put("recode",code);
                            postRes.put("rate", tripPrice);
                            postRes.put("rebus",rebus);
                            postRes.put("seatNo", seatvar);
                            postRes.put("state",passState);
                            postRes.put("reseat",reseat);
                            postRes.put("status", "Reserved");
                            postRes.put("code", title.getText().toString() + seatvar);
                            return postRes;
                        }
                    };
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(rebooking);
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();

        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();

    }
    public void paymentSuccess(){
        final AlertDialog alertDialog = new AlertDialog.Builder(RebookSeats.this).create();
        alertDialog.setTitle("Success");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Thank You For rebooking "+ seatvar+ " of Bus "+title.getText().toString());
        alertDialog.setIcon(R.drawable.ic_done_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                scheduleNotification(getNotification("Your Qr code to Bus "+title.getText().toString() +" has Expired"),86400000 );
                Intent ref = new Intent(RebookSeats.this, Seat_Reserve.class);
                ref.putExtra("title", rebus);
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
