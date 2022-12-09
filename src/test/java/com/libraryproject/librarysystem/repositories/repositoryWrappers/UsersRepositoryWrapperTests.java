package com.libraryproject.librarysystem.repositories.repositoryWrappers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UsersRepositoryWrapperTests {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UsersRepositoryWrapper usersRepositoryWrapper;

    @Test
    public void getAllUsers_shouldReturnUsers() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.LIBRARIAN);
        List<Users> usersList = new ArrayList<>();
        usersList.add(user);

        when(usersRepository.findAll()).thenReturn(usersList);

        // Act
        List<Users> users = usersRepositoryWrapper.getAllUsers();

        // Assert
        assertEquals(usersList, users);
    }
}
