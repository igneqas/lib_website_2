package com.libraryproject.librarysystem.utilities;

import com.libraryproject.librarysystem.domain.AccessLevel;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class UserHelpersTests {

    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UserHelpers userHelpers;

    @Test
    public void isUserLibrarian_userIsLibrarian_shouldReturnTrue() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.LIBRARIAN);

        // Act
        boolean result = userHelpers.isUserLibrarian(user);

        // Assert
        assertEquals(true, result);
    }

    @Test
    public void isUserLibrarian_userIsClient_shouldReturnFalse() {
        // Arrange
        Users user = new Users(6, "Marcus M", "MM", "6864546545", "email@gmail.com", "Password", AccessLevel.CLIENT);

        // Act
        boolean result = userHelpers.isUserLibrarian(user);

        // Assert
        assertEquals(false, result);
    }
}
