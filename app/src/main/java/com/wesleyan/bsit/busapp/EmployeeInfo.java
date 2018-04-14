package com.wesleyan.bsit.busapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeInfo extends AppCompatActivity {
        ImageView ivPic;
        TextView name,age,gender,address,contact,position,birthday;
        String imgPath;
        Bundle nameEmp;
        String namesE;
    ProgressDialog dialog12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);


        dialog12 = new ProgressDialog(EmployeeInfo.this);
        ivPic = (ImageView)findViewById(R.id.ivPicture);
        name = (TextView)findViewById(R.id.tvName);
        age = (TextView)findViewById(R.id.tvAge);
        gender = (TextView)findViewById(R.id.tvGender);
        address = (TextView)findViewById(R.id.tvAdd);
        contact = (TextView)findViewById(R.id.tvContact);
        position = (TextView)findViewById(R.id.tvPos);
        birthday = (TextView)findViewById(R.id.tvBirthday) ;
        HashMap postData = new HashMap();

        nameEmp = getIntent().getExtras();
        namesE = nameEmp.getString("name");
        postData.put("ID",namesE);



        String url = "https://jeraldjoemagno11.000webhostapp.com/app/load_employee.php";

        StringRequest showloc = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog12.dismiss();
                if (response.equals("No Results Found.")) {
                    Timeout();
                } else {
                    ArrayList<POST_Personel> empinfo = new JsonConverter<POST_Personel>().toArrayList(response, POST_Personel.class);
                    for (POST_Personel value : empinfo) {

                        name.setText(value.lname+", "+value.fname +" "+ value.mname);
                        age.setText(value.age);
                        gender.setText(value.gender);
                        address.setText(value.address);
                        contact.setText(value.contact_no);
                        position.setText(value.position);
                        birthday.setText(value.bday);
                        imgPath = value.image_path;
                        Picasso.with(EmployeeInfo.this)
                                .load(imgPath)
                                .resize(200, 200)
                                .centerCrop()
                                .into(ivPic);
                    }
                    getSupportActionBar().setTitle(name.getText().toString());
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
                postData.put("ID",namesE);
                return postData;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(showloc);
        dialog12.setTitle("Loading Personnel Information");
        dialog12.setCancelable(false);
        dialog12.setMessage("Please Wait");
        dialog12.show();

    }


    private void offlineMODE(){
        final AlertDialog alertDialog = new AlertDialog.Builder(EmployeeInfo.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(EmployeeInfo.this).create();
        alertDialog.setTitle("Connection Timeout");
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
}
