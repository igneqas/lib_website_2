package com.libraryproject.librarysystem.repositories.repositoryWrappers;

import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IUsersRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersRepositoryWrapper implements IUsersRepositoryWrapper {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
