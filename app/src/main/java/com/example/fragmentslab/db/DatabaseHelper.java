package com.example.fragmentslab.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Pizzeria.db";
    public static final int SCHEMA = 1;
    public static final String TABLE = "PIZZA_ORDER";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CUSTOMER = "CUSTOMER";
    public static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String COLUMN_PIZZA_NAME = "PIZZA_NAME";
    public static final String COLUMN_SPICY = "SPICY";
    public static final String COLUMN_ADD_COLA = "ADD_COLA";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (\n" +
                COLUMN_ID + "             INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                COLUMN_CUSTOMER + "       STRING (255) NOT NULL,\n" +
                COLUMN_PHONE_NUMBER + "   STRING (15)  NOT NULL,\n" +
                COLUMN_PIZZA_NAME + "     STRING (255) NOT NULL,\n" +
                COLUMN_SPICY + "          STRING (40)  NOT NULL,\n" +
                COLUMN_ADD_COLA + "       STRING (40)  NOT NULL\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
