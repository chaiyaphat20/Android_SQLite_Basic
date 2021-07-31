package com.chaiyaphatcoding.sqliteandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    TextView text_emptyData;
    ImageView image_emptyData;

    MyDataBaseHelper myDB;
    ArrayList<String> book_id, book_title,book_author,book_pages;

    CustomAdapter customAdapter;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);

        //no data view
        text_emptyData = findViewById(R.id.text_emptyData);
        image_emptyData= findViewById(R.id.image_emptyData);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDataBaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        Log.d("test","1");
        storeDataInArray();



        //เรียกใช้
        customAdapter = new CustomAdapter(MainActivity.this,this,book_id,book_title,book_author,book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    //update activity v1
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray(){
        Log.d("test","2");
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() ==0){
//            Toast.makeText(this,"Nodata",Toast.LENGTH_LONG).show();
            text_emptyData.setVisibility(View.VISIBLE);
            image_emptyData.setVisibility(View.VISIBLE);

        }else{
            Log.d("test","3");
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
            text_emptyData.setVisibility(View.GONE);
            image_emptyData.setVisibility(View.GONE);
        }
    }

    //add menu bar: hamberger menu
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //opteion เมื่อกดที่ menu นั้นๆ
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        Log.d("test",String.valueOf(item.getItemId()));
        if(item.getItemId() == R.id.delete_all){
            confirmDialogToDelete();

        }
        return super.onOptionsItemSelected(item);
    }

    //สร้าง Alert dialog
    void confirmDialogToDelete() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete ALL? ");
            builder.setMessage("Are you sure want to delete ALL ?" );
            //OK
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyDataBaseHelper myDB = new MyDataBaseHelper(MainActivity.this);
                    myDB.deleteAllData();

                    //refresh
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            //NO
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.create().show();


        } catch (Exception e) {
            Log.d("test", e.getMessage());
        }

    }




//update activity v2
//    //Update View When Click Go Back
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        finish();
//        overridePendingTransition(0, 0);
//        startActivity(getIntent());
//        overridePendingTransition(0, 0);
//    }
}