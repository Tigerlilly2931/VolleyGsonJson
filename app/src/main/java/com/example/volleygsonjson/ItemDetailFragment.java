package com.example.volleygsonjson;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.example.volleygsonjson.placeholder.PlaceholderContent;
import com.example.volleygsonjson.databinding.FragmentItemDetailBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    private FloatingActionButton testingFab;
    //private TextView tvResult;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The placeholder content this fragment is presenting.
     */
    //private PlaceholderContent.PlaceholderItem mItem;
    private VolleyModel mItem;
    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mTextView;

    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            ClipData.Item clipDataItem = event.getClipData().getItemAt(0);
            mItem = VolleyContent.VOLLEY_MAP.get(clipDataItem.getText().toString());
            updateContent();
        }
        return true;
    };
    private FragmentItemDetailBinding binding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the placeholder content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = VolleyContent.VOLLEY_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        mToolbarLayout = rootView.findViewById(R.id.toolbar_layout);
        mTextView = binding.itemDetail;
        //tvResult = rootView.findViewById(R.id.textViewResults);
        testingFab = rootView.findViewById(R.id.fab);
        // Show the placeholder content as text in a TextView & in the toolbar if available.
        updateContent();
        rootView.setOnDragListener(dragListener);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //String intToYearsssss = ((String) mItem.getmYear());
    private void updateContent() {

        if (mItem != null) {
            mTextView.setText(String.valueOf(mItem.getmConsole()));
            if (mToolbarLayout != null) {
                String strHold = mItem.getmName();
                String parsingUpper = strHold.substring(0,1).toUpperCase() + strHold.substring(1);
                mToolbarLayout.setTitle(parsingUpper + " Dragon   HP: " + String.valueOf(mItem.getmYear()));
            }
        }
        if (testingFab != null)
        {
            testingFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {

                }
            });
        }
    }
    private void stestAllThatJazzes() {
        String url = "https://api.jsonbin.io/v3/b/5f726a107243cd7e8245d58b";  // THAT should be in a strings.xml file!
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");
                            //mTextView.append(jsonArray.toString());
                            for(int i = 0; i<jsonArray.length(); i++){
                                JSONObject gameCompanies = jsonArray.getJSONObject(i);

                                String companyName = gameCompanies.optString("name");
                                int companyYear = gameCompanies.optInt("year");
                                String recentConsole = gameCompanies.optString("recentConsole");

                                mTextView.setText(companyName + ", " + String.valueOf(companyYear) + ", " + recentConsole + "\n\n");
                            }
                        } catch (JSONException e){
                            mTextView.setText(e.toString());
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

    //Prof's original
   /* private void testAllThatJazz() {
        String url = "https://api.jsonbin.io/v3/b/5f726a107243cd7e8245d58b";  // THAT should be in a strings.xml file!

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string in our convenient existing text view
                        mTextView.setText("Response is: "+ response);
                        // NEXT, we need to use GSON to turn that JSON into a model

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // you should drop a breakpoint RIGHT HERE if you need to see the error coming back
                mTextView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    */
}