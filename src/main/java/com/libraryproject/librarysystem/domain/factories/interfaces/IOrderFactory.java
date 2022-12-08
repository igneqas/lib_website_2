package com.libraryproject.librarysystem.domain.factories.interfaces;

import com.libraryproject.librarysystem.domain.Orders;

import java.util.List;

public interface IOrderFactory {
    Orders createOrder(String userId, String orderCreatedDate, String orderStatus, List<String> selectedBookIds);
}
