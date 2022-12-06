package com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces;

import com.libraryproject.librarysystem.domain.Orders;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.thymeleafTypes.EditOrderForm;

import java.util.List;

public interface IOrdersRepositoryWrapper {
    List<Orders> getOrdersForUser(Users user);
    void createAndSaveOrder(String userId, String orderCreatedDate, String orderStatus, List<String> selectedBookIds);
    void finishOrder(int orderId);
}
