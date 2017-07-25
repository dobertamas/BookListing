package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    //private Context mContext;

    BookAdapter(@NonNull Context context, @NonNull ArrayList<Book> books) {
        super(context, 0, books);
        //mContext = context;
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book book = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            Log.d(LOG_TAG, " creating new view ");
        }
        // Lookup view for data population
        TextView titleTextView = convertView.findViewById(R.id.list_item_title);
        TextView authorTextView = convertView.findViewById(R.id.list_item_author);

        // Populate the data into the template view using the data object
        assert book != null;
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());

        // Return the completed view to render on screen
        return convertView;
    }

}
