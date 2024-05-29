package com.example.revesiontest.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.revesiontest.Database.DatabaseConnection;
import com.example.revesiontest.Model.User;

import java.util.ArrayList;

public class UserRepository {
    private Context context;
    private static ArrayList<User> userData  = new ArrayList<>();
    SQLiteDatabase myDB;
    public UserRepository(Context context){
        this.context = context;
        DatabaseConnection databaseConnection = new DatabaseConnection(context);
        myDB = databaseConnection.open();
    }
    public static ArrayList<User> getUserData() {
        return userData;
    }
    public static void setUserData(ArrayList<User> userData) {
        UserRepository.userData = userData;
    }
    public void removeUser(User u){
        userData.remove(u);
    }
    public void addUser(User u){
        DatabaseConnection databaseConnection = new DatabaseConnection(context);
        databaseConnection.open();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseConnection.COL_USER,u.getUsername());
        cv.put(DatabaseConnection.COL_PASS,u.getPassword());
        long result = myDB.insert(DatabaseConnection.TB_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkifexist(User u){
        String query = "SELECT * FROM " + DatabaseConnection.TB_NAME;
        Cursor cursor = myDB.rawQuery(query,null);
        if(cursor.getCount() == 0){
            Toast.makeText(this.context,"No data...",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                userData.add(new User(cursor.getString(1),cursor.getString(2)));
            }
        }

        for (User user: userData) {
            if (user.getUsername().toString().equals(u.getUsername().toString()) &&
                    user.getPassword().toString().equals(u.getPassword().toString())){
                return true;
            }
        }
        return false;
    }
}