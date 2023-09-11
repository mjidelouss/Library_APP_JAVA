package com.library.app.services;

import com.library.app.domain.User;
import com.library.app.repository.MemberRepository;
import com.library.app.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {
        return this.userRepository.login(email, password);
    }

    public User register(String name, String email, String password, String telephone, String adresse) {
        return this.userRepository.register(name, email, password, telephone, adresse);
    }
}
