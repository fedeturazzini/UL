package com.app.fedeturazzini.ualaejercicio.View.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.fedeturazzini.ualaejercicio.Controller.Services.BookServiceProvider;
import com.app.fedeturazzini.ualaejercicio.R;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestBooks();
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

    // Request books
    private void requestBooks() {
        BookServiceProvider bookServiceProvider = new BookServiceProvider();
        bookServiceProvider.getBooks();
    }
}


