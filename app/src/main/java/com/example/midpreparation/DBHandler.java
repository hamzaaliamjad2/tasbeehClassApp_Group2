package com.example.midpreparation;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasbeehCounter.db";
    private static final String TABLE_NAME = "UserData";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Date = "Date";
    private static final String COLUMN_countDarood = "countDarood";
    private static final String COLUMN_countKalma = "countKalma";
    private static final  String COLUMN_countAstaghfar="countAstaghfar";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Date + " TEXT, "
                + COLUMN_countDarood + " INTEGER, "
                + COLUMN_countKalma  + " INTEGER, "
                + COLUMN_countAstaghfar+ " INTEGER "
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(UserData user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_Date,user.getDate().toString());
        values.put(COLUMN_countDarood, user.getCountDarood());
        values.put(COLUMN_countKalma, user.getCountKalma());
        values.put(COLUMN_countAstaghfar, user.getCountAstaghfar());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<UserData> selectAllUsers(String date) {
        List<UserData> users = new ArrayList<>();
        date="'"+date+"'";
        String sql = "SELECT * FROM " + TABLE_NAME+" WHERE Date = "+date;
        //String sql = "SELECT * FROM " + TABLE_NAME +" where Date = "+date;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        /*
        * if (cursorCourses.moveToFirst()) {
            do {
                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                      cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());
        }
        * */

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") int daroodCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_countDarood)));
                @SuppressLint("Range") int kalmaCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_countKalma)));
                @SuppressLint("Range") int astaghfarCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_countAstaghfar)));



                users.add(new UserData(LocalDate.now().toString(),daroodCount, kalmaCount, astaghfarCount));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }
    public List<UserData> selectAllUsers() {
        List<UserData> users = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        //String sql = "SELECT * FROM " + TABLE_NAME +" where Date = "+date;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        /*
        * if (cursorCourses.moveToFirst()) {
            do {
                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                      cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());
        }
        * */

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") int daroodCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_countDarood)));
                @SuppressLint("Range") int kalmaCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_countKalma)));
                @SuppressLint("Range") int astaghfarCount = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_countAstaghfar)));



                users.add(new UserData(LocalDate.now().toString(),daroodCount, kalmaCount, astaghfarCount));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return users;
    }
}