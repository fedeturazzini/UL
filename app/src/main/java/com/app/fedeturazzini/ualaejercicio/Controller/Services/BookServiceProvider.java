package com.app.fedeturazzini.ualaejercicio.Controller.Services;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.app.fedeturazzini.ualaejercicio.Controller.Events.BookEvents;
import com.app.fedeturazzini.ualaejercicio.Model.Book;
import com.app.fedeturazzini.ualaejercicio.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookServiceProvider {

    private static final String BASE_URL = "https://qodyhvpf8b.execute-api.us-east-1.amazonaws.com/";

    private Retrofit retrofit;
    private Context context;

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void getBooks () {

        BookService bookService = getRetrofit().create(BookService.class);
        Call<ArrayList<Book>> booksCall = bookService.getBooks();
        booksCall.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Book> books = response.body();
                    EventBus.getDefault().post(new BookEvents(books));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                Toast.makeText(context, Resources.getSystem().getString(R.string.conection_error), Toast.LENGTH_LONG).show();
            }
        });
    }
}
