package com.libraryproject.librarysystem.domain.DTO;

import com.libraryproject.librarysystem.domain.AccessLevel;
import com.libraryproject.librarysystem.domain.Orders;

import java.util.List;

public class UsersDTO {
    private int userID;
    private String userName;
    private String password;
    private String userFullName;
    private String phone;
    private String email;
    private AccessLevel accessLevel;
    private List<Orders> myOrders;

    public UsersDTO(int userID, String userName, String password, String userFullName, String phone, String email, AccessLevel accessLevel, List<Orders> myOrders) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userFullName = userFullName;
        this.phone = phone;
        this.email = email;
        this.accessLevel = accessLevel;
        this.myOrders = myOrders;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public List<Orders> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<Orders> myOrders) {
        this.myOrders = myOrders;
    }
}
