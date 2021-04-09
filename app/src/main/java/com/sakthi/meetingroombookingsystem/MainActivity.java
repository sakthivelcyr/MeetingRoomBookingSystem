package com.sakthi.meetingroombookingsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Meeting Room Booking System");

        EditText user = (EditText) findViewById(R.id.user);
        TextView userErr = (TextView) findViewById(R.id.userErr);
        EditText pass = (EditText) findViewById(R.id.pass);
        TextView passErr = (TextView) findViewById(R.id.passErr);
        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userErr.setText("");
                passErr.setText("");
                if(user.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    if(user.getText().toString().isEmpty())
                        userErr.setText("Enter user");
                    if(pass.getText().toString().isEmpty())
                        passErr.setText("Enter password");
                }
                else {
                    if (user.getText().toString().equals("ADMIN") && pass.getText().toString().equals("TEST")) {
                        Intent i = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(i);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Wrong Login Credantials", Toast.LENGTH_LONG ).show();
                }

            }
        });
    }

    //Make title as center
    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }
}