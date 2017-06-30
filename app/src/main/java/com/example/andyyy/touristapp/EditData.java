package com.example.andyyy.touristapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditData extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        final String ID = intent.getStringExtra("ID");

        final EditText editTextNazev = (EditText) findViewById(R.id.editTextNazev);
        editTextNazev.setText(intent.getStringExtra("Nazev"));

        final EditText editTextMesto = (EditText) findViewById(R.id.editTextMesto);
        editTextMesto.setText(intent.getStringExtra("Mesto"));

        final EditText editTextPoznamka = (EditText) findViewById(R.id.editTextPoznamka);
        editTextPoznamka.setText(intent.getStringExtra("Poznamka"));

        final EditText editTextDatum = (EditText) findViewById(R.id.editTextDatum);
        editTextDatum.setText(intent.getStringExtra("Datum"));

        Button buttonedit = (Button) findViewById(R.id.buttonEdit);


        buttonedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nazev = editTextNazev.getText().toString();
                String Mesto = editTextMesto.getText().toString();
                String Poznamka = editTextPoznamka.getText().toString();
                String Datum = editTextDatum.getText().toString();

                db.updateRow(ID, Nazev, Mesto, Datum, Poznamka);

                Toast.makeText(EditData.this, "Záznam byl upraven", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditData.this, MonumentOverviewActivity.class);
                startActivity(intent);
            }
        });


    }
}
