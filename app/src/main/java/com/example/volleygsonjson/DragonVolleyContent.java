package com.example.volleygsonjson;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonVolleyContent {
    Context context = App.getContext();
    Resources res = context.getResources();

    public static final Map<String, DragonVolleyModel> VOLLEY_MAP = new HashMap<String, DragonVolleyModel>();
    public static final List<DragonVolleyModel> JSONSTUFFS = new ArrayList<DragonVolleyModel>();

    private boolean isBuilt = false;
    private boolean haveIt = false;
    public boolean doneLoaded = false;


    private void addVolleyToList(DragonVolleyModel volleyThing){
        if(JSONSTUFFS.size() != 0 ){
            for(int i = 0; i<JSONSTUFFS.size(); i++){
                if(JSONSTUFFS.get(i) == volleyThing){
                    haveIt = true;
                }
                else if (JSONSTUFFS.get(i) == null){
                    haveIt = true;
                }
            }
        }
        if(!haveIt){
            JSONSTUFFS.add(volleyThing);
            VOLLEY_MAP.put(volleyThing.getmName(), volleyThing);
        }
    }
    public void testAllThatJazzes(Activity activity) {
        String url = res.getString(R.string.url);
        //String url = res.getString(R.string.url);  // THAT should be in a strings.xml file!
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(activity);

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("dragons");
                            JSONSTUFFS.clear();
                            VOLLEY_MAP.clear();
                            //mTextView.append(jsonArray.toString());
                            for(int i = 0; i<jsonArray.length(); i++){
                                JSONObject gameCompanies = jsonArray.getJSONObject(i);
                                String jsonHolder = String.valueOf(gameCompanies);
                                Gson gson = new Gson();
                                DragonVolleyModel DVM = gson.fromJson(jsonHolder, DragonVolleyModel.class);
                                addVolleyToList(DVM);
                            }

                            //startActivity(ItemListFragment.super.getActivity().getIntent());
                        } catch (JSONException e){
                            //mTextView.setText(e.toString());
                        }
                        if(!isBuilt){
                            isBuilt = true;

                            activity.recreate();
                        }

                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(request);

    }


}
