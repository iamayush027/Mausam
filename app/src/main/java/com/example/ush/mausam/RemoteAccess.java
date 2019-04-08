package com.example.ush.mausam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class RemoteAccess {

    public static JSONObject jsonObject = null;

    public static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=0089b8306978e1121b4b76ba274e3b8e";

    public static JSONObject getJSON(final Context context, final String city) {

        try {       Log.d("print",""+OPEN_WEATHER_MAP_API);
                    URL url = new URL(String.format(OPEN_WEATHER_MAP_API,"Haldwani"));
            Log.d("print",""+url);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.d("print",""+connection);
                   // connection.addRequestProperty("x-api-key",context.getString(R.string.open_weather_maps_app_id));
                    InputStream input=connection.getInputStream();
            Log.d("print",""+input);

            BufferedReader reader=new BufferedReader(new InputStreamReader(input));
           // reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            Log.d("print",""+OPEN_WEATHER_MAP_API);
                    Log.d("JSON",""+connection.getInputStream());

                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";
                    while ((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    JSONObject data = new JSONObject(json.toString());
                    Log.d("JSON",""+data);

                    jsonObject = data;

                    // This value will be 404 if the request was not
                    // successful
                    if (data.getInt("cod") != 200) {
                        return null;
                    }


                } catch (Exception e) {
                    Log.d("Exceptioning",""+e);
                    return null;
                }

        return jsonObject;

    }


    }





