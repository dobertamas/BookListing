package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    private Context mContext;

    /* public BookAdapter(@NonNull Context context, @NonNull Object[] objects) {
         super(context, objects);
     }*/
    public BookAdapter(@NonNull Context context, @NonNull ArrayList<Book> books) {
        super(context, 0, books);
        mContext = context;
    }

   /* private static class ViewHolder {
        TextView titleTextView;
        TextView authorTextView;

        ViewHolder(View view) {
            // Find individual views that we want to modify in the list item layout
            titleTextView = (TextView) view.findViewById(R.id.list_item_title);
            authorTextView = (TextView) view.findViewById(R.id.list_item_author);
        }
    }*/

 /*   @Override public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
    }*/

    @NonNull @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book book = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView titleTextView = (TextView) convertView.findViewById(R.id.list_item_title);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.list_item_author);

        // Populate the data into the template view using the data object
        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());

        // Return the completed view to render on screen
        return convertView;
    }

}
