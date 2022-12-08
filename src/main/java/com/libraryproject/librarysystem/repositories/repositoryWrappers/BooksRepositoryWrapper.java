package com.libraryproject.librarysystem.repositories.repositoryWrappers;

import com.libraryproject.librarysystem.domain.Availability;
import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces.IBooksRepositoryWrapper;
import com.libraryproject.librarysystem.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksRepositoryWrapper implements IBooksRepositoryWrapper {

    @Autowired
    private BooksRepository booksRepository;

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Books> getBook(int bookId) {
        return booksRepository.findById(bookId);
    }

    private Books getReservedBook(String bookId) {
        Optional<Books> booksOptional = getBook(Integer.parseInt(bookId));
        Books book = booksOptional.get();
        book.setAvailability(Availability.RESERVED);
        return book;
    }

    public List<Books> getReservedBooksFromIds(List<String> bookIds) {
        List<Books> booksList = new ArrayList<>();
        for (String bookId:bookIds) {
            Books selectedBook = getReservedBook(bookId);
            booksList.add(selectedBook);
        }
        return booksList;
    }
}
