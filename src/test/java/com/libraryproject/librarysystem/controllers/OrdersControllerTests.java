//package com.libraryproject.librarysystem.controllers;
//
//import com.libraryproject.librarysystem.domain.Books;
//import com.libraryproject.librarysystem.domain.OrderStatus;
//import com.libraryproject.librarysystem.domain.Orders;
//import com.libraryproject.librarysystem.domain.Users;
//import com.libraryproject.librarysystem.repositories.AuthorsRepository;
//import com.libraryproject.librarysystem.repositories.BooksRepository;
//import com.libraryproject.librarysystem.repositories.OrdersRepository;
//import com.libraryproject.librarysystem.repositories.UsersRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.testng.reporters.jq.Model;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(OrdersControllers.class)
//@AutoConfigureMockMvc(addFilters = false)
//public class OrdersControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    Books books;
//
//    @MockBean
//    Model model;
//
//    @MockBean
//    BooksRepository booksRepository;
//
//    @MockBean
//    AuthorsRepository authorsRepository;
//
//    @MockBean
//    UsersRepository usersRepository;
//
//    @MockBean
//    OrdersRepository ordersRepository;
//
//    @Test
//    public void shouldRedirectToOrdersList_ChangedDataIsCorrect() throws Exception {
//        Orders order = new Orders(1, new Users(), new ArrayList<Books>(), new Date(), new Date(), OrderStatus.INPROGRESS);
//        when(ordersRepository.save(any())).thenReturn(order);
//        when(ordersRepository.getById(order.getOrderID())).thenReturn(order);
//        mockMvc.perform(post("/editthisorder"))
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/orderslist"));
//    }
//}
