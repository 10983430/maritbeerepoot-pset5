package com.example.marit.maritbeerepoot_pset5;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Marit on 28-11-2017.
 */

public class RestoAdapter extends CursorAdapter {
    public RestoAdapter(Context context, Cursor cursor) {
        super(context, cursor, R.layout.rowlayout);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.rowlayout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        // Find view
        TextView textje = view.findViewById(R.id.textView2);
        TextView amountje = view.findViewById(R.id.textView);

        // Show item
        textje.setText(cursor.getString(cursor.getColumnIndex("name")));
        amountje.setText(cursor.getString(cursor.getColumnIndex("amount")));
        Log.d("hiiiii", cursor.getString(cursor.getColumnIndex("name")));

    }
}