package com.sakthi.meetingroombookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddViewRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view_room);

        DBHelper db = new DBHelper(this);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView building = (TextView) findViewById(R.id.building);
        TextView floor = (TextView) findViewById(R.id.floor);
        TextView room_id = (TextView) findViewById(R.id.room_id);

        Button homeBtn = (Button) findViewById(R.id.homeBtn);

        String id = getIntent().getStringExtra("id");

        String room_idRes = "";
        String buildingRes = "";
        String floorRes = "";

        Cursor res = db.getData(Integer.parseInt(id));
        if (res.moveToFirst())
        {
            room_idRes = ""+res.getInt(0);
            buildingRes = res.getString(1);
            floorRes = res.getString(2);
        }

        room_id.setText(room_idRes);
        building.setText(buildingRes);
        floor.setText(floorRes);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}