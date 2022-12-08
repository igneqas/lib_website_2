package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.DTO.OrdersDTO;
import com.libraryproject.librarysystem.domain.OrderStatus;
import com.libraryproject.librarysystem.domain.Orders;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.domain.factories.interfaces.IOrdersFactory;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.BooksRepositoryWrapper;
import com.libraryproject.librarysystem.utilities.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersFactory implements IOrdersFactory {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private BooksRepositoryWrapper booksRepositoryWrapper;

    @Autowired
    private DateFormatter dateFormatter;

    public Orders createOrder(String userId, String orderCreatedDate, String orderStatus, List<String> selectedBookIds) {
        Users orderOwner = usersRepository.getById(Integer.parseInt(userId));
        Date formattedOrderCreatedDate = dateFormatter.getFormattedDate(orderCreatedDate);
        List<Books> booksList = booksRepositoryWrapper.getReservedBooksFromIds(selectedBookIds);
        OrderStatus formattedOrderStatus = getOrderStatusFromString(orderStatus);
        return new Orders(orderOwner, formattedOrderCreatedDate, booksList, formattedOrderStatus);
    }

    private OrderStatus getOrderStatusFromString(String orderStatus) {
        if (orderStatus.equals("reserved")) {
            return OrderStatus.RESERVED;
        } else if (orderStatus.equals("inprogress")) {
            return OrderStatus.INPROGRESS;
        } else {
            return OrderStatus.FINISHED;
        }
    }
    @Override
    public Orders createFromDTO(OrdersDTO ordersDTO) {
        return new Orders(ordersDTO.getOrderID(),
                ordersDTO.getUser(),
                ordersDTO.getBooksList(),
                ordersDTO.getIssueDate(),
                ordersDTO.getReturnDate(),
                ordersDTO.getOrderInfo());
    }
}
