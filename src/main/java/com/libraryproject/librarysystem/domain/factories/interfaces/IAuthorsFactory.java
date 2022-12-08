package com.libraryproject.librarysystem.domain.factories.interfaces;

import com.libraryproject.librarysystem.domain.Authors;
import com.libraryproject.librarysystem.domain.DTO.AuthorsDTO;

public interface IAuthorsFactory {
    Authors create(AuthorsDTO authorsDTO);
}
