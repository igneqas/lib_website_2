package com.libraryproject.librarysystem.thymeleafTypes;

import com.libraryproject.librarysystem.domain.Orders;

public class EditOrderForm {
    private Orders order;
    private String books;

    public EditOrderForm(Orders order, String books) {
        this.order = order;
        this.books = books;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }
}
