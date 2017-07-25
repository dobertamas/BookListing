package com.example.android.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {
    private ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookList = new ArrayList<>();
        bookList.add(new Book("A", "B"));
        bookList.add(new Book("c", "d"));

        // Create an {@link BookAdapter}, whose data source is a list of {@link Book}s.
        // The adapter knows how to create list items for each item in the list.
        BookAdapter bookAdapter = new BookAdapter(this, bookList);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_book_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link AttractionAdapter} we created above, so that
        // the {@link ListView} will display list items for each {@link Attraction} in the list.
        listView.setAdapter(bookAdapter);

    }
}
