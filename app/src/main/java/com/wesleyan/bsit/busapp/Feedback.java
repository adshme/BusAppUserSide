package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Feedback extends AppCompatActivity {

    Spinner buses;
    EditText  feedback;
    Button submit;
    String getbusno, getfeedback;
    SimpleDateFormat sdf;
    HashMap postData;
    public static final String mypreference = "logprefs";
    SharedPreferences sharedpreferences;
    String user;
    TextView nobus;
    ProgressDialog dialog12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        dialog12 = new ProgressDialog(Feedback.this);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        long date = System.currentTimeMillis();
        buses = (Spinner)findViewById(R.id.buses);
        feedback = (EditText)findViewById(R.id.feedback);
        submit= (Button)findViewById(R.id.submit);
        nobus = (TextView)findViewById(R.id.tvnobus);
        postData = new HashMap();


        if (sharedpreferences.contains("userusername")) {
            getSupportActionBar().setTitle("Hello, " + sharedpreferences.getString("userfullname", "") );
        }else{
            getSupportActionBar().setTitle("Hello, Commuter");
        }

        String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_bus.php";

        StringRequest showbus = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")) {
                    buses.setEnabled(false);
                    nobus.setVisibility(View.VISIBLE);
                } else {
                    ArrayList<POST_Bus> buseslist = new JsonConverter<POST_Bus>().toArrayList(response, POST_Bus.class);
                    ArrayList<String> bus = new ArrayList<String>();

                    for (POST_Bus value : buseslist) {
                        bus.add(value.busNumber);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Feedback.this, android.R.layout.simple_list_item_1, bus);
                    buses.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError){
                    Timeout();
                    ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.offline, android.R.layout.simple_list_item_1);
                    pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    buses.setAdapter(pAdapter);
                    buses.setEnabled(false);

                }else if (error instanceof NoConnectionError){
                    offlineMODE();
                    ArrayAdapter<CharSequence> pAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.offline, android.R.layout.simple_list_item_1);
                    pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    buses.setAdapter(pAdapter);
                    buses.setEnabled(false);
                }
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showbus);

        sdf = new SimpleDateFormat("MMM/dd/yyyy");
        String dateString = sdf.format(date);
        postData.put("time",dateString);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getbusno = buses.getSelectedItem().toString();
                getfeedback = feedback.getText().toString();



                if(getbusno.trim().equals("")) {
                    INC();
                }else if(getfeedback.trim().equals("")){
                    INC();
                }else {

                        if (sharedpreferences.contains("userusername")) {
                           user = sharedpreferences.getString("userusername","");
                        }else{
                            user = "Anonymous";
                        }

                    String url = "https://jeraldjoemagno11.000webhostapp.com/app/insert_feedback.php";

                    StringRequest insertfeed = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            dialog12.dismiss();
                            if (response.equals("Try Again")) {
                                error();
                            } else {
                                success();
                                feedback.setText("");
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
                            postData.put("user",user);
                            postData.put("getbusno",getbusno);
                            postData.put("getfeedback",getfeedback);
                            return postData;
                        }
                    };
                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(insertfeed);
                    dialog12.setTitle("Inserting Feedback");
                    dialog12.setCancelable(false);
                    dialog12.setMessage("Please Wait");
                    dialog12.show();
                }
            }
        });
    }



    private void INC(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Feedback.this).create();
        alertDialog.setTitle("Incomplete");
        alertDialog.setMessage("Please Complete the Requirements");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Feedback.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(Feedback.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(Feedback.this).create();
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
    private void success(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Feedback.this).create();
        alertDialog.setTitle("Success");
        alertDialog.setMessage("Feedback Submit Successfully");
        alertDialog.setIcon(R.drawable.ic_done_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

