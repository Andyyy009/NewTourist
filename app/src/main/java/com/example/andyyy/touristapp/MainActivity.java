package com.example.andyyy.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amitshekhar.DebugDB;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DebugDB.getAddressLog();
        myDB = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            finish();
            System.exit(0);

        }

        return super.onOptionsItemSelected(item);
    }

    public void openMonumentOverview(View view) {

        Intent intent = new Intent(this, MonumentOverviewActivity.class);
        startActivity(intent);
    }

    public void AddMonumentToDatabase(View view) {
        Intent intent = new Intent(this, AddMonument.class);
        startActivity(intent);
    }

    public void openTripTip(View view) {

        Intent intent = new Intent(this, TripTip.class);
        startActivity(intent);
    }


    public void openMap(View view) {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
