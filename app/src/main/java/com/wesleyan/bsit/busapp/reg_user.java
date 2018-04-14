package com.wesleyan.bsit.busapp;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class reg_user extends AppCompatActivity {

    EditText username,password,cpassword,fullname,age,birthday,address,contact;
    Button signup;
    RadioGroup group;
    Calendar myCalendar;
    SimpleDateFormat sdf;
    String getGender;
    Bundle nameEmp;
    String namesE ;
    ProgressDialog dialog12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user);

        dialog12 = new ProgressDialog(reg_user.this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        cpassword = (EditText) findViewById(R.id.CPassword);
        fullname = (EditText) findViewById(R.id.Fname);
        age = (EditText) findViewById(R.id.age);
        birthday = (EditText) findViewById(R.id.bday);
        address = (EditText) findViewById(R.id.address);
        contact = (EditText) findViewById(R.id.contact);
        signup = (Button) findViewById(R.id.sign);
        myCalendar = Calendar.getInstance();
        group = (RadioGroup)findViewById(R.id.Gender);
        getGender = ((RadioButton)this.findViewById(group.getCheckedRadioButtonId())).getText().toString();

        nameEmp = getIntent().getExtras();
        namesE = nameEmp.getString("title");

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (username.getText().toString().equals("")){
                    username.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your username</font>"));
                }else if (username.length()<8){
                    username.setError(Html.fromHtml("<font color='#ffffff'>Username must be 8 characters</font>"));
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (password.getText().toString().equals("")) {
                    password.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your Password</font>"));
                }else if (password.length()<8){
                    password.setError(Html.fromHtml("<font color='#ffffff'>Password must be 8 characters</font>"));
                }
            }
        });
        cpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!cpassword.getText().toString().equals(password.getText().toString())){
                    cpassword.setError(Html.fromHtml("<font color='#ffffff'>Password not match</font>"));
                }
            }
        });
        fullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (fullname.getText().toString().equals("")){
                    fullname.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your Fullname</font>"));
                }
            }
        });
        age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (age.getText().toString().equals("")){
                    age.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your Age</font>"));
                }
            }
        });
        birthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (birthday.getText().toString().equals("")){
                    birthday.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your Birthday</font>"));
                }
            }
        });
        address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(address.getText().toString().equals("")){
                    address.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your Address</font>"));
                }
            }
        });
        contact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (contact.getText().toString().equals("")){
                    contact.setError(Html.fromHtml("<font color='#ffffff'>Please Enter your Contact</font>"));
                }
            }
        });

       final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.UK);

                birthday.setText(sdf.format(myCalendar.getTime()));
                birthday.setError(null);
            }

        };
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(reg_user.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("")
                        || password.getText().toString().equals("")
                        || cpassword.getText().toString().equals("")
                        || fullname.getText().toString().equals("")
                        || age.getText().toString().equals("")
                        || birthday.getText().toString().equals("")
                        || address.getText().toString().equals("")
                        || contact.getText().toString().equals("")){
                    INC();
                }else {
                    if (username.length()<8){
                        UsernameINC();
                    }else {
                        if (password.length()<8){
                            PasswordINC();
                        }else {
                            if (password.getText().toString().equals(cpassword.getText().toString())) {

                                String url = "https://jeraldjoemagno11.000webhostapp.com/app/insert_user.php";

                                StringRequest insertuser = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        dialog12.dismiss();
                                        if (response.equals("Try Again")) {
                                            Toast.makeText(getApplicationContext(), "Please Try Again Later", Toast.LENGTH_LONG).show();
                                        } else {

                                            username.setText("");
                                            password.setText("");
                                            cpassword.setText("");
                                            fullname.setText("");
                                            age.setText("");
                                            birthday.setText("");
                                            address.setText("");
                                            contact.setText("");
                                            group.clearCheck();
                                            Toast.makeText(getApplicationContext(), "Registered!", Toast.LENGTH_LONG).show();
                                            Intent back = new Intent(reg_user.this, Seat_Reserve.class);
                                            back.putExtra("title", namesE);
                                            startActivity(back);
                                            finish();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        dialog12.dismiss();
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
                                        postData.put("username", username.getText().toString());
                                        postData.put("password", password.getText().toString());
                                        postData.put("fullname", fullname.getText().toString());
                                        postData.put("age", age.getText().toString());
                                        postData.put("Gender", getGender);
                                        postData.put("birthday", birthday.getText().toString());
                                        postData.put("address", address.getText().toString());
                                        postData.put("contact", contact.getText().toString());
                                        return postData;
                                    }
                                };
                                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(insertuser);
                                dialog12.setTitle("Registring New User");
                                dialog12.setCancelable(false);
                                dialog12.setMessage("Please Wait");
                                dialog12.show();
                            } else {
                                PassCheck();
                            }
                        }
                    }
                }




            }
        });
    }




    private void INC(){
        final AlertDialog alertDialog = new AlertDialog.Builder(reg_user.this).create();
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
    private void PassCheck(){
        final AlertDialog alertDialog = new AlertDialog.Builder(reg_user.this).create();
        alertDialog.setTitle("Not Match");
        alertDialog.setMessage("Password does not Match");
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
        final AlertDialog alertDialog = new AlertDialog.Builder(reg_user.this).create();
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
        final AlertDialog alertDialog = new AlertDialog.Builder(reg_user.this).create();
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
    private void UsernameINC(){
        final AlertDialog alertDialog = new AlertDialog.Builder(reg_user.this).create();
        alertDialog.setTitle("Incomplete");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Your Username must compose of 8 characters");
        alertDialog.setIcon(R.drawable.ic_perm_scan_wifi_black_24dp);
        alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }
    private void PasswordINC(){
        final AlertDialog alertDialog = new AlertDialog.Builder(reg_user.this).create();
        alertDialog.setTitle("Incomplete");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Your Password must compose of 8 characters");
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
