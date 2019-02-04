package com.app.fedeturazzini.ualaejercicio.View.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.fedeturazzini.ualaejercicio.R;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {

    public static String BOOK_AUTHOR = "author";
    public static String BOOK_NAME = "name";
    public static String BOOK_POPULARITY = "popularity";
    public static String BOOK_AVAILABLE = "available";
    public static String BOOK_IMAGE = "bookImage";

    private String bookNameDetail;
    private String bookAuthorDetail;
    private Integer bookPopularityDetail;
    private Boolean bookAvailableDetail;
    private String bookImagenSelectedDetail;;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        getIncomingIntents();

        TextView bookName = findViewById(R.id.bookNameTextView);
        TextView bookAuthor = findViewById(R.id.bookAuthor);
        TextView bookPopularity = findViewById(R.id.bookPopularity);
        TextView bookAvailable = findViewById(R.id.bookAvailableTextView);
        ImageView bookImage = findViewById(R.id.bookImage);

        // Seteo info del book
        bookName.setText("Nombre del libro: " + bookNameDetail);
        bookAuthor.setText("Autor del libro: " + bookAuthorDetail);
        bookPopularity.setText(String.valueOf("Popularidad del libro: " + bookPopularityDetail));
        if (bookAvailableDetail) {
            bookAvailable.setText(R.string.available);
            bookAvailable.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAvailable));
        } else {
            bookAvailable.setText(R.string.not_available);
            bookAvailable.setTextColor(getApplicationContext().getResources().getColor(R.color.colorNotAvailable));
        }

        if (!bookImagenSelectedDetail.equals("")) {
            Picasso.get().load(bookImagenSelectedDetail)
                    .resize(300,200)
                    .centerCrop()
                    .placeholder(R.drawable.noimage)
                    .into(bookImage);
        }

        button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getIncomingIntents () {
        Intent intentRecieve = getIntent();
        Bundle bundleRecieve = intentRecieve.getExtras();

        bookNameDetail = bundleRecieve.getString(BOOK_NAME);
        bookAuthorDetail = bundleRecieve.getString(BOOK_AUTHOR);
        bookPopularityDetail = bundleRecieve.getInt(BOOK_POPULARITY);
        bookAvailableDetail = bundleRecieve.getBoolean(BOOK_AVAILABLE);
        bookImagenSelectedDetail = bundleRecieve.getString(BOOK_IMAGE);
    }
}
