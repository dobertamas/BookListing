package com.example.android.booklisting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.search_books_button) Button mSearchBooksButton;
    @InjectView(R.id.enter_search_term) EditText mSearchTermEditText;

    String mSearchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mSearchBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {


                mSearchTerm = mSearchTermEditText.getText().toString().trim();
                if (mSearchTerm.isEmpty()) {
                    return;
                }
                // Kick off an {@link AsyncTask} to perform the network request
                BookAsyncTask task = new BookAsyncTask();
                task.execute();

                Intent bookListIntent = new Intent(MainActivity.this, BookListActivity.class);
                startActivity(bookListIntent);
            }
        });
    }

    // https://www.google.com/#q=soccer
    private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Book>> {

        @Override protected ArrayList<Book> doInBackground(URL... urls) {
            ArrayList<Book> bookList = new ArrayList<>();
            return bookList;
        }
    }
}
