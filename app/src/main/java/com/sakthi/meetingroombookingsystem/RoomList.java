package com.sakthi.meetingroombookingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoomList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        DBHelper db = new DBHelper(this);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView roomView = (ListView) findViewById(R.id.room);

        ArrayList<Room> arrayList = db.getAllElements();

        //Create an array of elements
        final ArrayList<String> rooms = new ArrayList<>();

        for(int i=0;i<arrayList.size();i++) {
            rooms.add(arrayList.get(i).room_id+"                                         "+arrayList.get(i).building);
        }

        //Create adapter for ArrayList
        final ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, rooms);

        //Insert Adapter into List
        roomView.setAdapter(adapterRoom);

        //set click functionality for each list item
        roomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("User clicked ", rooms.get(position));
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}