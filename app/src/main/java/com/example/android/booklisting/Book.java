package com.example.android.booklisting;

import android.os.Parcel;
import android.os.Parcelable;

class Book implements Parcelable {
    private String mTitle;
    private String mAuthor;

    Book() {
    }

    String getTitle() {
        return mTitle;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    String getAuthor() {
        return mAuthor;
    }

    void setAuthor(String author) {
        mAuthor = author;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mAuthor);
    }

    private Book(Parcel in) {
        mTitle = in.readString();
        mAuthor = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}
