package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeatInfor extends AppCompatActivity {

    TextView title;

    Button s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,
            s11,s12,s13,s14,s15,s16,s17,s18,s19,s20,
            s21,s22,s23,s24,s25,s26,s27,s28,s29,s30,
            s31,s32,s33,s34,s35,s36,s37,s38,s39,s40,s41;
    String se1,se2,se3,se4,se5,se6,se7,se8,se9,se10,
            se11,se12,se13,se14,se15,se16,se17,se18,se19,se20,
            se21,se22,se23,se24,se25,se26,se27,se28,se29,se30,
            se31,se32,se33,se34,se35,se36,se37,se38,se39,se40,se41;

    String gettheBus;

    Bundle nameEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_infor);


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


        if (getIntent().getExtras() != null){

            nameEmp = getIntent().getExtras();
            gettheBus = nameEmp.getString("title");
            title.setText("" + gettheBus);
            getSupportActionBar().setTitle("Bus " + gettheBus);
        }else{
            error();
        }



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
                    s1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #1 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s1.setTextColor(Color.WHITE);
                    s1.setBackgroundColor(Color.BLUE);
                }else if(se1.equals("Reserved")){
                    s1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #1 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s1.setTextColor(Color.WHITE);
                    s1.setBackgroundColor(Color.RED);
                }else{
                    s1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #1 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se2.equals("Occupied")){
                    s2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #2 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s2.setTextColor(Color.WHITE);
                    s2.setBackgroundColor(Color.BLUE);
                }else if(se2.equals("Reserved")){
                    s2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #2 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s2.setTextColor(Color.WHITE);
                    s2.setBackgroundColor(Color.RED);
                }else{
                    s2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #2 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se3.equals("Occupied")){
                    s3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #3 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s3.setTextColor(Color.WHITE);
                    s3.setBackgroundColor(Color.BLUE);
                }else if(se3.equals("Reserved")){
                    s3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #3 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s3.setTextColor(Color.WHITE);
                    s3.setBackgroundColor(Color.RED);
                }else{
                    s3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #3 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se4.equals("Occupied")){
                    s4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #4 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s4.setTextColor(Color.WHITE);
                    s4.setBackgroundColor(Color.BLUE);
                }else if(se4.equals("Reserved")){
                    s4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #4 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s4.setTextColor(Color.WHITE);
                    s4.setBackgroundColor(Color.RED);
                }else{
                    s4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #4 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se5.equals("Occupied")){
                    s5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #5 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s5.setTextColor(Color.WHITE);
                    s5.setBackgroundColor(Color.BLUE);
                }else if(se5.equals("Reserved")){
                    s5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #5 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s5.setTextColor(Color.WHITE);
                    s5.setBackgroundColor(Color.RED);
                }else{
                    s5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #5 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se6.equals("Occupied")){
                    s6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #6 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s6.setTextColor(Color.WHITE);
                    s6.setBackgroundColor(Color.BLUE);
                }else if(se6.equals("Reserved")){
                    s6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #6 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s6.setTextColor(Color.WHITE);
                    s6.setBackgroundColor(Color.RED);
                }else{
                    s6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #6 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se7.equals("Occupied")){
                    s7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #7 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s7.setTextColor(Color.WHITE);
                    s7.setBackgroundColor(Color.BLUE);
                }else if(se7.equals("Reserved")){
                    s7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #7 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s7.setTextColor(Color.WHITE);
                    s7.setBackgroundColor(Color.RED);
                }else{
                    s7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #7 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se8.equals("Occupied")){
                    s8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #8 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s8.setTextColor(Color.WHITE);
                    s8.setBackgroundColor(Color.BLUE);
                }else if(se8.equals("Reserved")){
                    s8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #8 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s8.setTextColor(Color.WHITE);
                    s8.setBackgroundColor(Color.RED);
                }else{
                    s8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #8 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se9.equals("Occupied")){
                    s9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #9 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s9.setTextColor(Color.WHITE);
                    s9.setBackgroundColor(Color.BLUE);
                }else if(se9.equals("Reserved")){
                    s9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #9 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s9.setTextColor(Color.WHITE);
                    s9.setBackgroundColor(Color.RED);
                }else{
                    s9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #9 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se10.equals("Occupied")){
                    s10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #10 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s10.setTextColor(Color.WHITE);
                    s10.setBackgroundColor(Color.BLUE);
                }else if(se10.equals("Reserved")){
                    s10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #10 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s10.setTextColor(Color.WHITE);
                    s10.setBackgroundColor(Color.RED);
                }else{
                    s10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #10 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se11.equals("Occupied")){
                    s11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #11 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s11.setTextColor(Color.WHITE);
                    s11.setBackgroundColor(Color.BLUE);
                }else if(se11.equals("Reserved")){
                    s11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #11 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s11.setTextColor(Color.WHITE);
                    s11.setBackgroundColor(Color.RED);
                }else{
                    s11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #11 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se12.equals("Occupied")){
                    s12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #12 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s12.setTextColor(Color.WHITE);
                    s12.setBackgroundColor(Color.BLUE);
                }else if(se12.equals("Reserved")){
                    s12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #12 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s12.setTextColor(Color.WHITE);
                    s12.setBackgroundColor(Color.RED);
                }else{
                    s12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #12 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se13.equals("Occupied")){
                    s13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #13 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s13.setTextColor(Color.WHITE);
                    s13.setBackgroundColor(Color.BLUE);
                }else if(se13.equals("Reserved")){
                    s13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #13 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s13.setTextColor(Color.WHITE);
                    s13.setBackgroundColor(Color.RED);
                }else{
                    s13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #13 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se14.equals("Occupied")){
                    s14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #14 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s14.setTextColor(Color.WHITE);
                    s14.setBackgroundColor(Color.BLUE);
                }else if(se14.equals("Reserved")){
                    s14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #14 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s14.setTextColor(Color.WHITE);
                    s14.setBackgroundColor(Color.RED);
                }else{
                    s14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #14 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se15.equals("Occupied")){
                    s15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #15 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s15.setTextColor(Color.WHITE);
                    s15.setBackgroundColor(Color.BLUE);
                }else if(se15.equals("Reserved")){
                    s15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #15 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s15.setTextColor(Color.WHITE);
                    s15.setBackgroundColor(Color.RED);
                }else{
                    s15.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #15 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se16.equals("Occupied")){
                    s16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #16 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s16.setTextColor(Color.WHITE);
                    s16.setBackgroundColor(Color.BLUE);
                }else if(se16.equals("Reserved")){
                    s16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #16 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s16.setTextColor(Color.WHITE);
                    s16.setBackgroundColor(Color.RED);
                }else{
                    s16.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #16 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se17.equals("Occupied")){
                    s17.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #17 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s17.setTextColor(Color.WHITE);
                    s17.setBackgroundColor(Color.BLUE);
                }else if(se17.equals("Reserved")){
                    s17.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #17 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s17.setTextColor(Color.WHITE);
                    s17.setBackgroundColor(Color.RED);
                }else{
                    s17.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #17 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se18.equals("Occupied")){
                    s18.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #18 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s18.setTextColor(Color.WHITE);
                    s18.setBackgroundColor(Color.BLUE);
                }else if(se18.equals("Reserved")){
                    s18.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #18 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s18.setTextColor(Color.WHITE);
                    s18.setBackgroundColor(Color.RED);
                }else{
                    s18.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #18 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se19.equals("Occupied")){
                    s19.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #19 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s19.setTextColor(Color.WHITE);
                    s19.setBackgroundColor(Color.BLUE);
                }else if(se19.equals("Reserved")){
                    s19.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #19 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s19.setTextColor(Color.WHITE);
                    s19.setBackgroundColor(Color.RED);
                }else{
                    s19.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #19 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se20.equals("Occupied")){
                    s20.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #20 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s20.setTextColor(Color.WHITE);
                    s20.setBackgroundColor(Color.BLUE);
                }else if(se20.equals("Reserved")){
                    s20.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #20 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s20.setTextColor(Color.WHITE);
                    s20.setBackgroundColor(Color.RED);
                }else{
                    s20.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #20 Available", Toast.LENGTH_SHORT).show();
                        }
                    });
                }



                if(se21.equals("Occupied")){
                    s21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #21 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s21.setTextColor(Color.WHITE);
                    s21.setBackgroundColor(Color.BLUE);
                }else if(se21.equals("Reserved")){
                    s21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #21 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s21.setTextColor(Color.WHITE);
                    s21.setBackgroundColor(Color.RED);
                }else{
                    s21.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #21 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se22.equals("Occupied")){
                    s22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #22 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s22.setTextColor(Color.WHITE);
                    s22.setBackgroundColor(Color.BLUE);
                }else if(se22.equals("Reserved")){
                    s22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #22 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s22.setTextColor(Color.WHITE);
                    s22.setBackgroundColor(Color.RED);
                }else{
                    s22.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #22 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se23.equals("Occupied")){
                    s23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #23 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s23.setTextColor(Color.WHITE);
                    s23.setBackgroundColor(Color.BLUE);
                }else if(se23.equals("Reserved")){
                    s23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #23 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s23.setTextColor(Color.WHITE);
                    s23.setBackgroundColor(Color.RED);
                }else{
                    s23.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #23 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se24.equals("Occupied")){
                    s24.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #24 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s24.setTextColor(Color.WHITE);
                    s24.setBackgroundColor(Color.BLUE);
                }else if(se24.equals("Reserved")){
                    s24.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #24 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s24.setTextColor(Color.WHITE);
                    s24.setBackgroundColor(Color.RED);
                }else{
                    s24.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #24 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }


                if(se25.equals("Occupied")){
                    s25.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #25 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s25.setTextColor(Color.WHITE);
                    s25.setBackgroundColor(Color.BLUE);
                }else if(se25.equals("Reserved")){
                    s25.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #25 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s25.setTextColor(Color.WHITE);
                    s25.setBackgroundColor(Color.RED);
                }else{
                    s25.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #25 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se26.equals("Occupied")){
                    s26.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #26 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s26.setTextColor(Color.WHITE);
                    s26.setBackgroundColor(Color.BLUE);
                }else if(se26.equals("Reserved")){
                    s26.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #26 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s26.setTextColor(Color.WHITE);
                    s26.setBackgroundColor(Color.RED);
                }else{
                    s26.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #26 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se27.equals("Occupied")){
                    s27.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #27 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s27.setTextColor(Color.WHITE);
                    s27.setBackgroundColor(Color.BLUE);
                }else if(se27.equals("Reserved")){
                    s27.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #27 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s27.setTextColor(Color.WHITE);
                    s27.setBackgroundColor(Color.RED);
                }else{
                    s27.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #27 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se28.equals("Occupied")){
                    s28.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #28 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s28.setTextColor(Color.WHITE);
                    s28.setBackgroundColor(Color.BLUE);
                }else if(se28.equals("Reserved")){
                    s28.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #28 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s28.setTextColor(Color.WHITE);
                    s28.setBackgroundColor(Color.RED);
                }else{
                    s28.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #28 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se29.equals("Occupied")){
                    s29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #29 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s29.setTextColor(Color.WHITE);
                    s29.setBackgroundColor(Color.BLUE);
                }else if(se29.equals("Reserved")){
                    s29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #29 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s29.setTextColor(Color.WHITE);
                    s29.setBackgroundColor(Color.RED);
                }else{
                    s29.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #29 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se30.equals("Occupied")){
                    s30.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #30 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s30.setTextColor(Color.WHITE);
                    s30.setBackgroundColor(Color.BLUE);
                }else if(se30.equals("Reserved")){
                    s30.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #30 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s30.setTextColor(Color.WHITE);
                    s30.setBackgroundColor(Color.RED);
                }else{
                    s30.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #30 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se31.equals("Occupied")){
                    s31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #31 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s31.setTextColor(Color.WHITE);
                    s31.setBackgroundColor(Color.BLUE);
                }else if(se31.equals("Reserved")){
                    s31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #31 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s31.setTextColor(Color.WHITE);
                    s31.setBackgroundColor(Color.RED);
                }else{
                    s31.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #31 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se32.equals("Occupied")){
                    s32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #32 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });

                    s32.setTextColor(Color.WHITE);
                    s32.setBackgroundColor(Color.BLUE);
                }else if(se32.equals("Reserved")){
                    s32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #32 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s32.setTextColor(Color.WHITE);
                    s32.setBackgroundColor(Color.RED);
                }else{
                    s32.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #32 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se33.equals("Occupied")){
                    s33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #33 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });                    s33.setTextColor(Color.WHITE);
                    s33.setBackgroundColor(Color.BLUE);
                }else if(se33.equals("Reserved")){
                    s33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #33 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s33.setTextColor(Color.WHITE);
                    s33.setBackgroundColor(Color.RED);
                }else{
                    s33.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #33 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se34.equals("Occupied")){
                    s34.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #34 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s34.setTextColor(Color.WHITE);
                    s34.setBackgroundColor(Color.BLUE);
                }else if(se34.equals("Reserved")){
                    s34.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #34 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s34.setTextColor(Color.WHITE);
                    s34.setBackgroundColor(Color.RED);
                }else{
                    s34.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #34 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se35.equals("Occupied")){
                    s35.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #35 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s35.setTextColor(Color.WHITE);
                    s35.setBackgroundColor(Color.BLUE);
                }else if(se35.equals("Reserved")){
                    s35.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #35 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s35.setTextColor(Color.WHITE);
                    s35.setBackgroundColor(Color.RED);
                }else{
                    s35.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #35 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se36.equals("Occupied")){
                    s36.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #36 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s36.setTextColor(Color.WHITE);
                    s36.setBackgroundColor(Color.BLUE);
                }else if(se36.equals("Reserved")){
                    s36.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #36 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s36.setTextColor(Color.WHITE);
                    s36.setBackgroundColor(Color.RED);
                }else{
                    s36.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #36 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se37.equals("Occupied")){
                    s37.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #37 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s37.setTextColor(Color.WHITE);
                    s37.setBackgroundColor(Color.BLUE);
                }else if(se37.equals("Reserved")){
                    s37.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #37 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s37.setTextColor(Color.WHITE);
                    s37.setBackgroundColor(Color.RED);
                }else{
                    s37.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #37 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se38.equals("Occupied")){
                    s38.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #38 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s38.setTextColor(Color.WHITE);
                    s38.setBackgroundColor(Color.BLUE);
                }else if(se38.equals("Reserved")){
                    s38.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #38 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s38.setTextColor(Color.WHITE);
                    s38.setBackgroundColor(Color.RED);
                }else{
                    s38.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #38 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se39.equals("Occupied")){
                    s39.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #39 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s39.setTextColor(Color.WHITE);
                    s39.setBackgroundColor(Color.BLUE);
                }else if(se39.equals("Reserved")){
                    s39.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #39 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });
                    s39.setTextColor(Color.WHITE);
                    s39.setBackgroundColor(Color.RED);
                }else{
                    s39.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #39 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }



                if(se40.equals("Occupied")){
                    s40.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #40 Occupied", Toast.LENGTH_SHORT).show();

                        }
                    });                    s40.setTextColor(Color.WHITE);
                    s40.setBackgroundColor(Color.BLUE);
                }else if(se40.equals("Reserved")){
                    s40.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #40 Reserved", Toast.LENGTH_SHORT).show();

                        }
                    });                    s40.setTextColor(Color.WHITE);
                    s40.setBackgroundColor(Color.RED);
                }else{
                    s40.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #40 Available", Toast.LENGTH_SHORT).show();

                        }
                    });
                }


                if(se41.equals("Occupied")){
                    s41.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(SeatInfor.this, "Seat #41 Occupied", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s41.setTextColor(Color.WHITE);
                    s41.setBackgroundColor(Color.BLUE);
                }else if(se41.equals("Reserved")){
                    s41.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(SeatInfor.this, "Seat #41 Reserved", Toast.LENGTH_SHORT).show();
                        }
                    });
                    s41.setTextColor(Color.WHITE);
                    s41.setBackgroundColor(Color.RED);
                }else{
                    s41.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(SeatInfor.this, "Seat #41 Available", Toast.LENGTH_SHORT).show();

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

        }


    private void Timeout(){
        final AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
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

    private void error(){
        final AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
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
                Intent ref = new Intent(SeatInfor.this, SeatInfor.class);
                ref.putExtra("title",title.getText().toString());
                startActivity(ref);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
