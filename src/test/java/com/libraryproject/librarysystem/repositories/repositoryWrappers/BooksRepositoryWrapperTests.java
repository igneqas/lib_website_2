package com.libraryproject.librarysystem.repositories.repositoryWrappers;

import com.libraryproject.librarysystem.domain.Availability;
import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.Genre;
import com.libraryproject.librarysystem.repositories.BooksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BooksRepositoryWrapperTests {

    @Mock
    BooksRepository booksRepository;

    @InjectMocks
    BooksRepositoryWrapper booksRepositoryWrapper;

    @Test
    public void getAllBooks_shouldReturnAllBooks() {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);

        when(booksRepository.findAll()).thenReturn(booksList);

        // Act
        List<Books> books = booksRepositoryWrapper.getAllBooks();

        // Assert
        assertEquals(books, booksList);
    }

    @Test
    public void getBook_shouldReturnBook() {
        // Arrange
         Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
         Optional<Books> optionalBook = Optional.of(book);

        when(booksRepository.findById(anyInt())).thenReturn(optionalBook);

        // Act
        Optional<Books> actualBook = booksRepositoryWrapper.getBook(1);

        // Assert
        assertEquals(optionalBook, actualBook);
    }

    @Test
    public void getReservedBooksFromIds_shouldReturnReservedBook() {
        // Arrange
        Books book =  new Books(1, "Don Quixote", new ArrayList<>(), Genre.FANTASY, "2-6845-891253", "2020", "Nice book", "", Availability.AVAILABLE, new ArrayList<>());
        Optional<Books> optionalBook = Optional.of(book);
        List<String> bookIdsList = new ArrayList<>();
        bookIdsList.add("1");

        when(booksRepository.findById(anyInt())).thenReturn(optionalBook);

        // Act
        List<Books> actualBooksList = booksRepositoryWrapper.getReservedBooksFromIds(bookIdsList);

        // Assert
        assertEquals(Availability.RESERVED, actualBooksList.get(0).getAvailability());
        assertEquals(book.getId(), actualBooksList.get(0).getId());
    }
}
