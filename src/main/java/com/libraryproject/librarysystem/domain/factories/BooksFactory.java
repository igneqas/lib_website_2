package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.Books;
import com.libraryproject.librarysystem.domain.DTO.BooksDTO;
import com.libraryproject.librarysystem.domain.factories.interfaces.IBooksFactory;
import org.springframework.stereotype.Service;

@Service
public class BooksFactory implements IBooksFactory {
    @Override
    public Books create(BooksDTO booksDTO) {
        return new Books(booksDTO.getId(),
                booksDTO.getTitle(),
                booksDTO.getAuthorsList(),
                booksDTO.getGenre(),
                booksDTO.getIsbn(),
                booksDTO.getReleaseYear(),
                booksDTO.getDescription(),
                booksDTO.getUrl(),
                booksDTO.getAvailability(),
                booksDTO.getBookOrders());
    }
}
