package com.libraryproject.librarysystem.security;

import com.libraryproject.librarysystem.domain.Users;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Users> user = usersRepository.findByUserName(username);
        if(user.isPresent()) {
            Optional<UserDetails> userDetailsOptional = user.map(MyUserDetails::new);
            return userDetailsOptional.orElse(null);
        }
        return null;

    }
}
