package com.example.andyyy.touristapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

    public class TripTip extends FragmentActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_tip);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longtitude = location.getLongitude();
                    String position = String.valueOf(latitude) + "," + String.valueOf(longtitude);
                    String result = null;
                    try {
                        result = new Retrieve().execute(position).get();
                        Log.i("Test", result);
                        if (!result.isEmpty()) {
                            JSONObject main = new JSONObject(result);
                            if(main.getString("status") == "OK"){
                                JSONArray results = main.getJSONArray("results");
                                for(int i = 0; i < results.length(); i++){
                                    MarkerOptions mOptions = new MarkerOptions();
                                    JSONObject obj = results.getJSONObject(i);
                                    JSONObject geometry = obj.getJSONObject("geometry");
                                    JSONObject locationj = geometry.getJSONObject("location");
                                    mOptions.position(new LatLng(locationj.getDouble("lat"), locationj.getDouble("lng")));
                                    String name = obj.getString("name");

                                    mOptions.title(name);
                                    mMap.addMarker(mOptions);
                                }
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                  //  textView.setText(result);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        else if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longtitude = location.getLongitude();
                    String position = String.valueOf(latitude) + "," + String.valueOf(longtitude);
                    String result = null;
                    try {
                        result = new Retrieve().execute(position).get();
                        Log.i("Test", result);
                        if (!result.isEmpty()) {
                            JSONObject main = new JSONObject(result);
                            if(main.getString("status") == "OK"){
                                JSONArray results = main.getJSONArray("results");
                                for(int i = 0; i < results.length(); i++){
                                    MarkerOptions mOptions = new MarkerOptions();
                                    JSONObject obj = results.getJSONObject(i);
                                    JSONObject geometry = obj.getJSONObject("geometry");
                                    JSONObject locationj = geometry.getJSONObject("location");
                                    mOptions.position(new LatLng(locationj.getDouble("lat"), locationj.getDouble("lng")));

                                    String name = obj.getString("name");
                                    Log.i("Test", name);
                                    mOptions.title(name);
                                    mMap.addMarker(mOptions);
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }

    }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }

}
