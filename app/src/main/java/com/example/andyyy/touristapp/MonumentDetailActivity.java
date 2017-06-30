package com.example.andyyy.touristapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MonumentDetailActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_detail);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");
        TextView textViewNazev = (TextView) findViewById(R.id.textViewNazev);
        TextView textViewMesto = (TextView) findViewById(R.id.textViewMesto);
        TextView textViewPoznamka = (TextView) findViewById(R.id.textViewPoznamka);
        TextView textViewDatum = (TextView) findViewById(R.id.textViewDatum);

        //textViewNazev.setText(message);
        Cursor cursor = db.findByID(ID);
        cursor.moveToFirst();
       // Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
        textViewNazev.setText(cursor.getString(1));
        textViewMesto.setText(cursor.getString(2));
        textViewDatum.setText(cursor.getString(4));
        textViewPoznamka.setText(cursor.getString(5));

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
