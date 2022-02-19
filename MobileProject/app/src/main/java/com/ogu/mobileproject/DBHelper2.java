package com.ogu.mobileproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DBNAME = "Requests.db";
    public DBHelper2(Context context) {
        super(context, "Requests.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String query = "CREATE TABLE requests ( _id INTEGER PRIMARY KEY AUTOINCREMENT,userid INTEGER, startdate TEXT, enddate TEXT, animal TEXT, address TEXT,type INTEGER,price TEXT,active INTEGER,resuserid INTEGER);";
        MyDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(int userid,String startDate,String endDate,String animal,String address,int type,String price,int active,int resuserid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("userid", userid);
        contentValues.put("startdate", startDate);
        contentValues.put("enddate",endDate);
        contentValues.put("animal", animal);
        contentValues.put("address",address);
        contentValues.put("type",type);
        contentValues.put("price",price);
        contentValues.put("active",active);
        contentValues.put("resuserid",resuserid);
        long result = MyDB.insert("requests", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    void deleteOneRow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("requests", "_id=?", new String[]{String.valueOf(id)});
    }

    public void updateRequest(int id,int resID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE requests SET active=0,resuserid="+resID+" where _id="+id);

    }
    public void cancelRequest(int id,int resID){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        MyDB.execSQL("UPDATE requests SET active=1 where _id="+id);
        readReservation(resID);
    }

    Cursor readHostData(){
        String query = "SELECT * FROM requests WHERE type = 1 and active = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readOwnerData(){
        String query = "SELECT * FROM requests WHERE type = 0 and active = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor readReservation(int resID){
        String query = "SELECT * FROM requests WHERE active=0 and resuserid="+resID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor FilterData(String startDate,String endDate, String city,String animal,int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from requests where DATE(startdate) <= DATE(?) and DATE(enddate) >= ? and address = ? and animal = ? and type = 1 and active = 1 and userid != ?", new String[] {startDate,endDate,city,animal,String.valueOf(id)});
        }
        return cursor;
    }

    Cursor FilterHostArayanlarData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("Select * from requests where type = 0 and active = 1 and userid != ?", new String[] {String.valueOf(id)});
        }
        return cursor;
    }

}

