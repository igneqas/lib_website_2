package com.libraryproject.librarysystem.domain;

public class BooksWithCount {

    private Books book;
    private int count;

    public BooksWithCount() {
    }

    public BooksWithCount(Books book, int count) {
        this.book = book;
        this.count = count;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BooksWithCount{" +
                "book=" + book +
                ", count=" + count +
                '}';
    }
}
