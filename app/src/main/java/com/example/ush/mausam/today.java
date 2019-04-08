package com.example.ush.mausam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static com.example.ush.mausam.RemoteAccess.OPEN_WEATHER_MAP_API;
import static com.example.ush.mausam.RemoteAccess.getJSON;

public class today extends Fragment {

    private View mView;
    TextView temp, humidity, condi, wind, extra;
    JSONObject Newdata = null;

    public today() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.today, container, false);

        temp = (TextView) mView.findViewById(R.id.t2);
        humidity = (TextView) mView.findViewById(R.id.t3);
        condi = (TextView) mView.findViewById(R.id.t4);
        wind = (TextView) mView.findViewById(R.id.t5);
        extra = (TextView) mView.findViewById(R.id.t6);


        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();




//----------------------NEW CODE---------------------------
        RequestQueue queue = Volley.newRequestQueue(getContext());
            String url ="http://api.openweathermap.org/data/2.5/weather?q=haldwani&appid=0089b8306978e1121b4b76ba274e3b8e";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       Log.d("Mausam",response.toString());
                       try{
                           JSONObject data = new JSONObject(response.toString());
                           Newdata = data;

                           //JSONArray jsonarr;
                           //JSONObject Jobj = data.getJSONObject("0");
                           JSONArray jsonarr=data.getJSONArray("main");
                           JSONObject jobject= jsonarr.getJSONObject(0);
                           String main=jobject.getString("temp");
                           String desc=jobject.getString("pressure");
                           String icon=jobject.getString("humidity");
                           String str=jobject.getString("temp_min");
                           wind.setText(str);
                           temp.setText(main);
                           humidity.setText(desc);
                           condi.setText(icon);

                           Log.d("print",str+main+desc);

//                            String main=jobject.getString("main");
//                           temp.setText(main);
//                           humidity.setText(desc);
//                           condi.setText(icon);
//                           jsonarr=data.getJSONArray("base");
//
//                           JSONObject jobject2= jsonarr.getJSONObject(1);
//                           String base=jobject.getString("base");
//                          String base2=jobject2.getString("base");
//                           wind.setText(base2);
//                           Toast.makeText(getContext(),"base: "+base,Toast.LENGTH_SHORT);
//



                       }catch (Exception e){
                           //TODO handle exception here
                       }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "That didnt workeed", Toast.LENGTH_SHORT).show();
                Log.d("Mausam", error.getMessage().toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);




    }


}6
