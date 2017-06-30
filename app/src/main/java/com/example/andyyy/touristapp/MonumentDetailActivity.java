package com.example.andyyy.touristapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MonumentDetailActivity extends AppCompatActivity {

    DatabaseHelper db;
    String cesta;
    public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_detail);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");
        Button buttonToMap = (Button) findViewById(R.id.buttonToMap);
        Button buttonTakePhoto = (Button) findViewById(R.id.buttonTakePhoto);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);
        Button buttonEdit = (Button) findViewById(R.id.buttonEdit);
        final TextView textViewNazev = (TextView) findViewById(R.id.textViewNazev);
        final TextView textViewMesto = (TextView) findViewById(R.id.textViewMesto);
        final TextView textViewPoznamka = (TextView) findViewById(R.id.textViewPoznamka);
        final TextView textViewDatum = (TextView) findViewById(R.id.textViewDatum);
        //textViewNazev.setText(message);
        final Cursor cursor = db.findByID(ID);
        cursor.moveToFirst();
       // Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
        textViewNazev.setText(cursor.getString(1));
        textViewMesto.setText(cursor.getString(2));
        textViewDatum.setText(cursor.getString(4));
        textViewPoznamka.setText(cursor.getString(5));



        buttonToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(MonumentDetailActivity.this, MapsActivity.class);
                String nazev =  textViewNazev.getText().toString();
                mapIntent.putExtra("EXTRA_MESSAGE", nazev);
                startActivityForResult(mapIntent, 0);
            }
        });

        buttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MonumentDetailActivity.this, PhotoActivity.class);
                intent.putExtra("ID", ID);
                startActivity(intent);

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.DeleteRow(ID);
                Toast.makeText(MonumentDetailActivity.this, "Záznam byl smazán", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MonumentDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nazev =  textViewNazev.getText().toString();
                String Mesto = textViewMesto.getText().toString();
                String Poznamka = textViewPoznamka.getText().toString();
                String Datum = textViewDatum.getText().toString();

                Intent intent = new Intent(MonumentDetailActivity.this, EditData.class);
                intent.putExtra("Nazev", Nazev);
                intent.putExtra("Mesto", Mesto);
                intent.putExtra("Poznamka", Poznamka);
                intent.putExtra("Datum", Datum);
                intent.putExtra("ID", ID);

                startActivity(intent);
            }
        });

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
