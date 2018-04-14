package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;

import java.io.Serializable;
import java.util.ArrayList;

public class Reservation extends AppCompatActivity implements Serializable {

    ListView list_bus;
    ArrayList<POST_BUSLIST> listofbus2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        getSupportActionBar().setTitle("Seat Reservation");
        list_bus = (ListView)findViewById(R.id.olList);
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.list_layout_controller);
        list_bus.setLayoutAnimation(controller);

        String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_busReserved.php";

        StringRequest list = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")) {
                    noOLbus();
                } else {
                    listofbus2 = new JsonConverter<POST_BUSLIST>().toArrayList(response, POST_BUSLIST.class);
                    ArrayList<String> busName = new ArrayList<String>();
                    for (POST_BUSLIST value : listofbus2) {
                        busName.add(value.busNumber);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sizetext, busName);
                    list_bus.setAdapter(adapter);
                    list_bus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            POST_BUSLIST selectbus = listofbus2.get(i);
                            Intent nextpage = new Intent(Reservation.this, Seat_Reserve.class);
                            nextpage.putExtra("bus2", (Serializable) selectbus);
                            startActivity(nextpage);
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
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(list);

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
                Intent ref = new Intent(Reservation.this, Reservation.class);
                startActivity(ref);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Reservation.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(Reservation.this).create();
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
    private void noOLbus(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Reservation.this).create();
        alertDialog.setTitle("Online Buses");
        alertDialog.setMessage("No Online Bus");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}


