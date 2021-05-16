package com.tunaproject.userservice.services;

import com.tunaproject.userservice.domain.User;
import com.tunaproject.userservice.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void createUser(User user) {
        usersRepository.insert(user);
    }

    public User getUserByUserName(String username){
        return usersRepository.getUserByUsername(username);
    }
}
