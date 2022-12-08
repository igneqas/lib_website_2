package com.libraryproject.librarysystem.domain.factories;

import com.libraryproject.librarysystem.domain.DTO.UsersDTO;
import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.domain.factories.interfaces.IUsersFactory;
import org.springframework.stereotype.Service;

@Service
public class UsersFactory implements IUsersFactory {
    @Override
    public Users create(UsersDTO usersDTO) {
        return new Users(usersDTO.getUserID(),
                usersDTO.getUserName(),
                usersDTO.getPassword(),
                usersDTO.getUserFullName(),
                usersDTO.getPhone(),
                usersDTO.getEmail(),
                usersDTO.getAccessLevel(),
                usersDTO.getMyOrders());
    }
}
