package com.example.android.booklisting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.BOOK_LIST_ARRAY);
        Book[] bookList = Arrays.copyOf(parcelables, parcelables.length, Book[].class);

        //Bundle extras = getIntent().getExtras();
        //String[] books = extras.getStringArray("bookList");

      /*  ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("A", "B"));
        bookList.add(new Book("c", "d"));
*/
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
