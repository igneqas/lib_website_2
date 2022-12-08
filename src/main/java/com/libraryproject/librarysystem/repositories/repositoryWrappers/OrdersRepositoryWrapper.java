package com.libraryproject.librarysystem.repositories.repositoryWrappers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.domain.factories.interfaces.IOrdersFactory;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IOrdersRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.utilities.interfaces.IUserHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrdersRepositoryWrapper implements IOrdersRepositoryWrapper {

    @Autowired
    private IUserHelpers userHelpers;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private IOrdersFactory ordersFactory;

    public List<Orders> getOrdersForUser(Users user) {
        return userHelpers.isUserLibrarian(user) ? ordersRepository.findAll() : ordersRepository.findByUser(user);
    }

    public void createAndSaveOrder(String userId, String orderCreatedDate, String orderStatus, List<String> selectedBookIds) {
        Orders order = ordersFactory.createOrder(userId, orderCreatedDate, orderStatus, selectedBookIds);
        ordersRepository.save(order);
    }

    public void finishOrder(int orderId) {
        Orders order = ordersRepository.getById(orderId);
        order.setReturnDate(new Date());
        for (Books book: order.getBooksList()) {
            book.setAvailability(Availability.AVAILABLE);
        }
        order.setOrderInfo(OrderStatus.FINISHED);
        ordersRepository.save(order);
    }
}
