package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.Authors;
import com.libraryproject.librarysystem.domain.DTO.AuthorsDTO;
import com.libraryproject.librarysystem.domain.factories.interfaces.IAuthorsFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthorsFactory implements IAuthorsFactory {

    public Authors create(AuthorsDTO authorsDTO) {
        return new Authors(authorsDTO.getAuthorID(), authorsDTO.getAuthorName(), authorsDTO.getAuthorCountry(), authorsDTO.getBooksList());
    }
}
