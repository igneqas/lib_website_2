package com.libraryproject.librarysystem.domain.factories.interfaces;

import com.libraryproject.librarysystem.domain.DTO.OrdersDTO;
import com.libraryproject.librarysystem.domain.Orders;

import java.util.List;

public interface IOrdersFactory {
    Orders createOrder(String userId, String orderCreatedDate, String orderStatus, List<String> selectedBookIds);
    Orders createFromDTO(OrdersDTO ordersDTO);
}
