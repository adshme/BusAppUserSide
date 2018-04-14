package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.kosalgeek.android.json.JsonConverter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class qrcode extends AppCompatActivity implements Serializable {

    ImageView codeiv;
    TextView tvtitle,tvseat,txtday,txthour,txtmin,txtsec;
    public final static int QRcodeWidth = 250 ;
    Bitmap bitmap ;
    String code_generate,expiredate,title,RebookExpire,seat,retry,BNumber ;
    HashMap  postRes;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedpreferences;
    SimpleDateFormat dateFormat;
    Date currentDate;
    View mView3;
    AlertDialog.Builder mBuilder3;
    Button cancelrq;
    TextView titleDialog,warn;
    ListView qrcodes;
    ArrayList<POST_BusAvail> bss;
    ArrayList<String> bu;
    public static final String mypreference = "logprefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        codeiv = (ImageView)findViewById(R.id.ivcode);
        tvtitle = (TextView)findViewById(R.id.user);
        tvseat = (TextView)findViewById(R.id.tvseats);
        txtday = (TextView)findViewById(R.id.tvday);
        txthour = (TextView)findViewById(R.id.tvhour);
        txtmin = (TextView)findViewById(R.id.tvmin);
        txtsec = (TextView)findViewById(R.id.tvsec);
        postRes = new HashMap();
        mView3 = getLayoutInflater().inflate(R.layout.qrcodelist, null);
        mBuilder3 = new AlertDialog.Builder(qrcode.this);
        titleDialog = (TextView)mView3.findViewById(R.id.txttitlesDialog);
        cancelrq = (Button)mView3.findViewById(R.id.cancelbtn);
        qrcodes = (ListView)mView3.findViewById(R.id.listQR);
        warn = (TextView) mView3.findViewById(R.id.warning);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
        currentDate = new Date();
        sharedpreferences = getSharedPreferences(mypreference, MODE_PRIVATE);
        if (sharedpreferences.contains("userusername")) {

            POST_QR qrinfo = (POST_QR) getIntent().getSerializableExtra("qr");

            if(qrinfo != null) {
                tvtitle.setText("Bus " + qrinfo.busNumber);
                title = qrinfo.busNumber;
                tvseat.setText("Seat " + qrinfo.seatNumber);
                seat = qrinfo.seatNumber;
                code_generate = qrinfo.code;
                expiredate = qrinfo.expirationdate;
                RebookExpire = qrinfo.rebookexpiredate;
            }


                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(this, 1000);
                        try {

                            Date cd = new Date();
                            Date futureDate = dateFormat.parse(expiredate);
                            if (!cd.after(futureDate)) {
                                long diff = futureDate.getTime()
                                        - cd.getTime();
                                long days = diff / (24 * 60 * 60 * 1000);
                                diff -= days * (24 * 60 * 60 * 1000);
                                long hours = diff / (60 * 60 * 1000);
                                diff -= hours * (60 * 60 * 1000);
                                long minutes = diff / (60 * 1000);
                                diff -= minutes * (60 * 1000);
                                long seconds = diff / 1000;
                                txtday.setText("" + String.format("%02d", days));
                                txthour.setText("" + String.format("%02d", hours));
                                txtmin.setText(""
                                        + String.format("%02d", minutes));
                                txtsec.setText(""
                                        + String.format("%02d", seconds));
                            } else {
                                handler.removeCallbacks(runnable);

                                String url = "https://jeraldjoemagno11.000webhostapp.com/app/expire_qr.php";

                                StringRequest expireQR = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("Success")) {
                                            expired();
                                            Toast.makeText(qrcode.this, "Expired", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(qrcode.this, "Error", Toast.LENGTH_SHORT).show();
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
                                        postData.put("code",code_generate);
                                        postData.put("busid",title);
                                        postData.put("seat",seat);
                                        return postData;
                                    }
                                };
                                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(expireQR);


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                handler.postDelayed(runnable, 1 * 1000);


            try {
                bitmap = TextToImageEncode(code_generate);
                codeiv.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }




    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.common_google_signin_btn_text_dark_focused):getResources().getColor(R.color.common_google_signin_btn_text_dark);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 250, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);

    }
    private void expired(){
        final AlertDialog alertDialog = new AlertDialog.Builder(qrcode.this).create();
        alertDialog.setTitle("Expired");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("The QR Code is Already Expired");
        alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
                Intent ref = new Intent(qrcode.this, Seat_Reserve.class);
                ref.putExtra("title", title);
                startActivity(ref);
                finish();

            }
        });
        alertDialog.show();
    }

    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(qrcode.this).create();
        alertDialog.setTitle("Offline");
        alertDialog.setCancelable(false);
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
        final AlertDialog alertDialog = new AlertDialog.Builder(qrcode.this).create();
        alertDialog.setTitle("Connection Timeout");
        alertDialog.setCancelable(false);
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rebookmenu, menu);


        String url = "https://jeraldjoemagno11.000webhostapp.com/app/retries.php";

        StringRequest Qrmenu = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Results Found.")){
                    Toast.makeText(qrcode.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<POST_Retries> trip = new JsonConverter<POST_Retries>().toArrayList(response, POST_Retries.class);
                    for (POST_Retries value : trip) {
                        retry = value.retries;
                    }
                    if (Integer.valueOf(retry)> 0) {
                        try {

                            Date fDate = dateFormat.parse(RebookExpire);
                            if (!currentDate.after(fDate)) {
                                menu.findItem(R.id.rebook).setVisible(true);
                            } else {
                                menu.findItem(R.id.rebook).setVisible(false);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        menu.findItem(R.id.rebook).setEnabled(false);
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
                postData.put("code",code_generate);
                postData.put("busid",title);
                postData.put("seat",seat);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(Qrmenu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.rebook:
                Toast.makeText(this,retry+ " left ", Toast.LENGTH_SHORT).show();
                busL();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void busL(){
        mBuilder3.setView(mView3);
        final AlertDialog dialog = mBuilder3.create();
        dialog.setCancelable(false);
        titleDialog.setText("Buses for Rebooking");

        String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_ApBuses.php";

        StringRequest AvailforRebook = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("No Result Found.")) {
                    warn.setVisibility(View.VISIBLE);
                } else {
                    bss = new JsonConverter<POST_BusAvail>().toArrayList(response, POST_BusAvail.class);
                    bu = new ArrayList<String>();
                    for (POST_BusAvail value : bss) {
                        bu.add(value.busNumber);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(qrcode.this, android.R.layout.simple_list_item_1, bu);
                    qrcodes.setAdapter(adapter);
                    qrcodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            POST_BusAvail avail = bss.get(i);
                            Intent next = new Intent(qrcode.this, RebookSeats.class);
                            next.putExtra("busAvail", (Serializable) avail);
                            next.putExtra("code",code_generate);
                            next.putExtra("rebus",title);
                            next.putExtra("reseat",seat);
                            startActivity(next);
                            finish();
                        }
                    });

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError){
                    Timeout();
                    warn.setVisibility(View.VISIBLE);
                }else if (error instanceof NoConnectionError){
                    offlineMODE();
                    warn.setVisibility(View.VISIBLE);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> postData = new HashMap<>();
                postData.put("code",code_generate);
                postData.put("busid",title);
                postData.put("seat",seat);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(AvailforRebook);


        cancelrq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                Intent ref = new Intent(qrcode.this,Seat_Reserve.class);
                ref.putExtra("title",title);
                startActivity(ref);
                finish();

            }
        });
        dialog.show();
    }

}
