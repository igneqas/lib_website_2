package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.AccessLevel;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.domain.DTO.UsersDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class UsersFactoryTests {

    @InjectMocks
    UsersFactory usersFactory;

    @Test
    public void create_shouldCreateUsersObject() {
        // Arrange
        UsersDTO usersDTO = new UsersDTO(1, "username", "password", "fullName",
                "phone", "email", AccessLevel.CLIENT, new ArrayList<>());
        // Act
        Users users = usersFactory.create(usersDTO);
        // Assert
        assertEquals(users.getUserID(), usersDTO.getUserID());
        assertEquals(users.getUserName(), usersDTO.getUserName());
        assertEquals(users.getPassword(), usersDTO.getPassword());
        assertEquals(users.getUserFullName(), usersDTO.getUserFullName());
        assertEquals(users.getPhone(), usersDTO.getPhone());
        assertEquals(users.getEmail(), usersDTO.getEmail());
        assertEquals(users.getAccessLevel(), usersDTO.getAccessLevel());
        assertEquals(users.getMyOrders(), usersDTO.getMyOrders());
    }
}
