package com.example.andyyy.touristapp;

import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;
import static android.content.Context.LOCATION_SERVICE;


public class Retrieve extends AsyncTask<String, Void, String>{
    private Exception exception;


    protected void onPreExecute() {
      //  responseView.setText("");
    }

    protected String doInBackground(String... urls) {
        String position = urls[0];
        Log.d("zkouska", position);

        try {
            URL url= new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+position+"&radius=50000&types=museum,church,hindu_temple,synagogue,point_of_interest&key=AIzaSyDtOI-3tRfg7suOOKWHqoK3Ucwb-ksg3uc");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
      //  responseView.setText(response);
    }
}
