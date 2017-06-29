package com.example.andyyy.touristapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class MonumentDetailActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_detail);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        String ID = intent.getStringExtra("ID");
        TextView textView = (TextView) findViewById(R.id.textView);
        final TextView textViewDate = (TextView) findViewById(R.id.textView4);

        textView.setText(message);
        Cursor cursor = db.findByID(ID);
        cursor.moveToFirst();
       // Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
        textViewDate.setText(cursor.getString(4));

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

}
