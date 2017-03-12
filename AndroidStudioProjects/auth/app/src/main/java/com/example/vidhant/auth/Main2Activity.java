package com.example.vidhant.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vidhant.auth.extras.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextId;
    private Button buttonGet;
    Button btnauto;
    private TextView textViewResult;
    public static String Name;
    public static String Price;
    public static String id;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextId = (EditText) findViewById(R.id.editTextId1);
        buttonGet = (Button) findViewById(R.id.buttonGet1);
        textViewResult = (TextView) findViewById(R.id.textviewresult);

        buttonGet.setOnClickListener(this);

        Intent i = getIntent();
        id = i.getStringExtra("TextBox");

        editTextId.setText(id);
        buttonGet.performClick();
    }

    private void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        String url = Config.DATA_URL + editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Main2Activity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject Data = result.getJSONObject(0);
            Name = Data.getString(Config.KEY_VALUE);
            Price = Data.getString(Config.KEY_COFFEE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewResult.setText("Name:\t" + Name + "\nPrice:\t" + Price);


    }


    @Override
    public void onClick(View v) {
        if (v == buttonGet)
            getData();
                buttonclick();
    }



    private void buttonclick() {
        btnauto = (Button) findViewById(R.id.btnauto1);
        btnauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(Main2Activity.this, Addcart.class);
                startActivity(in1);
            }

        });

    }
}