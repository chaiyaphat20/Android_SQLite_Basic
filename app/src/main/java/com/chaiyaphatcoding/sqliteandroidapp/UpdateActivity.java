package com.chaiyaphatcoding.sqliteandroidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private Context context;

    EditText Update_Edt_title, Update_Edt_author, Update_Edt_page;
    Button Update_Btn_update;
    String id, title, pages, author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        context = getApplicationContext();
        Update_Edt_title = findViewById(R.id.Update_Edt_title);
        Update_Edt_author = findViewById(R.id.Update_Edt_author);
        Update_Edt_page = findViewById(R.id.Update_Edt_page);
        Update_Btn_update = findViewById(R.id.Update_Btn_update);

        //First We Call This
        getAndIntentData();

        //set Action bar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        Update_Btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //And only then we call this
                MyDataBaseHelper myDb = new MyDataBaseHelper(context);

                //get value from activity
                title = Update_Edt_title.getText().toString().trim();
                author = Update_Edt_author.getText().toString().trim();
                pages = Update_Edt_page.getText().toString().trim();

                //set value
                myDb.updateData(id, title, author, pages);
            }
        });

    }

    private void getAndIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("pages")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent Data
            Update_Edt_title.setText(id);
            Update_Edt_title.setText(title);
            Update_Edt_author.setText(author);
            Update_Edt_page.setText(pages);

        } else {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
    }
}