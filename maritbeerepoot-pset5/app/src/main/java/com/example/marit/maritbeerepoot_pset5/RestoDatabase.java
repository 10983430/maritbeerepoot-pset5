package com.example.marit.maritbeerepoot_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Marit on 27-11-2017.
 */

public class RestoDatabase extends SQLiteOpenHelper {
    private static RestoDatabase instance;
    private static int version = 2;
    private static String databasename = "resto";


    private RestoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static RestoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new RestoDatabase(context.getApplicationContext(), databasename, null, version);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table resto (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price DOUBLE, amount INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "resto");
        onCreate(db);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor lol = db.rawQuery("SELECT * FROM resto", null);
        return lol;
    }

    public void insert(String name, double price) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM resto WHERE name = '"+ name+ "'",null);
        ContentValues values =  new ContentValues();

        // Check if item is in the database
        if (cursor.getCount()>0){
            Integer tempid = cursor.getColumnIndex("_id");
            Integer tempamount = cursor.getColumnIndex("amount");
            values.put("amount", tempamount + 1);
            db.update("resto", values,"_id=" + tempid, null);
            Integer newamount = tempamount + 1;
            Log.d("lolhi", newamount.toString());
        }

        // If not, add it to the database
        else {
            values.put("name", name);
            values.put("price", price);
            values.put("amount", 1);
            db.insert("resto",null, values);
        }
    }
/*
    public void update(long id, int box) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", box);
        db.update("resto", values, "_id=" + id, null);
    }*/

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("resto", "_id=" + id, null);
    }

    public void clear() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("resto", null, null);
    }
}
