package com.example.marit.maritbeerepoot_pset5;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends ListFragment {
    public ArrayList<String> gerechten = new ArrayList<>();
    String category;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get bundle
        Bundle arguments = this.getArguments();
        category = arguments.getString("category");

        // Create new queue
        RequestQueue RQ = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Add to queue
        String categories = "https://resto.mprog.nl/menu";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, categories,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Parse JSON to arraylist
                            makelistview(getTextJSON(response));
                        } catch (JSONException e) {
                            // Catch exception when needed
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RQ.add(stringRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    public void makelistview(ArrayList<String> info) {
        // Set the adapter
        Log.d("list", info.toString());
        this.setListAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, info));
    }

    public ArrayList<String> getTextJSON(String string) throws JSONException {
        try {
            // The following code is from the android developers website
            JSONObject object = (JSONObject) new JSONTokener(string).nextValue();
            JSONArray cat = object.getJSONArray("items");
            // Loop through JSONArray to add the items to an Arraylist
            for (int i = 0; i < cat.length(); i++) {
                // Only get items with the wanted category
                if (Objects.equals(cat.getJSONObject(i).getString("category"), category)) {
                    gerechten.add(cat.getJSONObject(i).getString("name"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gerechten;
    }
}