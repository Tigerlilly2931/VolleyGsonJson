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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolleyContent {
    Context context = App.getContext();
    Resources res = context.getResources();

    public static final Map<String, VolleyModel> VOLLEY_MAP = new HashMap<String, VolleyModel>();
    public static final List<VolleyModel> JSONSTUFFS = new ArrayList<VolleyModel>();

    public String dragon1;
    public int hp1;
    public String desc1;

    public List<String> names = new ArrayList<String>();
    public List<String> years = new ArrayList<String>();
    public List<String> consoles = new ArrayList<String>();


    private boolean isBuilt = false;

    public String dragon2;
    public int hp2;
    public String desc2;

    public String dragon3;
    public int hp3;
    public String desc3;

    public String dragon4;
    public int hp4;
    public String desc4;

    public String dragon5;
    public int hp5;
    public String desc5;

    public String dragon6;
    public int hp6;
    public String desc6;

    private boolean haveIt = false;
    public boolean doneLoaded = false;

    public List<VolleyModel> createVolleyStuff(){

        VolleyModel thing1 = new VolleyModel(dragon1, hp1, desc1);
        VolleyModel thing2 = new VolleyModel(dragon2,hp2, desc2);
        VolleyModel thing3 = new VolleyModel(dragon3, hp3, desc3);
        VolleyModel thing4 = new VolleyModel(dragon4, hp4, desc4);
        VolleyModel thing5 = new VolleyModel(dragon5, hp5, desc5);
        VolleyModel thing6 = new VolleyModel(dragon6, hp6, desc6);
        JSONSTUFFS.clear();
        VOLLEY_MAP.clear();
        addVolleyToList(thing1);
        addVolleyToList(thing2);
        addVolleyToList(thing3);
        addVolleyToList(thing4);
        addVolleyToList(thing5);
        addVolleyToList(thing6);
        return JSONSTUFFS;
    }

    private void addVolleyToList(VolleyModel volleyThing){
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
                            //mTextView.append(jsonArray.toString());
                            for(int i = 0; i<jsonArray.length(); i++){
                                JSONObject gameCompanies = jsonArray.getJSONObject(i);

                                String dragonType = gameCompanies.optString("type");
                                int dragonHP = gameCompanies.optInt("hp");
                                String dragonDescription = gameCompanies.optString("description");

                                //mTextView.setText(dragonType + ", " + String.valueOf(dragonHP) + ", " + dragonDescription + "\n\n");
                                String inttoStr = String.valueOf(dragonHP);
                                //volleyContent.names.add(dragonType);
                                //volleyContent.years.add(inttoStr);
                                //volleyContent.consoles.add(dragonDescription);

                                switch(i){
                                    case 0:
                                        dragon1 = dragonType;
                                        hp1 = dragonHP;
                                        desc1 = dragonDescription;
                                        break;

                                    case 1:
                                        dragon2 = dragonType;
                                        hp2 = dragonHP;
                                        desc2 = dragonDescription;
                                        break;

                                    case 2:
                                        dragon3 = dragonType;
                                        hp3 = dragonHP;
                                        desc3 = dragonDescription;
                                        break;

                                    case 3:
                                        dragon4 = dragonType;
                                        hp4 = dragonHP;
                                        desc4 = dragonDescription;
                                        break;

                                    case 4:
                                        dragon5 = dragonType;
                                        hp5 = dragonHP;
                                        desc5 = dragonDescription;
                                        break;

                                    case 5:
                                        dragon6 = dragonType;
                                        hp6 = dragonHP;
                                        desc6 = dragonDescription;
                                        break;
                                    default:
                                        break;
                                }
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
