package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.Authors;
import com.libraryproject.librarysystem.domain.DTO.AuthorsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class AuthorsFactoryTests {

    @InjectMocks
    AuthorsFactory authorsFactory;

    @Test
    public void create_shouldCreateAuthorsObject() {
        // Arrange
        AuthorsDTO authorsDTO = new AuthorsDTO(1, "name", "USA", new ArrayList<>());
        // Act
        Authors authors = authorsFactory.create(authorsDTO);
        // Assert
        assertEquals(authors.getAuthorID(), authorsDTO.getAuthorID());
        assertEquals(authors.getAuthorName(), authorsDTO.getAuthorName());
        assertEquals(authors.getAuthorCountry(), authorsDTO.getAuthorCountry());
        assertEquals(authors.getBooksList(), authorsDTO.getBooksList());
    }
}
