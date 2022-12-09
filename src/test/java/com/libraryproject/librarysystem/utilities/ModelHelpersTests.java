package com.libraryproject.librarysystem.utilities;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.thymeleafTypes.EditOrderForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ModelHelpersTests {

    @Mock
    Model model;

    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    ModelHelpers modelHelpers;

    @Test
    public void addUserToModel_userIsLibrarian_shouldAdd() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.LIBRARIAN);

        // Act
        modelHelpers.addUserToModel(model, user);
    }

    @Test
    public void addUserToModel_userIsClient_shouldAdd() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);

        // Act
        modelHelpers.addUserToModel(model, user);
    }

    @Test
    public void createEditOrderForm_shouldReturnEditOrderForm() {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);
        Orders order = new Orders(1, user, booksList, new Date(), new Date(), OrderStatus.INPROGRESS);
        EditOrderForm editOrderForm = new EditOrderForm(order, booksList.toString());

        when(ordersRepository.getById(anyInt())).thenReturn(order);

        // Act
        EditOrderForm result = modelHelpers.createEditOrderForm(1);

        // Assert
        assertEquals(editOrderForm.getOrder(), result.getOrder());
    }
}
