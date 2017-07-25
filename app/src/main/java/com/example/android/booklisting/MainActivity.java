package com.example.android.booklisting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    private static final String GOOGLE_SEARCH_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    @InjectView(R.id.search_books_button) Button mSearchBooksButton;
    @InjectView(R.id.enter_search_term) EditText mSearchTermEditText;

    String mSearchTerm;

    public static final String BOOK_LIST_ARRAY = "BOOK_LIST_ARRAY";

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
            }
        });
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, Book[]> {

        final String LOG_TAG = BookAsyncTask.class.getSimpleName();

        @Override protected Book[] doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(GOOGLE_SEARCH_REQUEST_URL + mSearchTerm + "&maxResults=10" + "&key=AIzaSyDzeb_q3BZLDcETAF6xOf6B0B4X_VNNfpE");
            assert url != null;
            Log.i(LOG_TAG, " Url created" + url.getPath());

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem making the HTTP request.", e);
            }

            Book[] bookArray = new Book[10];

            try {
                bookArray = extractFeatureFromJson(jsonResponse);
                Log.i(LOG_TAG, "extracted");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return bookArray;
        }

        /**
         * Send data from ArrayList of books (which was the result of the
         * {@link BookAsyncTask}) to BookListActivity.
         */
        @Override protected void onPostExecute(Book[] books) {

            Intent bookListIntent = new Intent(MainActivity.this, BookListActivity.class);
            bookListIntent.putExtra(BOOK_LIST_ARRAY, books);
            startActivity(bookListIntent);
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String urlString) {
            URL url;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL" + urlString, exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }
                else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an ArrayList of {@link Book} objects by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private Book[] extractFeatureFromJson(String booksJSON) throws JSONException {
            // If the JSON string is empty or null, then return early.
            if (TextUtils.isEmpty(booksJSON)) {
                return null;
            }

            JSONObject baseJsonResponse = new JSONObject(booksJSON);
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");

            Book[] books = new Book[itemsArray.length()];

            for (int x = 0; x < itemsArray.length(); x++) {
                JSONObject bookItem = itemsArray.getJSONObject(x);
                JSONObject volumeInfoObject = bookItem.getJSONObject("volumeInfo");
                Book bookLoopItem = new Book();
                bookLoopItem.setTitle(volumeInfoObject.getString("title"));

                books[x] = bookLoopItem;


            }

            return books;

        }

    }

}
