package com.chaiyaphatcoding.sqliteandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText title_input, author_input, page_input;
    Button addPage_btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        page_input = findViewById(R.id.page_input);
        addPage_btn_add = findViewById(R.id.addPage_btn_add);
        addPage_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString(), author_input.getText().toString(), Integer.parseInt(page_input.getText().toString().trim()));
            }
        });
    }
}