package com.example.r6guidebackend.services.interfaces;

import com.example.r6guidebackend.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface IUserService {

    User getUser(String email);

    List<User> getAllUsers();

    Boolean addUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(User user);

    Boolean checkIfUserRegistered(User user);
}
