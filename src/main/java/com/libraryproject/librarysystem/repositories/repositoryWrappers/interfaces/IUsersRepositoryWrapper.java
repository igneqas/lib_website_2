package com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces;

import com.libraryproject.librarysystem.domain.Users;

import java.util.List;

public interface IUsersRepositoryWrapper {
    List<Users> getAllUsers();
}
