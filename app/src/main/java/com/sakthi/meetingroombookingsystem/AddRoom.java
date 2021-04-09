package com.sakthi.meetingroombookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        DBHelper db = new DBHelper(this);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView buildingTxtView = (TextView) findViewById(R.id.buildingTxtView);
        TextView floorTxtView = (TextView) findViewById(R.id.floorTxtView);
        TextView buildinErr = (TextView) findViewById(R.id.buildingErr);
        TextView floorErr = (TextView) findViewById(R.id.floorErr);

        EditText building = (EditText) findViewById(R.id.building);
        EditText floor = (EditText) findViewById(R.id.floor);

        Button add_roomBtn = (Button) findViewById(R.id.add_roomBtn);

        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);

        SpannableString spannableStringBuilding = new SpannableString("Building *");
        spannableStringBuilding.setSpan(foregroundSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        buildingTxtView.setText(spannableStringBuilding);

        SpannableString spannableStringFloor = new SpannableString("Floor *");
        spannableStringFloor.setSpan(foregroundSpan, 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        floorTxtView.setText(spannableStringFloor);

        add_roomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildinErr.setText("");
                floorErr.setText("");

                if(building.getText().toString().isEmpty() || floor.getText().toString().isEmpty()) {
                    if(building.getText().toString().isEmpty())
                        buildinErr.setText("Building is mandatory");

                    if(floor.getText().toString().isEmpty())
                        floorErr.setText("Floor is mandatory");
                }
                else {
                    String buildingStr = building.getText().toString();
                    String floorStr = floor.getText().toString();
                    int res = db.addRoom(buildingStr, floorStr);

                    Intent i = new Intent(getBaseContext(), AddViewRoom.class);
                    i.putExtra("id", ""+res);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}