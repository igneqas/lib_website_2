package com.libraryproject.librarysystem.utilities.interfaces;

import com.libraryproject.librarysystem.domain.Users;

public interface IUserHelpers {
    Users getCurrentUser();
    boolean isUserLibrarian(Users user);
}
