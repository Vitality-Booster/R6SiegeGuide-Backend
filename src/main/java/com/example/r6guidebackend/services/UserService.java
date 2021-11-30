package com.example.r6guidebackend.services;

import com.example.r6guidebackend.models.User;
import com.example.r6guidebackend.repositories.IUserRepository;
import com.example.r6guidebackend.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUser(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public Boolean addUser(User user) {
        if (!checkIfUserRegistered(user)) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUser(User user) {
        return null;
    }

    @Override
    public Boolean deleteUser(User user) {
        return null;
    }

    @Override
    public Boolean checkIfUserRegistered(User user) {
        User checkUser = userRepository.findUserByEmail(user.getEmail());
        if (checkUser != null) {
            User checkUser2 = userRepository.findUserByUsername(user.getUsername());
            if (checkUser2 != null) {
                return false;
            }
        }
        return true;
    }
}
