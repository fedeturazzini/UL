package com.app.fedeturazzini.ualaejercicio.Controller.Events;

import com.app.fedeturazzini.ualaejercicio.Model.Book;

import java.util.ArrayList;

public class BookEvents {

    private ArrayList<Book> books;

    public BookEvents(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
