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

public class VolleyContent {
    Context context = App.getContext();
    Resources res = context.getResources();

    public static final Map<String, VolleyModel> VOLLEY_MAP = new HashMap<String, VolleyModel>();
    public static final List<VolleyModel> JSONSTUFFS = new ArrayList<VolleyModel>();

    private boolean BuiltDammit = false;

    private boolean haveIt = false;
    public boolean doneLoaded = false;

    /**public String cname1;
    public int cyear1;
    public String cconsole1;

    public List<String> names = new ArrayList<String>();
    public List<String> years = new ArrayList<String>();
    public List<String> consoles = new ArrayList<String>();




    public String cname2;
    public int cyear2;
    public String cconsole2;

    public String cname3;
    public int cyear3;
    public String cconsole3;

    public String cname4;
    public int cyear4;
    public String cconsole4;

    public String cname5;
    public int cyear5;
    public String cconsole5;

    public String cname6;
    public int cyear6;
    public String cconsole6;



    public List<VolleyModel> createVolleyStuff(){

        VolleyModel thing1 = new VolleyModel(cname1, cyear1, cconsole1);
        VolleyModel thing2 = new VolleyModel(cname2,cyear2, cconsole2);
        VolleyModel thing3 = new VolleyModel(cname3, cyear3, cconsole3);
        VolleyModel thing4 = new VolleyModel(cname4, cyear4, cconsole4);
        VolleyModel thing5 = new VolleyModel(cname5, cyear5, cconsole5);
        VolleyModel thing6 = new VolleyModel(cname6, cyear6, cconsole6);

        addVolleyToList(thing1);
        addVolleyToList(thing2);
        addVolleyToList(thing3);
        addVolleyToList(thing4);
        addVolleyToList(thing5);
        addVolleyToList(thing6);
        return JSONSTUFFS;
    }*/

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
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");
                            JSONSTUFFS.clear();
                            VOLLEY_MAP.clear();
                            //mTextView.append(jsonArray.toString());
                            for(int i = 0; i<jsonArray.length(); i++){
                                JSONObject gameCompanies = jsonArray.getJSONObject(i);

                                String jboyson = String.valueOf(gameCompanies);
                                Gson gboyson = new Gson();
                                VolleyModel vModel = gboyson.fromJson(jboyson, VolleyModel.class);
                                addVolleyToList(vModel);

                                /**String companyName = gameCompanies.optString("name");
                                int companyYear = gameCompanies.optInt("year");
                                String recentConsole = gameCompanies.optString("recentConsole");

                                //mTextView.setText(companyName + ", " + String.valueOf(companyYear) + ", " + recentConsole + "\n\n");
                                String inttoStr = String.valueOf(companyYear);
                                //volleyContent.names.add(companyName);
                                //volleyContent.years.add(inttoStr);
                                //volleyContent.consoles.add(recentConsole);

                                switch(i){
                                    case 0:
                                        cname1 = companyName;
                                        cyear1 = companyYear;
                                        cconsole1 = recentConsole;
                                        break;

                                    case 1:
                                        cname2 = companyName;
                                        cyear2 = companyYear;
                                        cconsole2 = recentConsole;
                                        break;

                                    case 2:
                                        cname3 = companyName;
                                        cyear3 = companyYear;
                                        cconsole3 = recentConsole;
                                        break;

                                    case 3:
                                        cname4 = companyName;
                                        cyear4 = companyYear;
                                        cconsole4 = recentConsole;
                                        break;

                                    case 4:
                                        cname5 = companyName;
                                        cyear5 = companyYear;
                                        cconsole5 = recentConsole;
                                        break;

                                    case 5:
                                        cname6 = companyName;
                                        cyear6 = companyYear;
                                        cconsole6 = recentConsole;
                                        break;
                                    default:
                                        break;
                                }

                                //volleyContent.cname1 = name1;
                                //volleyContent.cyear1 = year1;
                                //volleyContent.cconsole1 = con1;*/
                            }

                            //startActivity(ItemListFragment.super.getActivity().getIntent());
                        } catch (JSONException e){
                            //mTextView.setText(e.toString());
                        }
                        if(!BuiltDammit){
                            BuiltDammit = true;

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
