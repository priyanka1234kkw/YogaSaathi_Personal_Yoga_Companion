package com.example.yoga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ImageDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "yoga_images.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "images";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PATH = "path";

    public ImageDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PATH + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertImagePath(String path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATH, path);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> getAllImagePaths() {
        ArrayList<String> paths = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT path FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                paths.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return paths;
    }
}
