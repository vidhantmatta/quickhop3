package com.example.vidhant.auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vidhant.auth.extras.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Main2Activity extends AppCompatActivity {
    private TextView textviewresult;
    private ProgressDialog loading;
    scan object=new scan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textviewresult = (TextView) findViewById(R.id.textviewresult);
    }
    private void getData(){
        String id= object.temp;


        loading = ProgressDialog.show(this,"Please wait...","Fetching Data...",false,false);

        String url = Config.DATA_URL+id.trim();

        StringRequest stringRequest= new StringRequest(url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                loading.dismiss();
                showJSON(response);
            }

        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);}

        private void showJSON(String response){
            String Name="";
            String Price="";
            try{
                JSONObject JSONObject=new JSONObject(response);
                JSONArray result=JSONObject.getJSONArray(Config.JSON_ARRAY);
                JSONObject Data=result.getJSONObject(0);
                Name=Data.getString(Config.KEY_VALUE);
                Price=Data.getString(Config.KEY_COFFEE);
            } catch (JSONException e){
                e.printStackTrace();
            }
        textviewresult.setText("Name:\t"+ Name+ "\nPrice:\t"+ Price);
    }




}

