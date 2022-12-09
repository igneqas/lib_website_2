package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.domain.DTO.OrdersDTO;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.BooksRepositoryWrapper;
import com.libraryproject.librarysystem.utilities.DateFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrdersFactoryTests {

    @Mock
    UsersRepository usersRepository;

    @Mock
    DateFormatter dateFormatter;

    @Mock
    BooksRepositoryWrapper booksRepositoryWrapper;

    @InjectMocks
    OrdersFactory ordersFactory;

    @Test
    public void createFromDTO_shouldCreateOrdersObject() {
        // Arrange
        OrdersDTO ordersDTO = new OrdersDTO(1, new Users(), new ArrayList<>(), new Date(),
                new Date(), OrderStatus.FINISHED);
        // Act
        Orders orders = ordersFactory.createFromDTO(ordersDTO);
        // Assert
        assertEquals(orders.getOrderID(), ordersDTO.getOrderID());
        assertEquals(orders.getUser(), ordersDTO.getUser());
        assertEquals(orders.getBooksList(), ordersDTO.getBooksList());
        assertEquals(orders.getIssueDate(), ordersDTO.getIssueDate());
        assertEquals(orders.getReturnDate(), ordersDTO.getReturnDate());
        assertEquals(orders.getOrderInfo(), ordersDTO.getOrderInfo());
    }

    @Test
    public void createOrder_shouldCreateOrdersObject() {
        // Arrange
        String userId = "1";
        String orderCreatedDate = "2020-10-25";
        String orderStatus = "FINISHED";
        List<String> selectedBookIds = new ArrayList<>();
        selectedBookIds.add("5");
        Users user = new Users(1, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        Date date = new Date();
        Orders expectedObject = new Orders(1, user, new ArrayList<>(), date,
                null, OrderStatus.FINISHED);

        when(usersRepository.getById(anyInt())).thenReturn(user);
        when(dateFormatter.getFormattedDate(anyString())).thenReturn(date);

        // Act
        Orders orders = ordersFactory.createOrder(userId, orderCreatedDate, orderStatus, selectedBookIds);
        // Assert
        assertEquals(expectedObject.getUser(), orders.getUser());
        assertEquals(expectedObject.getBooksList(), orders.getBooksList());
        assertEquals(expectedObject.getIssueDate(), orders.getIssueDate());
        assertEquals(expectedObject.getReturnDate(), orders.getReturnDate());
        assertEquals(expectedObject.getOrderInfo(), orders.getOrderInfo());
    }
}
