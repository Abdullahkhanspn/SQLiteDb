package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

//Extends the SQLiteOpenHelper base class
public class DbHandler extends SQLiteOpenHelper {
    //Making A Constructor in the DbHandler.
    public DbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory , version);
    }

    @Override
    //method to create a table in the database .
    public void onCreate(SQLiteDatabase db) {
    String create = "CREATE TABLE myemployee (sno INTEGER PRIMARY KEY, name TEXT, increment TEXT)";
    //execute the database now.
    db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Upgrading the database.
        String drop = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(drop, new String[]{"myemployee"});
        onCreate(db);
    }
    public void addEmployee(Employee emp){
        //Making a instance of SQLIte database for storing and writing data in it.
        SQLiteDatabase db = this.getWritableDatabase();
        //Creating the ContentValues to store values.
        ContentValues values = new ContentValues();
        //inserting the values now.
        values.put("name",emp.getName());
        values.put("increment",emp.getIncrement());

        long k = db.insert("myemployee",null,values);
        //converting the long k to string.
        Log.d("mytag",Long.toString(k));
        db.close();
    }
        //making a getEmployee method now .
    public void getEmployee(int sno){
        SQLiteDatabase db = this.getReadableDatabase();
        //Making a cursor isko use karke dekhte hai ki hume kya dekhna hai .
        Cursor cursor = db.query("myemployee",new String[]{"sno","name","increment"},
                "sno=?",new String[]{String.valueOf(sno)},
                null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            // making the conditions to see the cursor 1 is sno .
            Log.d("mytag",cursor.getString(1));
            // then seeing the 2 is name now
            Log.d("mytag",cursor.getString(2));
        }
        else{
            Log.d("mytag","some error occured");
        }
    }
}
