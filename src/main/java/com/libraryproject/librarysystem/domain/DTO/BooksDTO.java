package com.libraryproject.librarysystem.domain.DTO;

import com.libraryproject.librarysystem.domain.Authors;
import com.libraryproject.librarysystem.domain.Availability;
import com.libraryproject.librarysystem.domain.Genre;
import com.libraryproject.librarysystem.domain.Orders;

import java.util.List;

public class BooksDTO {
    private int id;
    private String title;
    private List<Authors> authorsList;
    private Genre genre;
    private String isbn;
    private String releaseYear;
    private String description;
    private String url;
    private Availability availability;
    private List<Orders> bookOrders;

    public BooksDTO(int id, String title, List<Authors> authorsList, Genre genre, String isbn, String releaseYear, String description, String url, Availability availability, List<Orders> bookOrders) {
        this.id = id;
        this.title = title;
        this.authorsList = authorsList;
        this.genre = genre;
        this.isbn = isbn;
        this.releaseYear = releaseYear;
        this.description = description;
        this.url = url;
        this.availability = availability;
        this.bookOrders = bookOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Authors> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Authors> authorsList) {
        this.authorsList = authorsList;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<Orders> getBookOrders() {
        return bookOrders;
    }

    public void setBookOrders(List<Orders> bookOrders) {
        this.bookOrders = bookOrders;
    }
}
