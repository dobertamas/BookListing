package com.example.android.booklisting;

class Book {
    private String mTitle;
    private String mAuthor;
    private String[] mAuthors;

    Book(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }

    public Book() {
    }

    public String[] getAuthors() {
        return mAuthors;
    }

    public void setAuthors(String[] authors) {
        mAuthors = authors;
    }

    String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }
}
