package com.example.marit.maritbeerepoot_pset5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marit on 27-11-2017.
 */

public class RestoDatabase extends SQLiteOpenHelper {
    private static RestoDatabase instance;
    private static int version = 1;
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
        db.execSQL("create table resto (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price INTEGER,amountordered INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "resto");
        onCreate(db);
    }
}
