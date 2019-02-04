package com.app.fedeturazzini.ualaejercicio.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.fedeturazzini.ualaejercicio.Model.Book;
import com.app.fedeturazzini.ualaejercicio.R;
import com.app.fedeturazzini.ualaejercicio.View.Activities.BookDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerBooksAdapter extends RecyclerView.Adapter<RecyclerBooksAdapter.MyViewHolder> {

    private ArrayList<Book> bookArrayList;
    private Context context;

    // Constructor
    public RecyclerBooksAdapter(ArrayList<Book> bookArrayList, Context context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerBooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_books_cell, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBooksAdapter.MyViewHolder myViewHolder, int i) {

        final Book book = bookArrayList.get(i);

        myViewHolder.bookName.setText(book.getNombre());
        myViewHolder.bookAuthor.setText("Autor: " + book.getAutor());
        myViewHolder.bookPopularity.setText("Popularidad: " + String.valueOf(book.getPopularidad()));
        if (book.getDisponibilidad()) {
            myViewHolder.bookAvailable.setText(R.string.available);
            myViewHolder.bookAvailable.setTextColor(context.getResources().getColor(R.color.colorAvailable));
        } else {
            myViewHolder.bookAvailable.setText(R.string.not_available);
            myViewHolder.bookAvailable.setTextColor(context.getResources().getColor(R.color.colorNotAvailable));
        }

        if (!book.getImagen().equals("")) {
            Picasso.get().load(book.getImagen())
                    .resize(200,200)
                    .centerCrop()
                    .into(myViewHolder.bookImage);
        }

        myViewHolder.linearLayoutCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(BookDetailActivity.BOOK_AUTHOR, book.getAutor());
                bundle.putBoolean(BookDetailActivity.BOOK_AVAILABLE, book.getDisponibilidad());
                bundle.putString(BookDetailActivity.BOOK_NAME, book.getNombre());
                bundle.putInt(BookDetailActivity.BOOK_POPULARITY, book.getPopularidad());
                bundle.putString(BookDetailActivity.BOOK_IMAGE, book.getImagen());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    // View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView bookName;
        public TextView bookAuthor;
        public TextView bookPopularity;
        public TextView bookAvailable;
        public ImageView bookImage;
        public LinearLayout linearLayoutCell;

        
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookNameTextView);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookPopularity = itemView.findViewById(R.id.bookPopularity);
            bookAvailable = itemView.findViewById(R.id.bookAvailableTextView);
            bookImage = itemView.findViewById(R.id.bookImage);
            linearLayoutCell = itemView.findViewById(R.id.linearLayoutCell);
        }
    }
}
