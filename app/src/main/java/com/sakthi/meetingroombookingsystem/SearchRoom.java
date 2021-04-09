package com.sakthi.meetingroombookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SearchRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);

        DBHelper db = new DBHelper(this);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText room_id = (EditText) findViewById(R.id.room_id);
        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);

        TextView building_txt_view = (TextView) findViewById(R.id.building_txt_view);
        TextView room_id_txt_view = (TextView) findViewById(R.id.room_id_txt_view);
        TextView floor_txt_view = (TextView) findViewById(R.id.floor_txt_view);

        TextView room_idView = (TextView) findViewById(R.id.room_idView);
        TextView buildingView = (TextView) findViewById(R.id.buildingView);
        TextView floorView = (TextView) findViewById(R.id.floorView);

        deleteBtn.setVisibility(View.INVISIBLE);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getData(Integer.parseInt(room_id.getText().toString()));

                if(res.getCount()==0){
                    Toast.makeText(getApplicationContext(),"Room id : "+room_id.getText().toString()+" not found.", Toast.LENGTH_LONG ).show();
                }
                else{
                    building_txt_view.setText("Building");
                    room_id_txt_view.setText("Room Id");
                    floor_txt_view.setText("Floor");

                    if (res.moveToFirst())
                    {
                        String room_idRes = ""+res.getInt(0);
                        String buildingRes = res.getString(1);
                        String floorRes = res.getString(2);

                        room_idView.setText(room_idRes);
                        buildingView.setText(buildingRes);
                        floorView.setText(floorRes);
                    }

                    deleteBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRoom(Integer.parseInt(room_id.getText().toString()));
                Toast.makeText(getApplicationContext(),"Room id : "+room_id.getText().toString()+" deleted.", Toast.LENGTH_LONG ).show();
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