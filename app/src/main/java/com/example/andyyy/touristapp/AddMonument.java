package com.example.andyyy.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class AddMonument extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editTextNazev, editTextMesto, editTextPoznamka;
    Button buttonAdd, buttonDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monument);
        editTextNazev = (EditText) findViewById(R.id.editTextNazev);
        editTextMesto = (EditText) findViewById(R.id.editTextMesto);
        editTextPoznamka = (EditText) findViewById(R.id.editTextPoznamka);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonDb = (Button) findViewById(R.id.buttonDatabase);
        myDB = new DatabaseHelper(this);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editTextNazev.getText().toString();
                String newEntry1 = editTextMesto.getText().toString();
                String newEntry2 = editTextPoznamka.getText().toString();
                String navstiveno = "Ano";
                String currentDateString = DateFormat.getDateInstance().format(new Date());
                if (editTextNazev.length() != 0 || editTextMesto.length() != 0) {
                    AddData(newEntry, newEntry1, navstiveno, currentDateString, newEntry2);
                    editTextNazev.setText("");
                    editTextMesto.setText("");
                    editTextPoznamka.setText("");
                } else{
                    Toast.makeText(AddMonument.this, "Musis vlozit text", Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMonument.this, MonumentOverviewActivity.class);
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
        if (id == R.id.action_quit) {
            finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }


    //Metoda pro přidání dat do databáze
    public void AddData(String newEntry, String newEntry1,String navstiveno, String currentDateString, String newEntry2 ){
        boolean insertData = myDB.addData(newEntry, newEntry1, navstiveno, currentDateString, newEntry2);

        if (insertData == true) {
            Toast.makeText(AddMonument.this, "Vlozeno", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(AddMonument.this, "Neco se porouchalo", Toast.LENGTH_SHORT).show();
        }
    }
}
