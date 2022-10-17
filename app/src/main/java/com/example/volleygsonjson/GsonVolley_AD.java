package com.example.volleygsonjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//I've just realized that NONE of the things inside this is necessary because it's ALL in ItemDetailFragment -_-
public class GsonVolley_AD extends AppCompatActivity {
    private TextView tV;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_volley_ad);

        tV = findViewById(R.id.textViewResult);
        Button buttonParse = findViewById(R.id.buttonParse);

        requestQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                jsonParse();
            }
        });
    }

    private void jsonParse(){
        String url = "";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
        new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray jsonArray = response.getJSONArray("");

                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject arrayName = jsonArray.getJSONObject(i);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });
    }
}