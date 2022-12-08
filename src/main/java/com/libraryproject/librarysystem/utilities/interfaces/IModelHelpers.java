package com.libraryproject.librarysystem.utilities.interfaces;

import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.thymeleafTypes.EditOrderForm;
import org.springframework.ui.Model;

public interface IModelHelpers {
    void addUserToModel(Model model, Users user);
    EditOrderForm createEditOrderForm(int orderId);
}
