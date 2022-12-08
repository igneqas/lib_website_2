package com.libraryproject.librarysystem.repositories.repositoryWrappers.interfaces;

import com.libraryproject.librarysystem.domain.Books;

import java.util.List;
import java.util.Optional;

public interface IBooksRepositoryWrapper {
    List<Books> getAllBooks();
    Optional<Books> getBook(int bookId);
    List<Books> getReservedBooksFromIds(List<String> bookIds);
}
