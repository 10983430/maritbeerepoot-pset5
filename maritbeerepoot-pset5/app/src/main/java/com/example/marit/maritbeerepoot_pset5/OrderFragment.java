package com.example.marit.maritbeerepoot_pset5;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.DialogFragment;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends DialogFragment {
    RestoDatabase db;
    RestoAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_order, container, false);
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        ListView list = (ListView) view.findViewById(R.id.list);
        db = RestoDatabase.getInstance(getContext());
        //db.insert("Italian Salad",5);
        Cursor cursor = db.selectAll();
        adapter = new RestoAdapter(getContext(), cursor);

        list.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
