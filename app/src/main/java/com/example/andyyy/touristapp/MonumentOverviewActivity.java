package com.example.andyyy.touristapp;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class MonumentOverviewActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    //ListAdapter listAdapter;
    ArrayAdapter<String> listAdapter;
    ArrayList<String> listItems, listItemsID;
    ListView listView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monument_overview_activity);

        myDB = new DatabaseHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.listView);
        listItemsID = new ArrayList<>();
        listItems = new ArrayList<>();
        final Cursor data = myDB.getListContents();
        while (data.moveToNext()){

            listItems.add(data.getString(1) + " - " + data.getString(2));
            listItemsID.add(data.getString(0));
            listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItems);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Toast.makeText(view.getContext(),adapter.getItem(position),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MonumentOverviewActivity.this, MonumentDetailActivity.class);
                    String message = listAdapter.getItem(position).toString();
                    intent.putExtra("EXTRA_MESSAGE", message);
                    String ID = listItemsID.get(position);
                    intent.putExtra("ID", ID);
                    //Toast.makeText(MonumentOverviewActivity.this, test + " " + position + " " + id, Toast.LENGTH_LONG).show();
                    startActivityForResult(intent, 0);

                }
            });
        }

        searchView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchView.setIconified(false);
        }
    });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                listAdapter.getFilter().filter(text);
                return false;
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





 /*   public void order () {

       // EditText editTextCity = (EditText) findViewById(R.id.editTextCity);

        final ArrayAdapter<String> adapter;
        listItems = new ArrayList<>();
        //Nahrani databaze z Excelu
        try{
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("Monument_DB.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int row = sheet.getRows();
            String displayText = "";
            for(int i=1; i<row;i++){
                Cell cell = sheet.getCell(3,i);
                displayText = cell.getContents();
                cell = sheet.getCell(7,i);
                displayText = displayText + " - " + cell.getContents();
                listItems.add(displayText);
            }
            adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.txtitem, listItems);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                        initList();
                    }
                    else {
                    searchItem(s.toString());
                }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        catch(Exception e){

        }
    }*/



    public void initList() {

    }
    public void searchItem(String textToSearch) {

    }
}


