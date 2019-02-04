package com.app.fedeturazzini.ualaejercicio.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.fedeturazzini.ualaejercicio.Controller.Events.BookEvents;
import com.app.fedeturazzini.ualaejercicio.Controller.Services.BookServiceProvider;
import com.app.fedeturazzini.ualaejercicio.Model.Book;
import com.app.fedeturazzini.ualaejercicio.R;
import com.app.fedeturazzini.ualaejercicio.View.Adapters.RecyclerBooksAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerBookView;
    private RecyclerBooksAdapter recyclerBooksAdapter;

    private ArrayList<Book> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestBooks();
        recyclerBookView = findViewById(R.id.recyclerViewBooks);
    }

    // Event Bus
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBooksEvents(BookEvents books) {
        bookArrayList = books.getBooks();
        // Ordeno los libros de mayor a menor
        Collections.sort(bookArrayList, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return Integer.valueOf(book2.getPopularidad().compareTo(book1.getPopularidad()));
            }
        });
        recyclerBooksAdapter = new RecyclerBooksAdapter(bookArrayList, getApplicationContext());
        recyclerBookView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerBookView.setAdapter(recyclerBooksAdapter);
    };

    // Request books
    private void requestBooks() {
        BookServiceProvider bookServiceProvider = new BookServiceProvider();
        bookServiceProvider.getBooks();
    }
}


