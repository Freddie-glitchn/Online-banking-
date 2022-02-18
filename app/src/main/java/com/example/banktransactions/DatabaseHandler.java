package com.example.banktransactions;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(MainActivity mainActivity) {
        super(mainActivity, "mydatabase", null, 1);
    }

    public void addTransaction(String amount){

        // using getWritableDatabase to do data modification
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO transactions VALUES(?, ?)",new Object[]{amount});
        sqLiteDatabase.close();
    }

    public void deleteTransactions(){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM transactions");
        sqLiteDatabase.close();
    }

    public List<String>getTransactions(){

        //Retrieving all transactions ffrom database
        List<String> transaction = new ArrayList<String>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM transactions", null);

        while (cursor.moveToNext()){

            String transact = cursor.getString(0);
            transaction.add(transact);
        }

        sqLiteDatabase.close();
        return transaction;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE transactions (id PRIMARY KEY, amount VARCHAR(15))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS transactions";
        sqLiteDatabase.close();
        onCreate(sqLiteDatabase);
    }
}
