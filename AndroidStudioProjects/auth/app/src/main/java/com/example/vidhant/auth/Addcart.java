package com.example.vidhant.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vidhant.auth.extras.Config;

import java.util.HashMap;
import java.util.Map;

public class Addcart extends AppCompatActivity implements  View.OnClickListener {
    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);
        btn1= (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn1.performClick();
        btn2=(Button)findViewById(R.id.btn2);
        btn2.performClick();


    }



    private void addinfo() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KEY_CARTURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Addcart.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Addcart.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.KEY_ID, Main2Activity.id);
                params.put(Config.KEY_NAME, Main2Activity.Name);
                params.put(Config.KEY_PRICE, Main2Activity.Price);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if (v == btn1) {
            addinfo();
        }
    }
}

