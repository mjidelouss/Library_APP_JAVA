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
    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password.matches(regex);
    }

    public boolean isValidRegisterData(String name, String telephone, String adresse, String email, String password) {
        if (name != null && telephone != null && adresse != null && email != null && password != null) {
            return true;
        } else {
            return false;
        }
    }
}
