package com.chaiyaphatcoding.sqliteandroidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";

    MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //@INSERT    add book to DB
    void addBook(String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        //insert to SQLITE
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Added succesfully!", Toast.LENGTH_LONG).show();
        }
    }

    //SELECT    data
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    //Update Data
    void updateData(String row_id,String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        //update ????????? column = _id ????????? ?????? data ??????????????? cv ?????????????????????
        long result = db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Failed to Update.",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Succesfully! Added",Toast.LENGTH_LONG).show();
        }
    }

    //delete Data
    void deleteOnRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =  db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Failed to Delete.",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Succesfully! Deleted",Toast.LENGTH_LONG).show();
        }

    }

    //delete ALL
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
    }



}
