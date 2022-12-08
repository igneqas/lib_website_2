package com.libraryproject.librarysystem.domain.factories.interfaces;

import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.DTO.BooksDTO;

public interface IBooksFactory {
    Books create(BooksDTO booksDTO);
}
