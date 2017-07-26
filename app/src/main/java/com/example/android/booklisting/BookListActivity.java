package com.example.android.booklisting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BookListActivity extends AppCompatActivity {

    @InjectView(R.id.list) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.BOOK_LIST_ARRAY);
        Book[] bookList = Arrays.copyOf(parcelables, parcelables.length, Book[].class);

        // Create an {@link BookAdapter}, whose data source is a list of {@link Book}s.
        // The adapter knows how to create list items for each item in the list.
        BookAdapter bookAdapter = new BookAdapter(this, bookList);

        // Make the {@link ListView} use the {@link AttractionAdapter} we created above, so that
        // the {@link ListView} will display list items for each {@link Attraction} in the list.
        mListView.setAdapter(bookAdapter);
    }

}
