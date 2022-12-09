package com.libraryproject.librarysystem.repositories.repositoryWrappers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.domain.factories.OrdersFactory;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.utilities.UserHelpers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrdersRepositoryWrapperTests {

    @Mock
    OrdersRepository ordersRepository;

    @Mock
    UserHelpers userHelpers;

    @Mock
    OrdersFactory ordersFactory;

    @InjectMocks
    OrdersRepositoryWrapper ordersRepositoryWrapper;

    @Test
    public void getOrdersForUser_userIsLibrarian_shouldReturnOrders() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.LIBRARIAN);
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Orders order = new Orders(1, new Users(), booksList, new Date(), new Date(), OrderStatus.INPROGRESS);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(ordersRepository.findAll()).thenReturn(ordersList);
        when(userHelpers.isUserLibrarian(any())).thenReturn(true);

        // Act
        List<Orders> orders = ordersRepositoryWrapper.getOrdersForUser(user);

        // Assert
        assertEquals(ordersList, orders);
    }

    @Test
    public void getOrdersForUser_userIsClient_shouldReturnOrders() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Orders order = new Orders(1, new Users(), booksList, new Date(), new Date(), OrderStatus.INPROGRESS);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);

        when(ordersRepository.findByUser(any())).thenReturn(ordersList);
        when(userHelpers.isUserLibrarian(any())).thenReturn(false);

        // Act
        List<Orders> orders = ordersRepositoryWrapper.getOrdersForUser(user);

        // Assert
        assertEquals(ordersList, orders);
    }

    @Test
    public void createAndSaveOrder_shouldCreateAndSaveOrder() {
        // Arrange
        String userId = "1";
        String orderCreatedDate = "2020-10-25";
        String orderStatus = "FINISHED";
        List<String> selectedBookIds = new ArrayList<>();
        selectedBookIds.add("5");

        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Orders order = new Orders(1, new Users(), booksList, new Date(), new Date(), OrderStatus.INPROGRESS);

        when(ordersFactory.createOrder(anyString(), anyString(), anyString(), anyList())).thenReturn(order);

        // Act
        ordersRepositoryWrapper.createAndSaveOrder(userId, orderCreatedDate, orderStatus, selectedBookIds);
    }

    @Test
    public void finishOrder_shouldFinishOrder() {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Orders order = new Orders(1, new Users(), booksList, new Date(), new Date(), OrderStatus.INPROGRESS);

        when(ordersRepository.getById(any())).thenReturn(order);

        // Act
        ordersRepositoryWrapper.finishOrder(1);
    }
}
