package com.example.vidhant.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.vidhant.auth.extras.SessionManagement2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {



    private EditText emailEditText;
    private EditText passEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = (EditText) findViewById(R.id.username);
        passEditText = (EditText) findViewById(R.id.password);

    }

    public void checkLogin(View arg0) {


        final String email = emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            emailEditText.setError("Invalid Email");
        }

        final String pass = passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            passEditText.setError("Too short");
        }

        if (isValidEmail(email) && isValidPassword(pass)) {
            // Validation Completed
            Button signin = (Button) findViewById(R.id.signin);
            signin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in1 = new Intent(MainActivity.this, scan.class);
                    startActivity(in1);
                }

            });
        }

    }

    // validating email id
    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    public boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }
    public void session()
    {
        SessionManagement2 obj=new SessionManagement2(getApplicationContext());
        obj.login();
        obj.emaildone();
        obj.passdone();
    }


}
