package com.example.revesiontest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.revesiontest.Model.User;

public class DatabaseConnection extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "UserIN4";
    private static final int DATABASE_VERSION = 1;
    public static final String TB_NAME = "User";
    public static final String COL_ID = "Id";
    public static final String COL_USER = "Username";
    public static final String COL_PASS = "Password";
    public DatabaseConnection(@Nullable Context context){
        super(context,DB_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TB_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_USER + " TEXT, " + COL_PASS + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
    public long insertData(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER, u.getUsername().toString());
        values.put(COL_PASS, u.getPassword().toString());

        long newRowId = db.insert(TB_NAME, null, values);
        return newRowId;
    }
}
