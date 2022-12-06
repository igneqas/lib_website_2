package com.libraryproject.librarysystem.utilities;

import com.libraryproject.librarysystem.domain.AccessLevel;
import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.Orders;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.thymeleafTypes.EditOrderForm;
import com.libraryproject.librarysystem.utilities.interfaces.IModelHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ModelHelpers implements IModelHelpers {

    @Autowired
    OrdersRepository ordersRepository;

    private final String accessLevelAttribute = "level";

    public void addUserToModel(Model model, Users user) {
        String accessLevelAttributeValue = getAccessLevelAttributeValue(user);
        model.addAttribute(accessLevelAttribute, accessLevelAttributeValue);
    }

    private String getAccessLevelAttributeValue(Users user) {
        if (user.getAccessLevel() == AccessLevel.LIBRARIAN) {
            return "librarian";
        }
        return "user";
    }

    public EditOrderForm createEditOrderForm(int orderId) {
        Orders order = ordersRepository.getById(orderId);
        String booksListString = order.getBooksList().get(0).getTitle();
        order.getBooksList().remove(0);
        for(Books book : order.getBooksList()){
            booksListString = booksListString + "\n" + book.getTitle();
        }
        return new EditOrderForm(order, booksListString);
    }
}
