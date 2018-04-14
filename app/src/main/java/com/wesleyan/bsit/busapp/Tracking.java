package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tracking extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    HashMap postData;
    Handler  handler;
    LatLng curLoc;
    String title;
    String fromvar,tovar;
    double lon,lat;
    Bundle nameBus;
    String namesB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        handler = new Handler();
        postData = new HashMap();

        nameBus = getIntent().getExtras();
        namesB = nameBus.getString("busid");



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String url = "https://jeraldjoemagno11.000webhostapp.com/app/trip_loc.php";

        StringRequest showtrack = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")) {
                    Toast.makeText(Tracking.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<POST_BUSLIST> trip = new JsonConverter<POST_BUSLIST>().toArrayList(response, POST_BUSLIST.class);
                    for (POST_BUSLIST value : trip) {
                        fromvar = value.from;
                        tovar = value.to;
                    }
                    firstSave();

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
                postData.put("BusName",namesB);
                postData.put("busNumber",namesB);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showtrack);

            handler.postDelayed(runLocation, 30000);

    }
    public void firstSave(){

        String url1 = "https://jeraldjoemagno11.000webhostapp.com/app/location.php";

        StringRequest track = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")) {
                    Toast.makeText(Tracking.this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<POST_Location> empinfo = new JsonConverter<POST_Location>().toArrayList(response, POST_Location.class);
                    for (POST_Location value : empinfo) {
                        title = value.BusNumber;
                        lat = value.Latitude;
                        lon = value.Longitude;
                    }

                    if (fromvar.equals("Cubao") && tovar.equals("San Jose City")) {
                        mMap.clear();
                        SanJoseCubao();
                        curLoc = new LatLng(lat,lon);
                        mMap.addMarker(new MarkerOptions().position(curLoc).title("Bus "+ title).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))).showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curLoc,16));

                    } else if (fromvar.equals("San Jose City") && tovar.equals("Cubao")) {
                        mMap.clear();
                        SanJoseCubao();
                        curLoc = new LatLng(lat,lon);
                        mMap.addMarker(new MarkerOptions().position(curLoc).title("Bus "+ title).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))).showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curLoc,16));
                    } else {
                        mMap.clear();
                        Cabanatuan();
                        curLoc = new LatLng(lat,lon);
                        mMap.addMarker(new MarkerOptions().position(curLoc).title("Bus "+ title).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))).showInfoWindow();
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curLoc,16));

                    }



                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError){
                    Timeout();
                    handler.removeCallbacks(runLocation);
                }else if (error instanceof NoConnectionError){
                    offlineMODE();
                    handler.removeCallbacks(runLocation);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> postData = new HashMap<>();
                postData.put("BusName",namesB);
                postData.put("busNumber",namesB);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(track);

    }

    public Runnable runLocation = new Runnable(){
        @Override
        public void run() {
            mMap.clear();
            String url2 = "https://jeraldjoemagno11.000webhostapp.com/app/trip_loc.php";

            StringRequest trackagain = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("No Results Found.")) {
                        Toast.makeText(Tracking.this, "Error", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<POST_BUSLIST> trip = new JsonConverter<POST_BUSLIST>().toArrayList(response, POST_BUSLIST.class);
                        for (POST_BUSLIST value : trip) {
                            fromvar = value.from;
                            tovar = value.to;
                        }
                        firstSave();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError){
                        Timeout();
                        Tracking.this.handler.removeCallbacks(runLocation);
                    }else if (error instanceof NoConnectionError){
                        offlineMODE();
                        Tracking.this.handler.removeCallbacks(runLocation);
                    }
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> postData = new HashMap<>();
                    postData.put("BusName",namesB);
                    postData.put("busNumber",namesB);
                    return postData;
                }
            };
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(trackagain);

            Toast.makeText(Tracking.this, "location check", Toast.LENGTH_SHORT).show();
            Tracking.this.handler.postDelayed(Tracking.this.runLocation, 30000);

        }



    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runLocation);
    }


    public void SanJoseCubao(){
        LatLng ter = new LatLng(15.793947,120.988314);
        mMap.addMarker(new MarkerOptions().position(ter).title("San Jose City Terminal"));


        LatLng ter3 = new LatLng(14.624645,121.049276);
        mMap.addMarker(new MarkerOptions().position(ter3).title("Cubao Terminal"));

        // Add a marker in Sydney and move the camera
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(15.793947,120.988314),
                        new LatLng(15.794060,120.988885),
                        new LatLng(15.793926,120.989076),
                        new LatLng(15.793751,120.989196),
                        new LatLng(15.791965,120.990030),
                        new LatLng(15.791866,120.990036),
                        new LatLng(15.791203,120.988869),
                        new LatLng(15.791131,120.988724),
                        new LatLng(15.790602,120.987579),
                        new LatLng(15.789071,120.981960),
                        new LatLng(15.788903,120.981412),
                        new LatLng(15.785120,120.973220),
                        new LatLng(15.784634,120.972404),
                        new LatLng(15.781651,120.969486),
                        new LatLng(15.780577,120.968317),
                        new LatLng(15.779875,120.967405),
                        new LatLng(15.778749,120.966772),
                        new LatLng(15.770479,120.963650),
                        new LatLng(15.769591,120.963253),
                        new LatLng(15.768962,120.962845),
                        new LatLng(15.747701,120.946054),
                        new LatLng(15.747133,120.945604),
                        new LatLng(15.746493,120.945368),
                        new LatLng(15.743994,120.944327),
                        new LatLng(15.743674,120.944145),
                        new LatLng(15.743333,120.943866),
                        new LatLng(15.715884,120.914812),
                        new LatLng(15.713508,120.908911),
                        new LatLng(15.713188,120.908321),
                        new LatLng(15.712785,120.907870),
                        new LatLng(15.706568,120.902849),
                        new LatLng(15.705762,120.902248),
                        new LatLng(15.705236,120.901969),
                        new LatLng(15.702943,120.901412),
                        new LatLng(15.700629,120.900843),
                        new LatLng(15.685756,120.892002),
                        new LatLng(15.642513,120.885587),
                        new LatLng(15.642048,120.886112),
                        new LatLng(15.637533,120.898332),
                        new LatLng(15.636820,120.898794),
                        new LatLng(15.625248,120.904426),
                        new LatLng(15.607631,120.921177),
                        new LatLng(15.600883,120.921059),
                        new LatLng(15.582054,120.919685),
                        new LatLng(15.562159,120.924707),
                        new LatLng(15.557777,120.925865),
                        new LatLng(15.550859,120.931726),
                        new LatLng(15.549588,120.933357),
                        new LatLng(15.548513,120.933518),
                        new LatLng(15.544678,120.932150),
                        new LatLng(15.538507,120.933008),
                        new LatLng(15.532894,120.933201),
                        new LatLng(15.529979,120.934103),
                        new LatLng(15.527778,120.935422),
                        new LatLng(15.520190,120.938287),
                        new LatLng(15.518546,120.953372),
                        new LatLng(15.517564,120.954445),
                        new LatLng(15.508746,120.959375),
                        new LatLng(15.503959,120.976595),
                        new LatLng(15.502088,120.977271),
                        new LatLng(15.487541,120.974417),
                        new LatLng(15.486610,120.973548),
                        new LatLng(15.481730,120.961290),
                        new LatLng(15.480923,120.960400),
                        new LatLng(15.444309,120.943309),
                        new LatLng(15.430717,120.938867),
                        new LatLng(15.425054,120.938355),
                        new LatLng(15.425216,120.929948),
                        new LatLng(15.427391,120.928230),
                        new LatLng(15.428662,120.927247),
                        new LatLng(15.429369,120.926017),
                        new LatLng(15.437068,120.867988),
                        new LatLng(15.444527,120.840805),
                        new LatLng(15.449239,120.799320),
                        new LatLng(15.449184,120.798343),
                        new LatLng(15.448720,120.796777),
                        new LatLng(15.448503,120.795710),
                        new LatLng(15.448820,120.794325),
                        new LatLng(15.449902,120.793132),
                        new LatLng(15.450830,120.792290),
                        new LatLng(15.451546,120.790165),
                        new LatLng(15.448717,120.788545),
                        new LatLng(15.447433,120.787437),
                        new LatLng(15.446496,120.784868),
                        new LatLng(15.442852,120.757871),
                        new LatLng(15.442910,120.756936),
                        new LatLng(15.444506,120.753784),
                        new LatLng(15.444761,120.752690),
                        new LatLng(15.443567,120.749337),
                        new LatLng(15.443589,120.747930),
                        new LatLng(15.442468,120.733556),
                        new LatLng(15.442977,120.729299),
                        new LatLng(15.445289,120.720609),
                        new LatLng(15.454783,120.704088),
                        new LatLng(15.455049,120.696396),
                        new LatLng(15.465101,120.684660),
                        new LatLng(15.467124,120.684151),
                        new LatLng(15.467519,120.683969),
                        new LatLng(15.472047,120.682994),
                        new LatLng(15.472008,120.681649),
                        new LatLng(15.475108,120.681393),
                        new LatLng(15.475687,120.681062),
                        new LatLng(15.475965,120.680314),
                        new LatLng(15.476195,120.679127),
                        new LatLng(15.476606,120.678551),
                        new LatLng(15.477026,120.672337),
                        new LatLng(15.477789,120.678191),
                        new LatLng(15.487394,120.678260),
                        new LatLng(15.481244,120.678213),
                        new LatLng(15.462625,120.675667),
                        new LatLng(15.431962,120.665210),
                        new LatLng(15.418043,120.660899),
                        new LatLng(15.404245,120.659161),
                        new LatLng(15.393198,120.659504),
                        new LatLng(15.381323,120.659762),
                        new LatLng(15.372840,120.658045),
                        new LatLng(15.250486,120.589412),
                        new LatLng(15.247640,120.586434),
                        new LatLng(15.241719,120.570856),
                        new LatLng(15.238717,120.568045),
                        new LatLng(15.234783,120.567272),
                        new LatLng(15.204999,120.575372),
                        new LatLng(15.200236,120.575887),
                        new LatLng(15.200107,120.575699),
                        new LatLng(15.199661,120.575227),
                        new LatLng(15.198957,120.574015),
                        new LatLng(15.197689,120.574117),
                        new LatLng(15.197368,120.575737),
                        new LatLng(15.197285,120.586852),
                        new LatLng(15.197223,120.589931),
                        new LatLng(15.197223,120.591637),
                        new LatLng(15.197483,120.594115),
                        new LatLng(15.197478,120.596384),
                        new LatLng(15.197167,120.596867),
                        new LatLng(15.194568,120.597715),
                        new LatLng(15.188128,120.600139),
                        new LatLng(15.187973,120.600118),
                        new LatLng(15.172729,120.607405),
                        new LatLng(15.171465,120.607813),
                        new LatLng(15.170016,120.608725),
                        new LatLng(15.169260,120.609433),
                        new LatLng(15.112827,120.658331),
                        new LatLng(15.074075,120.678319),
                        new LatLng(15.051869,120.692784),
                        new LatLng(15.040700,120.703599),
                        new LatLng(15.034841,120.710739),
                        new LatLng(15.011290,120.735613),
                        new LatLng(15.006212,120.739593),
                        new LatLng(14.999331,120.744357),
                        new LatLng(14.987682,120.751223),
                        new LatLng(14.968198,120.764441),
                        new LatLng(14.894694,120.828521),
                        new LatLng(14.875366,120.842897),
                        new LatLng(14.853741,120.872860),
                        new LatLng(14.844169,120.885187),
                        new LatLng(14.840975,120.889629),
                        new LatLng(14.835924,120.897772),
                        new LatLng(14.817276,120.932426),
                        new LatLng(14.816886,120.934121),
                        new LatLng(14.814144,120.935559),
                        new LatLng(14.803668,120.941610),
                        new LatLng(14.801842,120.942726),
                        new LatLng(14.801178,120.942962),
                        new LatLng(14.696501,120.999152),
                        new LatLng(14.695494,120.999613),
                        new LatLng(14.691914,121.000600),
                        new LatLng(14.691281,121.000697),
                        new LatLng(14.689953,121.000772),
                        new LatLng(14.681567,121.000546),
                        new LatLng(14.681084,121.000455),
                        new LatLng(14.678920,120.999870),
                        new LatLng(14.676824,121.000181),
                        new LatLng(14.675609,121.000428),
                        new LatLng(14.657151,121.000100),
                        new LatLng(14.656738,120.999963),
                        new LatLng(14.656565,120.999530),
                        new LatLng(14.656760,120.999294),
                        new LatLng(14.657064,120.999291),
                        new LatLng(14.657252,120.999418),
                        new LatLng(14.657568,121.020029),
                        new LatLng(14.657324,121.021901),
                        new LatLng(14.655524,121.029845),
                        new LatLng(14.655161,121.030274),
                        new LatLng(14.632748,121.044960),
                        new LatLng(14.632021,121.045287),
                        new LatLng(14.624076,121.048926),
                        new LatLng(14.623892,121.048961),
                        new LatLng(14.623443,121.049154),
                        new LatLng(14.621412,121.050069),
                        new LatLng(14.621539,121.050310),
                        new LatLng(14.623997,121.049197),
                        new LatLng(14.624120,121.049090),
                        new LatLng(14.624520,121.048923),
                        new LatLng(14.624645,121.049276))
                .width(5)
                .color(Color.BLUE));
    }

    public  void Cabanatuan(){
        LatLng ter2 = new LatLng(15.472546,120.968691);
        mMap.addMarker(new MarkerOptions().position(ter2).title("Cabanatuan City Terminal"));


        LatLng ter3 = new LatLng(14.624645,121.049276);
        mMap.addMarker(new MarkerOptions().position(ter3).title("Cubao Terminal"));

        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(15.472456,120.968493),
                        new LatLng(15.472046,120.968648),
                        new LatLng(15.471778,120.967532),
                        new LatLng(15.471850,120.967130),
                        new LatLng(15.470053,120.965925),
                        new LatLng(15.467590,120.962565),
                        new LatLng(15.467582,120.967682),
                        new LatLng(15.470348,120.955445),
                        new LatLng(15.444309,120.943309),
                        new LatLng(15.430717,120.938867),
                        new LatLng(15.425054,120.938355),
                        new LatLng(15.425216,120.929948),
                        new LatLng(15.427391,120.928230),
                        new LatLng(15.428662,120.927247),
                        new LatLng(15.429369,120.926017),
                        new LatLng(15.437068,120.867988),
                        new LatLng(15.444527,120.840805),
                        new LatLng(15.449239,120.799320),
                        new LatLng(15.449184,120.798343),
                        new LatLng(15.448720,120.796777),
                        new LatLng(15.448503,120.795710),
                        new LatLng(15.448820,120.794325),
                        new LatLng(15.449902,120.793132),
                        new LatLng(15.450830,120.792290),
                        new LatLng(15.451546,120.790165),
                        new LatLng(15.448717,120.788545),
                        new LatLng(15.447433,120.787437),
                        new LatLng(15.446496,120.784868),
                        new LatLng(15.442852,120.757871),
                        new LatLng(15.442910,120.756936),
                        new LatLng(15.444506,120.753784),
                        new LatLng(15.444761,120.752690),
                        new LatLng(15.443567,120.749337),
                        new LatLng(15.443589,120.747930),
                        new LatLng(15.442468,120.733556),
                        new LatLng(15.442977,120.729299),
                        new LatLng(15.445289,120.720609),
                        new LatLng(15.454783,120.704088),
                        new LatLng(15.455049,120.696396),
                        new LatLng(15.465101,120.684660),
                        new LatLng(15.467124,120.684151),
                        new LatLng(15.467519,120.683969),
                        new LatLng(15.472047,120.682994),
                        new LatLng(15.472008,120.681649),
                        new LatLng(15.475108,120.681393),
                        new LatLng(15.475687,120.681062),
                        new LatLng(15.475965,120.680314),
                        new LatLng(15.476195,120.679127),
                        new LatLng(15.476606,120.678551),
                        new LatLng(15.477026,120.672337),
                        new LatLng(15.477789,120.678191),
                        new LatLng(15.487394,120.678260),
                        new LatLng(15.481244,120.678213),
                        new LatLng(15.462625,120.675667),
                        new LatLng(15.431962,120.665210),
                        new LatLng(15.418043,120.660899),
                        new LatLng(15.404245,120.659161),
                        new LatLng(15.393198,120.659504),
                        new LatLng(15.381323,120.659762),
                        new LatLng(15.372840,120.658045),
                        new LatLng(15.250486,120.589412),
                        new LatLng(15.247640,120.586434),
                        new LatLng(15.241719,120.570856),
                        new LatLng(15.238717,120.568045),
                        new LatLng(15.234783,120.567272),
                        new LatLng(15.204999,120.575372),
                        new LatLng(15.200236,120.575887),
                        new LatLng(15.200107,120.575699),
                        new LatLng(15.199661,120.575227),
                        new LatLng(15.198957,120.574015),
                        new LatLng(15.197689,120.574117),
                        new LatLng(15.197368,120.575737),
                        new LatLng(15.197285,120.586852),
                        new LatLng(15.197223,120.589931),
                        new LatLng(15.197223,120.591637),
                        new LatLng(15.197483,120.594115),
                        new LatLng(15.197478,120.596384),
                        new LatLng(15.197167,120.596867),
                        new LatLng(15.194568,120.597715),
                        new LatLng(15.188128,120.600139),
                        new LatLng(15.187973,120.600118),
                        new LatLng(15.172729,120.607405),
                        new LatLng(15.171465,120.607813),
                        new LatLng(15.170016,120.608725),
                        new LatLng(15.169260,120.609433),
                        new LatLng(15.112827,120.658331),
                        new LatLng(15.074075,120.678319),
                        new LatLng(15.051869,120.692784),
                        new LatLng(15.040700,120.703599),
                        new LatLng(15.034841,120.710739),
                        new LatLng(15.011290,120.735613),
                        new LatLng(15.006212,120.739593),
                        new LatLng(14.999331,120.744357),
                        new LatLng(14.987682,120.751223),
                        new LatLng(14.968198,120.764441),
                        new LatLng(14.894694,120.828521),
                        new LatLng(14.875366,120.842897),
                        new LatLng(14.853741,120.872860),
                        new LatLng(14.844169,120.885187),
                        new LatLng(14.840975,120.889629),
                        new LatLng(14.835924,120.897772),
                        new LatLng(14.817276,120.932426),
                        new LatLng(14.816886,120.934121),
                        new LatLng(14.814144,120.935559),
                        new LatLng(14.803668,120.941610),
                        new LatLng(14.801842,120.942726),
                        new LatLng(14.801178,120.942962),
                        new LatLng(14.696501,120.999152),
                        new LatLng(14.695494,120.999613),
                        new LatLng(14.691914,121.000600),
                        new LatLng(14.691281,121.000697),
                        new LatLng(14.689953,121.000772),
                        new LatLng(14.681567,121.000546),
                        new LatLng(14.681084,121.000455),
                        new LatLng(14.678920,120.999870),
                        new LatLng(14.676824,121.000181),
                        new LatLng(14.675609,121.000428),
                        new LatLng(14.657151,121.000100),
                        new LatLng(14.656738,120.999963),
                        new LatLng(14.656565,120.999530),
                        new LatLng(14.656760,120.999294),
                        new LatLng(14.657064,120.999291),
                        new LatLng(14.657252,120.999418),
                        new LatLng(14.657568,121.020029),
                        new LatLng(14.657324,121.021901),
                        new LatLng(14.655524,121.029845),
                        new LatLng(14.655161,121.030274),
                        new LatLng(14.632748,121.044960),
                        new LatLng(14.632021,121.045287),
                        new LatLng(14.624076,121.048926),
                        new LatLng(14.623892,121.048961),
                        new LatLng(14.623443,121.049154),
                        new LatLng(14.621412,121.050069),
                        new LatLng(14.621539,121.050310),
                        new LatLng(14.623997,121.049197),
                        new LatLng(14.624120,121.049090),
                        new LatLng(14.624520,121.048923),
                        new LatLng(14.624645,121.049276))
                .width(5)
                .color(Color.BLUE));

    }
    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(Tracking.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(Tracking.this).create();
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
    
}
