package com.libraryproject.librarysystem.controllers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.repositories.BooksRepository;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.utilities.interfaces.IModelHelpers;
import com.libraryproject.librarysystem.utilities.interfaces.IUserHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LibraryController {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private IUserHelpers userHelpers;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private IModelHelpers modelHelpers;

    private final String accessAttributeName = "level";

    @GetMapping("/login")
    public String home() {
        return "login.html";
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        Users user = userHelpers.getCurrentUser();
        modelHelpers.addUserToModel(model, user);
        return "dashboard.html";
    }

    @GetMapping("/bookslist")
    public String bookListUser(Model model) {
        Users user = userHelpers.getCurrentUser();

        List<Books> books;
        modelHelpers.addUserToModel(model, user);

        if (user.getAccessLevel() == AccessLevel.LIBRARIAN) {
            books = booksRepository.findAll();
        } else {
            books = booksRepository.findByAvailability(Availability.AVAILABLE);
        }

        model.addAttribute("books", books);
        model.addAttribute("available", Availability.AVAILABLE);
        return "booklistuser.html";
    }

    @PostMapping("/library/confirmreservation")
    public String startReservation(@RequestParam MultiValueMap<String, Integer> map, Model model) {

        if(map.isEmpty()){
            model.addAttribute("empty", "add books to make confirmation");
            model.addAttribute("books", booksRepository.findAll());
            return "redirect:/";
        }

        String[] booksSelected = map.get("bookIds").toString().replace("\\[","").replace("\\]","").split(",");
        ArrayList<Books> list = new ArrayList<>();

        for (String number : booksSelected) {
            Integer n =  Integer.parseInt(number.trim());
            list.add(booksRepository.getById(n));
        }

        model.addAttribute("booksSelected", list);
        return "/confirmreservation.html";
    }

    @PostMapping("/library/confirmreservationend")
    public String confirmreservationend(@RequestParam MultiValueMap<String, Integer> map) {
        String[] booksSelected = map.get("bookIds").toString().replace("\\[","").replace("\\]","").split(",");
        Users user = userHelpers.getCurrentUser();
        List<Books> listOfBooks = new ArrayList<>();

        for (String number:booksSelected) {
            Integer bookId = Integer.parseInt(number.trim());
            Optional<Books> bookOp = booksRepository.findById(bookId);
            Books book = bookOp.orElseGet(Books::new);
            book.setAvailability(Availability.RESERVED);
            listOfBooks.add(book);
        }
        Orders order = new Orders(user, new Date(), listOfBooks, OrderStatus.RESERVED);
        ordersRepository.save(order);

        return "/confirmreservationend.html";
    }
}
