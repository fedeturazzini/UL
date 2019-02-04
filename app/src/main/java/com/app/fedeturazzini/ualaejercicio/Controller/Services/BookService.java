package com.app.fedeturazzini.ualaejercicio.Controller.Services;

import com.app.fedeturazzini.ualaejercicio.Model.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    @GET("test/books")
    Call <ArrayList<Book>> getBooks();
}
