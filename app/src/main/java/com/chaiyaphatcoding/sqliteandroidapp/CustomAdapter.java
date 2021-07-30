package com.chaiyaphatcoding.sqliteandroidapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList book_id, book_title, book_author, book_pages;

    public CustomAdapter(Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        // set ค่า ไปที่หน้านั้นๆ
        holder.book_id_text.setText(String.valueOf(book_id.get(position)));
        holder.book_title_text.setText(String.valueOf(book_title.get(position)));
        holder.book_author_text.setText(String.valueOf(book_author.get(position)));
        holder.book_pages_text.setText(String.valueOf(book_pages.get(position)));
    }

    @Override
    public int getItemCount() {
        Log.d("test",String.valueOf(book_title.toString()));
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //binding กหับ layout
        TextView book_id_text, book_title_text, book_author_text,book_pages_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_text = itemView.findViewById(R.id.book_id_text);
            book_title_text = itemView.findViewById(R.id.book_title_text);
            book_author_text = itemView.findViewById(R.id.book_author_text);
            book_pages_text = itemView.findViewById(R.id.book_pages_text);
        }
    }
}
