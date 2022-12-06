package com.libraryproject.librarysystem.controllers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IBooksRepositoryWrapper;
import com.libraryproject.librarysystem.utilities.interfaces.IModelHelpers;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IOrdersRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IUsersRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.thymeleafTypes.EditOrderForm;
import com.libraryproject.librarysystem.utilities.interfaces.IUserHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class OrdersControllers {

    private final String booksListRedirect = "redirect:/orderslist";

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private IUsersRepositoryWrapper usersRepositoryWrapper;

    @Autowired
    private IModelHelpers modelHelpers;

    @Autowired
    private IOrdersRepositoryWrapper orderHelpers;

    @Autowired
    private IBooksRepositoryWrapper booksRepositoryWrapper;

    @Autowired
    private IUserHelpers userHelpers;

    @GetMapping("/orderslist")
    public String openOrdersListPage(Model model) {
        Users user = userHelpers.getCurrentUser();
        List<Orders> orders = orderHelpers.getOrdersForUser(user);

        modelHelpers.addUserToModel(model, user);
        model.addAttribute("orders", orders);
        return "orderslist.html";
    }

    @GetMapping("/addneworder")
    public String openNewOrderPage(Model model) {
        model.addAttribute("users", usersRepositoryWrapper.getAllUsers());
        model.addAttribute("books", booksRepositoryWrapper.getAllBooks());
        return "addneworder.html";
    }

    @PostMapping("/addthisneworder")
    public String addNewOrder(
            @RequestParam(value = "userId") String userID,
            @RequestParam(value = "issueDate") String orderCreatedDate,
            @RequestParam(value = "orderInfo") String orderStatus,
            @RequestParam(value = "bookIds") List<String> selectedBookIds) {
        orderHelpers.createAndSaveOrder(userID, orderCreatedDate, orderStatus, selectedBookIds);
        return booksListRedirect;
    }

    @GetMapping("/vieworder/{orderID}")
    public String openOrderInformationPage(Model model, @PathVariable int orderID) {
        Orders order = ordersRepository.getById(orderID);
        Users user = userHelpers.getCurrentUser();

        modelHelpers.addUserToModel(model, user);
        model.addAttribute("order", order);
        return "infooneorder.html";
    }

    @GetMapping("/vieworder/edit/{orderID}")
    public String openEditOrderPage(Model model, @PathVariable int orderID) {
        EditOrderForm form = modelHelpers.createEditOrderForm(orderID);
        model.addAttribute("editOrderForm", form);
        return "editorder.html";
    }

    @PostMapping("/editthisorder")
    public String editOrder(@Valid Orders order) {
        ordersRepository.save(order);
        return booksListRedirect;
    }

    @PostMapping("/vieworder/finish/{orderID}")
    public String finishOrder(@PathVariable int orderID) {
        orderHelpers.finishOrder(orderID);
        return booksListRedirect;
    }

    @GetMapping("/vieworder/delete/{orderID}")
    public String deleteOrder(@PathVariable int orderID) {
        ordersRepository.deleteById(orderID);
        return booksListRedirect;
    }
}
