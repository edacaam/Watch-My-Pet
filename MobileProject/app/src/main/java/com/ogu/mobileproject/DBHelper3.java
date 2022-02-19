package com.ogu.mobileproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper3 extends SQLiteOpenHelper {
    public static final String DBNAME = "Profile.db";
    public DBHelper3(Context context) {
        super(context, "Profile.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String query = "CREATE TABLE profile ( _id INTEGER PRIMARY KEY, name TEXT, email TEXT, phone TEXT, address TEXT, rozet1 INTEGER, rozet2 INTEGER, rozet3 INTEGER, rozet4 INTEGER, rozet5 INTEGER, rozet6 INTEGER, rozet7 INTEGER, rozet8 INTEGER, rozet9 INTEGER, rozet10 INTEGER);";
        MyDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(int id,String name, String email,String phone,String address,int rozet1,int rozet2,int rozet3,int rozet4,int rozet5,int rozet6,int rozet7,int rozet8,int rozet9,int rozet10){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("_id", id);
        contentValues.put("name", name);
        contentValues.put("email",email);
        contentValues.put("phone", phone);
        contentValues.put("address",address);
        contentValues.put("rozet1",rozet1);
        contentValues.put("rozet2",rozet2);
        contentValues.put("rozet3",rozet3);
        contentValues.put("rozet4",rozet4);
        contentValues.put("rozet5",rozet5);
        contentValues.put("rozet6",rozet6);
        contentValues.put("rozet7",rozet7);
        contentValues.put("rozet8",rozet8);
        contentValues.put("rozet9",rozet9);
        contentValues.put("rozet10",rozet10);
        long result = MyDB.insert("profile", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Cursor readProfile(int id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from profile where _id = ?", new String[] {String.valueOf(id)});
        if(cursor.moveToFirst())
            return cursor;
        else
            return null;
    }
    void deleteOneRow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("profile", "_id=?", new String[]{String.valueOf(id)});
    }

}

