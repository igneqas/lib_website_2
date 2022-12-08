package com.libraryproject.librarysystem.domain.factories.interfaces;

import com.libraryproject.librarysystem.domain.DTO.UsersDTO;
import com.libraryproject.librarysystem.domain.Users;

public interface IUsersFactory {
    Users create(UsersDTO usersDTO);
}
