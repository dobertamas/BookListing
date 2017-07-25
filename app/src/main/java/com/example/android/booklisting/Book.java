package com.example.android.booklisting;

class Book {
    private String mTitle;
    private String mAuthor;

    Book(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }

    String getTitle() {
        return mTitle;
    }

   /* public void setTitle(String title) {
        mTitle = title;
    }*/

    String getAuthor() {
        return mAuthor;
    }

    /*public void setAuthor(String author) {
        mAuthor = author;
    }*/
}
