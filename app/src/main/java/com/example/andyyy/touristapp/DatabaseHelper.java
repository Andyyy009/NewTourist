package com.example.andyyy.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_name = "Monument_DB";
    public static final String Table_name = "Monument_table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "NAZEV";
    public static final String Col_3 = "OBEC";
    public static final String Col_4 = "NAVSTIVENO";
    public static final String Col_5 = "DATUM_NAVSTEVY";
    public static final String Col_6 = "POZNAMKA";

    public DatabaseHelper(Context context) {

        super(context, Database_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
     //   context.deleteDatabase(Database_name);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //String SQL_String = "CREATE TABLE " + Table_name + "(" + Col_1 +
        //        "INTEGER PRIMARY KEY AUTOINCREMENT)," + Col_2 + " NAZEV TEXT" + Col_3 + "OBEC TEXT)";
        String SQL_String = "CREATE TABLE " + Table_name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " " + " NAZEV TEXT, OBEC TEXT, NAVSTIVENO TEXT, DATUM_NAVSTEVY DATETIME, POZNAMKA TEXT)";

        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Table_name);
        onCreate(db);
    }

    public boolean addData(String nazev, String obec, String navstiveno, String datum, String poznamka){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, nazev);
        contentValues.put(Col_3, obec);
        contentValues.put(Col_4, navstiveno);
        contentValues.put(Col_5, datum);
        contentValues.put(Col_6, poznamka);

     //   db.insert(Table_name, null, contentValues1);
        long result = db.insert(Table_name, null, contentValues);
        if (result == -1){
            return false;}
        else{
            return true;}

    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + Table_name, null);
        return data;
    }

    public Cursor findByID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + Table_name + " WHERE ID = " + id , null);
        return data;
    }

    public void DeleteRow(String id){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + Table_name + " WHERE ID = " + id;
        //db.rawQuery("DELETE FROM " + Table_name + " WHERE ID = " + id , null);
        db.execSQL(query);
    }

    public Cursor getItemID(String Nazev){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Col_1 + " FROM " + Table_name + " WHERE " + Col_2 + " = '" + Nazev + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateRow(String id, String Nazev, String Mesto, String Datum, String Poznamka)
  //  public void updateRow(String id, String Nazev, String Mesto)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       // String query = "UPDATE " + Table_name + " SET " + Col_2 + " = '" + Nazev + "' " + Col_3 +
        //        " = '" + Mesto + "' " + Col_5 + " = '" + Datum + "' " + Col_6 + " = '" + Poznamka + "' WHERE " + Col_1 + " = '" + id + "'";
        String query = "UPDATE " + Table_name + " SET " + Col_2 + " = '" + Nazev + "', " + Col_3 + " = '" + Mesto + "', " + Col_5 + " = '" + Datum + "', " + Col_6 + " = '" + Poznamka + "' WHERE " + Col_1 + " = '" + id + "'";
        db.execSQL(query);
    }
}
