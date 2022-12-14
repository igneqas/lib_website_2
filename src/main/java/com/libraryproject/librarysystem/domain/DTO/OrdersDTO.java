package com.libraryproject.librarysystem.domain.DTO;

import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.OrderStatus;
import com.libraryproject.librarysystem.domain.Users;

import java.util.Date;
import java.util.List;

public class OrdersDTO {
    private int orderID;
    private Users user;
    private List<Books> booksList;
    private Date issueDate;
    private Date returnDate;
    private OrderStatus orderInfo;

    public OrdersDTO(int orderID, Users user, List<Books> booksList, Date issueDate, Date returnDate, OrderStatus orderInfo) {
        this.orderID = orderID;
        this.user = user;
        this.booksList = booksList;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.orderInfo = orderInfo;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public OrderStatus getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderStatus orderInfo) {
        this.orderInfo = orderInfo;
    }
}
