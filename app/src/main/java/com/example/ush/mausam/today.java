package com.example.ush.mausam;

import android.annotation.SuppressLint;
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

import org.json.JSONObject;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.math.*;

public class today extends Fragment {

    private View mView;
    TextView temp, prepprob, humidity, wind_speed,pressure,cloud,uvIndex,ozone;
    JSONObject Newdata = null;

    public today() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.today, container, false);

        temp = (TextView) mView.findViewById(R.id.temp);
        prepprob = (TextView) mView.findViewById(R.id.prepprob);
        humidity = (TextView) mView.findViewById(R.id.humidity);
        wind_speed = (TextView) mView.findViewById(R.id.wind_speed);
        pressure = (TextView) mView.findViewById(R.id.pressure);
        cloud =(TextView) mView.findViewById(R.id.cloudcover);
        uvIndex =(TextView) mView.findViewById(R.id.uvindex);
         ozone =(TextView) mView.findViewById(R.id.ozone);
         TextView celcius =(TextView)mView.findViewById(R.id.celcius);
        celcius.setText((char) 0x00B0  + "C");

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();




//----------------------NEW CODE---------------------------
        RequestQueue queue = Volley.newRequestQueue(getContext());
            //String url ="https://api.darksky.net/forecast/2e444c242c30a304f476f6381a96c48a/30.398724,%2078.075705";
        String url="https://api.darksky.net/forecast/2e444c242c30a304f476f6381a96c48a/30.397595,78.075168";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       Log.d("Mausam",response.toString());
                       try{
                           JSONObject data = new JSONObject(response.toString());
                           Newdata = data;


                           JSONObject jsonob=data.getJSONObject("currently");
                           Log.d("Main",jsonob.toString());
                           String sttemp=jsonob.getString(("temperature"));
                           String stpress=jsonob.getString(("pressure"));
                           String sthumdi=jsonob.getString(("humidity"));
                           String stwind=jsonob.getString(("windSpeed"));
                           String stvisible=jsonob.getString(("visibility"));
                           String stprepprob= jsonob.getString("precipProbability");
                           String stcloud=jsonob.getString("cloudCover");
                           String stuvIndex=jsonob.getString("uvIndex");
                           String stozone=jsonob.getString("ozone");
                           
                           temp.setText(""+(Math.round((((Float.parseFloat(sttemp)-32)*5)/9))));
                           wind_speed.setText("Wind Speed: "+(Float.parseFloat(stwind)));
                           humidity.setText("Humidity: "+Float.parseFloat(sthumdi));
                           pressure.setText("Pressure: "  + Float.parseFloat(stpress));
                           pressure.setText("Visibility: " + Float.parseFloat(stvisible));
                           prepprob.setText("Precipitation Probability: "+ Float.parseFloat(stprepprob));
                           cloud.setText("Cloud Cover: "+Float.parseFloat(stcloud));
                           uvIndex.setText("Cloud Cover: "+Float.parseFloat(stuvIndex));
                           ozone.setText("Cloud Cover: "+Float.parseFloat(stozone));
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


}
