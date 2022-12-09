package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.Authors;
import com.libraryproject.librarysystem.domain.Availability;
import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.DTO.AuthorsDTO;
import com.libraryproject.librarysystem.domain.DTO.BooksDTO;
import com.libraryproject.librarysystem.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class BooksFactoryTests {

    @InjectMocks
    BooksFactory booksFactory;

    @Test
    public void create_shouldCreateBooksObject() {
        // Arrange
        BooksDTO booksDTO = new BooksDTO(1, "title", new ArrayList<>(), Genre.FANTASY,
                "isbn", "2020", "description", "url", Availability.AVAILABLE, new ArrayList<>());
        // Act
        Books books = booksFactory.create(booksDTO);
        // Assert
        assertEquals(books.getId(), booksDTO.getId());
        assertEquals(books.getTitle(), booksDTO.getTitle());
        assertEquals(books.getBookOrders(), booksDTO.getBookOrders());
        assertEquals(books.getGenre(), booksDTO.getGenre());
        assertEquals(books.getIsbn(), booksDTO.getIsbn());
        assertEquals(books.getReleaseYear(), booksDTO.getReleaseYear());
        assertEquals(books.getDescription(), booksDTO.getDescription());
        assertEquals(books.getUrl(), booksDTO.getUrl());
        assertEquals(books.getAvailability(), booksDTO.getAvailability());
        assertEquals(books.getAuthorsList(), booksDTO.getAuthorsList());
    }
}
