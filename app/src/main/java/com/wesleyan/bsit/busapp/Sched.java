package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Sched extends AppCompatActivity {

    TextView curDate, curTime,txtmes;
    Button changeDate;
    String checkTime;
    Calendar myCalendar;
    SimpleDateFormat sdf;
    ListView listtime, listbus, listfrom,listto;
    HashMap postData;
    ProgressDialog dialog12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sched);

        dialog12 = new ProgressDialog(Sched.this);
        txtmes = (TextView)findViewById(R.id.txtmes);
        myCalendar = Calendar.getInstance();
        curDate = (TextView) findViewById(R.id.curDate);
        curTime = (TextView) findViewById(R.id.curTime);
        listtime = (ListView)findViewById(R.id.listtime);
        listbus = (ListView)findViewById(R.id.listbus);
        listfrom = (ListView)findViewById(R.id.listfrom);
        listto = (ListView)findViewById(R.id.listto);
        changeDate = (Button) findViewById(R.id.changeDate);
        postData = new HashMap();
        myCalendar = Calendar.getInstance();
        long date = System.currentTimeMillis();

        sdf = new SimpleDateFormat("MMM/dd/yyyy");
        String dateString = sdf.format(date);
        curDate.setText(dateString);

        if(isConnectingToInternet(Sched.this)){

            postData.put("curDate",curDate.getText().toString());
            load();
                    }else{
            offlineMODE();
        }
        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MMM/dd/yyyy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.UK);

                curDate.setText(sdf.format(myCalendar.getTime()));

            }

        };

        curDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Sched.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
        curTime.setEnabled(false);
        curTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTime = curTime.getText().toString();
                if(checkTime.equals("AM")) {
                    curTime.setText("PM");
                }else{
                    curTime.setText("AM");
                }
            }
        });

        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectingToInternet(Sched.this)) {
                    load();
                    dialog12.setTitle("Loading Bus Information");
                    dialog12.setCancelable(false);
                    dialog12.setMessage("Please Wait");
                    dialog12.show();
                }else{
                    offlineMODE();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custommenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                Intent ref = new Intent(Sched.this, Sched.class);
                startActivity(ref);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
        final AlertDialog alertDialog = new AlertDialog.Builder(Sched.this).create();
        alertDialog.setTitle("Offline");
        alertDialog.setMessage("Please Check your Internet Connection");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void Timeout(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Sched.this).create();
        alertDialog.setTitle("Connection Timeout");
        alertDialog.setMessage("Please Check your Internet Connection");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void sched(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Sched.this).create();
        alertDialog.setTitle("No Schedule");
        alertDialog.setMessage("No Schedule Found, Please Try Again Later");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void load(){

        String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_sched.php";

        StringRequest showsched = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog12.dismiss();
                if (response.equals("No Results Found.")) {
                    txtmes.setVisibility(View.VISIBLE);
                    listtime.setVisibility(View.INVISIBLE);
                    listbus.setVisibility(View.INVISIBLE);
                    listfrom.setVisibility(View.INVISIBLE);
                    listto.setVisibility(View.INVISIBLE);
                } else {
                    txtmes.setVisibility(View.GONE);
                    listtime.setVisibility(View.VISIBLE);
                    listbus.setVisibility(View.VISIBLE);
                    listfrom.setVisibility(View.VISIBLE);
                    listto.setVisibility(View.VISIBLE);
                    listtime.setEnabled(false);
                    listbus.setEnabled(false);
                    listfrom.setEnabled(false);
                    listto.setEnabled(false);
                    ArrayList<POST> schedtime = new JsonConverter<POST>().toArrayList(response, POST.class);
                    ArrayList<String> departs = new ArrayList<String>();
                    ArrayList<String> bus = new ArrayList<String>();
                    ArrayList<String> from = new ArrayList<String>();
                    ArrayList<String> to = new ArrayList<String>();
                    for (POST value : schedtime) {
                        departs.add(value.departureTime);
                        bus.add(value.busNumber);
                        from.add(value.fromWhere);
                        to.add(value.toWhere);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Sched.this, android.R.layout.simple_list_item_1, departs);
                    listtime.setAdapter(adapter);
                    ArrayAdapter<String> busadapter = new ArrayAdapter<String>(Sched.this, android.R.layout.simple_list_item_1, bus);
                    listbus.setAdapter(busadapter);
                    ArrayAdapter<String> fromadapter = new ArrayAdapter<String>(Sched.this, android.R.layout.simple_list_item_1, from);
                    listfrom.setAdapter(fromadapter);
                    ArrayAdapter<String> toadapter = new ArrayAdapter<String>(Sched.this, android.R.layout.simple_list_item_1, to);
                    listto.setAdapter(toadapter);
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
                postData.put("curDate", curDate.getText().toString());
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showsched);




    }
}
