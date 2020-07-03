package com.test.location_googlemap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    ArrayList<Maps> mapsArrayList = new ArrayList<>();

    RecyclerView recyclerView;  // 메인 화면에 있는 리사이클러 뷰
    RecyclerViewAdapter recyclerViewAdapter;    // 우리가 만든, 하나의 셀을 연결시키는 어댑터

    private String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?language=ko&location=37.541253,126.838158&radius=2000&key=AIzaSyD3KC2ug6UrcFciDoqR8LrWd98rn59mit0";

    String nextPageToken;
    String pageToken = "";

    // TODO: 2020-07-02 lng, lat Maps.class에 생성자 추가해주고 photo처리


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,baseUrl,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.i("AAA4",baseUrl);
                    Log.i("AAA", response.toString());
                    nextPageToken = response.getString("nextPageToken");

                    JSONArray jArrayResults = response.getJSONArray("results");
                    for(int i=0;i<jArrayResults.length();i++){
                        JSONObject jsonObject = jArrayResults.getJSONObject(i);

                        Log.i("AAA2", response.toString());

                        String name = jsonObject.getString("name");
                        String icon_url = jsonObject.getString("icon");
                        String vicinity = jsonObject.getString("vicinity");

                        Maps maps = new Maps();
                        maps.setName(name);
                        maps.setIconUrl(icon_url);
                        maps.setVicinity(vicinity);
                        mapsArrayList.add(maps);


                    }
                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mapsArrayList);
                    recyclerView.setAdapter(recyclerViewAdapter);

                } catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);




    }


}
