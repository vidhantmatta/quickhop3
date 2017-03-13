package com.example.vidhant.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scan extends AppCompatActivity {
    ImageButton scan;
    ImageButton cart;
    String temp;
    SharedPreferences sf;
    public static final String preference = "pref";
    public static final String saveit = "savekey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        buttonclick();
        scan = (ImageButton) findViewById(R.id.scan);
        sf = getSharedPreferences(preference, Context.MODE_PRIVATE);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(scan.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                String scanvalue = result.getContents();
                temp = scanvalue;
                SharedPreferences.Editor editor = sf.edit();
                editor.putString(saveit, temp);
                editor.commit();
                Intent i = new Intent(scan.this, Main2Activity.class);
                i.putExtra("TextBox", temp.toString());
                startActivity(i);


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void buttonclick() {
        cart = (ImageButton) findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(scan.this, cart.class);
                startActivity(in1);
            }


        });
    }
}