//package com.libraryproject.librarysystem.controllers;
//
//import com.libraryproject.librarysystem.domain.*;
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
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ReportController.class)
//@AutoConfigureMockMvc(addFilters = false)
//public class ReportControllerTests {
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
//    public void generateFullReport_DateMissing_ReturnsError() throws Exception {
//        mockMvc.perform(get("/generateReportFull").param("fromDate", "2022-11-10"))
//                .andExpect(MockMvcResultMatchers.view().name("error.html"))
//                .andExpect(MockMvcResultMatchers.model().attribute("errorMessage", "Missing fromDate or toDate."));
//    }
//
//    @Test
//    public void generateFullReport_DatesSelected_ReturnsFullReport() throws Exception {
//        Users userOne = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
//        Users userTwo = new Users(7, "Alex A", "MM", "Alex A", "email@gmail.com", "Password", AccessLevel.CLIENT);
//        List<Users> usersList = new ArrayList<>();
//        usersList.add(userOne);
//        usersList.add(userTwo);
//        Authors author = new Authors(6, "France", "Marcel Proust");
//        List<Authors> authorsList = new ArrayList<>();
//        authorsList.add(author);
//        Books bookOne = new Books(1, "Don Quixote", authorsList, Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
//        Books bookTwo = new Books(2, "Book name", authorsList, Genre.ADVENTURE, "1-4526-859253", "1986", "Second book", "", Availability.AVAILABLE, new ArrayList<>());
//        List<Books> booksList = new ArrayList<>();
//        booksList.add(bookOne);
//        booksList.add(bookTwo);
//
//        Orders orderOne = new Orders(1, userOne, booksList, new Date(), new Date(), OrderStatus.RESERVED);
//        Orders orderTwo = new Orders(2, userTwo, booksList, new Date(86400000), new Date(), OrderStatus.FINISHED);
//        Orders orderThree = new Orders(3, userTwo, booksList, new Date(), null, OrderStatus.INPROGRESS);
//        List<Orders> ordersListOne = new ArrayList<>();
//        List<Orders> ordersListTwo = new ArrayList<>();
//        ordersListOne.add(orderOne);
//        ordersListTwo.add(orderTwo);
//        ordersListTwo.add(orderThree);
//        userOne.setMyOrders(ordersListOne);
//        userTwo.setMyOrders(ordersListTwo);
//        List<Orders> ordersList = new ArrayList<>();
//        ordersList.addAll(ordersListOne);
//        ordersList.addAll(ordersListTwo);
//
//        BooksWithCount booksWithCountOne = new BooksWithCount(bookTwo, 2);
//        BooksWithCount booksWithCountTwo = new BooksWithCount(bookOne, 2);
//        List<BooksWithCount> booksCountList = new ArrayList<>();
//        booksCountList.add(booksWithCountOne);
//        booksCountList.add(booksWithCountTwo);
//
//        when(ordersRepository.findAll()).thenReturn(ordersList);
//        when(booksRepository.findAll()).thenReturn(booksList);
//        when(usersRepository.findAll()).thenReturn(usersList);
//
//        mockMvc.perform(get("/generateReportFull").param("fromDate", "2022-11-10").param("toDate", "2022-12-10"))
//                .andExpect(MockMvcResultMatchers.view().name("generateReportFull.html"))
////                .andExpect(MockMvcResultMatchers.model().attribute("booksWithCount", booksCountList))
//                .andExpect(MockMvcResultMatchers.model().attribute("ordersFinished", 1))
//                .andExpect(MockMvcResultMatchers.model().attribute("averageOrderDuration", "0,00 days"))
//                .andExpect(MockMvcResultMatchers.model().attribute("ordersStarted", 2));
//    }
//}
