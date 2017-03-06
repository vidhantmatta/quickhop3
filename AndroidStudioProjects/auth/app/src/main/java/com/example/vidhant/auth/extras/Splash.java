package com.example.vidhant.auth.extras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vidhant.auth.Intro;
import com.example.vidhant.auth.SessionManagement;
import com.example.vidhant.auth.MainActivity;


public class Splash extends AppCompatActivity {


    private SessionManagement session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        session = new SessionManagement(getApplicationContext());

        redirect();

    }



    private void redirect() {

        if (session.introDone()) {

            Intent go = new Intent(this,MainActivity.class);

            finish();

            startActivity(go);

        }

        else {

            Intent go = new Intent(this, Intro.class);

            finish();

            startActivity(go);

        }

    }


}
