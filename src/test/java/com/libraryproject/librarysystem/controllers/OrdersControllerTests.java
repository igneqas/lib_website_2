package com.libraryproject.librarysystem.controllers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.domain.DTO.OrdersDTO;
import com.libraryproject.librarysystem.domain.factories.interfaces.IOrdersFactory;
import com.libraryproject.librarysystem.repositories.AuthorsRepository;
import com.libraryproject.librarysystem.repositories.BooksRepository;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IBooksRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IOrdersRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IUsersRepositoryWrapper;
import com.libraryproject.librarysystem.thymeleafTypes.EditOrderForm;
import com.libraryproject.librarysystem.utilities.interfaces.IModelHelpers;
import com.libraryproject.librarysystem.utilities.interfaces.IUserHelpers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(OrdersControllers.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrdersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    Books books;

    @MockBean
    Model model;

    @MockBean
    BooksRepository booksRepository;

    @MockBean
    AuthorsRepository authorsRepository;

    @MockBean
    UsersRepository usersRepository;

    @MockBean
    OrdersRepository ordersRepository;

    @MockBean
    IOrdersFactory ordersFactory;

    @MockBean
    IUsersRepositoryWrapper usersRepositoryWrapper;

    @MockBean
    IBooksRepositoryWrapper booksRepositoryWrapper;

    @MockBean
    IModelHelpers modelHelpers;

    @MockBean
    IOrdersRepositoryWrapper ordersRepositoryWrapper;

    @MockBean
    IUserHelpers userHelpers;

    @Test
    public void openOrdersListPage_shouldOpen() throws Exception {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Orders order = new Orders(1, new Users(), booksList, new Date(), new Date(), OrderStatus.INPROGRESS);
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(order);
        when(ordersRepositoryWrapper.getOrdersForUser(any())).thenReturn(ordersList);
        when(userHelpers.getCurrentUser()).thenReturn(user);

        // Act and assert
        mockMvc.perform(get("/orderslist"))
                .andExpect(MockMvcResultMatchers.view().name("orderslist.html"));
    }

    @Test
    public void openNewOrderPage_shouldOpen() throws Exception {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        List<Users> usersList = new ArrayList<>();
        usersList.add(user);
        when(usersRepositoryWrapper.getAllUsers()).thenReturn(usersList);
        when(booksRepositoryWrapper.getAllBooks()).thenReturn(booksList);

        // Act and assert
        mockMvc.perform(get("/addneworder"))
                .andExpect(MockMvcResultMatchers.view().name("addneworder.html"));
    }

    @Test
    public void addNewOrder_shouldRedirectToBooksList() throws Exception {
        // Act and assert
        mockMvc.perform(post("/addthisneworder").param("userId", "1").param("issueDate", "2022-10-25")
                        .param("orderInfo", "RESERVED").param("bookIds", ""))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/orderslist"));
    }

    @Test
    public void openOrderInformationPage_shouldOpen() throws Exception {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        Orders order = new Orders(1, user, booksList, new Date(), new Date(), OrderStatus.INPROGRESS);
        when(ordersRepository.getById(anyInt())).thenReturn(order);
        when(userHelpers.getCurrentUser()).thenReturn(user);

        // Act and assert
        mockMvc.perform(get("/vieworder/1"))
                .andExpect(MockMvcResultMatchers.view().name("infooneorder.html"));
    }

    @Test
    public void openEditOrderPage_shouldOpen() throws Exception {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        Orders order = new Orders(1, user, booksList, new Date(), new Date(), OrderStatus.INPROGRESS);
        EditOrderForm editOrderForm = new EditOrderForm(order, booksList.toString());
        when(modelHelpers.createEditOrderForm(anyInt())).thenReturn(editOrderForm);
        when(userHelpers.getCurrentUser()).thenReturn(user);

        // Act and assert
        mockMvc.perform(get("/vieworder/edit/1"))
                .andExpect(MockMvcResultMatchers.view().name("editorder.html"));
    }

    @Test
    public void finishOrder_shouldRedirectToBooksList() throws Exception {
        // Act and assert
        mockMvc.perform(post("/vieworder/finish/1"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/orderslist"));
    }

    @Test
    public void deleteOrder_shouldRedirectToBooksList() throws Exception {
        // Act and assert
        mockMvc.perform(get("/vieworder/delete/1"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/orderslist"));
    }
}
