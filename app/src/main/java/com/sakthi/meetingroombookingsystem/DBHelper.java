package com.sakthi.meetingroombookingsystem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class Room {
    int room_id;
    String building;
    String floor;

    void setId(int id){
        this.room_id = id;
    }
    void setBuilding(String building){
        this.building = building;
    }

    void setFloor(String floor){
        this.floor = floor;
    }
}

public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "mrbsystem.db";
    public static final String ROOM_TABLE_NAME = "Rooms";
    public static final String ROOM_COLUMN_ID = "id";
    public static final String ROOM_COLUMN_BUILDING = "building";
    public static final String ROOM_COLUMN_FLOOR = "floor";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Rooms " +
                        "(id integer primary key, building text,floor text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Rooms");
        onCreate(db);
    }

    public int addRoom (String building, String floor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("building", building);
        contentValues.put("floor", floor);
        db.insert("Rooms", null, contentValues);
        return this.numberOfRows();
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Rooms where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ROOM_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String product_name, String description, String type, int quantity, int unit_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_name", product_name);
        contentValues.put("description", description);
        contentValues.put("type", type);
        contentValues.put("quantity", quantity);
        contentValues.put("unit_price", unit_price);
        db.update("TBL_Product", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteRoom (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Rooms",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Room> getAllElements() {

        ArrayList<Room> list = new ArrayList<Room>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + ROOM_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Room obj = new Room();
                        //only one column
                        obj.setId(cursor.getInt(0));
                        obj.setBuilding(cursor.getString(1));
                        obj.setFloor(cursor.getString(2));

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        return list;
    }
}
