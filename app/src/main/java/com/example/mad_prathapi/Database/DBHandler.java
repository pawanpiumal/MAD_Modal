package com.example.mad_prathapi.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "prathapi.db";

    public DBHandler(Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_ENTRIES ="CREATE TABLE "+UserProfile.Users.TABLE_NAME + " (" +
                UserProfile.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                UserProfile.Users.COLUMN_NAME_USERNAME + " TEXT ,"+
                UserProfile.Users.COLUMN_NAME_DATEOFBIRTH + " TEXT, "+
                UserProfile.Users.COLUMN_NAME_GENDER  + " TEXT)";


        sqLiteDatabase.execSQL(CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ UserProfile.Users.TABLE_NAME);
        onCreate(sqLiteDatabase);


    }


    public boolean addInfo(String Username, String DOB, String Gender){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues =  new ContentValues();

        contentValues.put(UserProfile.Users.COLUMN_NAME_USERNAME,Username);
        contentValues.put(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH,DOB);
        contentValues.put(UserProfile.Users.COLUMN_NAME_GENDER,Gender);


        long id = db.insert(UserProfile.Users.TABLE_NAME,
                null,
                contentValues
                );
        db.close();

        if(id < 0){
            return false;
        }else{
            return true;
        }
    }


    public boolean updateInfo( String Username, String DOB, String Gender ){
        SQLiteDatabase db = getWritableDatabase();

        Log.i("Update DB1", Username);
        Log.i("Update DB2", DOB);Log.i("Update DB3", Gender);


        ContentValues contentValues =  new ContentValues();

        contentValues.put(UserProfile.Users.COLUMN_NAME_USERNAME,Username);
        contentValues.put(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH,DOB);
        contentValues.put(UserProfile.Users.COLUMN_NAME_GENDER,Gender);


        int count = db.update(UserProfile.Users.TABLE_NAME, contentValues,
                UserProfile.Users.COLUMN_NAME_USERNAME + " = ?",
                new String[]{Username});

        if(count < 1){
            return false;
        }else{
            return true;
        }

    }


    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(UserProfile.Users.TABLE_NAME,
                new String[]{UserProfile.Users.COLUMN_NAME_USERNAME},
                null,
                null,
                null,
                null,
                UserProfile.Users.COLUMN_NAME_USERNAME +  " ASC");
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME));
            list.add(username);
        }
        return list;
    }

    public ContentValues readinfo(String username){
        SQLiteDatabase db = getReadableDatabase();

        Log.i("Update DB", username);
        Cursor cursor = db.query(UserProfile.Users.TABLE_NAME,
                new String[]{UserProfile.Users.COLUMN_NAME_USERNAME, UserProfile.Users.COLUMN_NAME_GENDER, UserProfile.Users.COLUMN_NAME_DATEOFBIRTH},
                UserProfile.Users.COLUMN_NAME_USERNAME + " = ? ",
                new String[]{username},
                null,
                null,
                null);


        ContentValues contentValues = new ContentValues();


        while (cursor.moveToNext()) {

            contentValues.put("u", cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_USERNAME)));
            contentValues.put("d", cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DATEOFBIRTH)));
            contentValues.put("g", cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_GENDER)));
        }
        return contentValues;
    }

    public void deleteInfo(String username){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(UserProfile.Users.TABLE_NAME,
                UserProfile.Users.COLUMN_NAME_USERNAME + "=?",
                new String[]{username});

    }

}
