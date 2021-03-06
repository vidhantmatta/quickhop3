package com.example.vidhant.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vidhant.auth.extras.Config;

public class cart extends AppCompatActivity implements View.OnClickListener{

    private Button buttonget;
    private ListView listView;
    double p;
    String s;
    Button checkout;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        buttonclick1();
        buttonget = (Button) findViewById(R.id.buttonGet);
        buttonget.setOnClickListener(this);
        buttonget.performClick();
        listView = (ListView) findViewById(R.id.listView);
    }


    private void sendRequest(){
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        StringRequest stringRequest = new StringRequest(Config.KEY_RURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cart.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json){

        parsejson  pj= new parsejson( json);
        pj.parsejson();
        TextView textView= (TextView)findViewById(R.id.textView);
        p=pj.price;
        s=Double.toString(p);
        textView.setText(s);
        customlistview cl = new customlistview(this, parsejson.ids,parsejson.Names,parsejson.Prices);
        listView.setAdapter(cl);
    }



    @Override
    public void onClick(View v) {
        sendRequest();
    }



    private void buttonclick1()
    {
        checkout = (Button) findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in2 = new Intent(cart.this,payment.class);
                in2.putExtra("amount",s);
                startActivity(in2);
            }


        });
    }
}
