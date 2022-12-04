package com.libraryproject.librarysystem.domain;

public class Report {

    private Books popularBooks;

    public Report() {
    }

    public Report(Books popularBooks) {
        this.popularBooks = popularBooks;
    }

    public Books getPopularBooks() {
        return popularBooks;
    }

    public void setPopularBooks(Books popularBooks) {
        this.popularBooks = popularBooks;
    }
}
