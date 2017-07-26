package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    BookAdapter(@NonNull Context context, @NonNull Book[] books) {
        super(context, 0, books);
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Book book = getItem(position);

        // Populate the data into the view using the data object
        assert book != null;
        viewHolder.titleTextView.setText(book.getTitle());
        viewHolder.authorTextView.setText(book.getAuthor());

        // Return the completed view to render on screen
        return convertView;
    }

    private class ViewHolder {
        TextView titleTextView;
        TextView authorTextView;

        ViewHolder(View view) {
            this.titleTextView = view.findViewById(R.id.list_item_title);
            this.authorTextView = view.findViewById(R.id.list_item_author);
        }
    }
}
