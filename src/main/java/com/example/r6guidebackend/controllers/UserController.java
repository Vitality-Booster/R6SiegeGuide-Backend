package com.example.r6guidebackend.controllers;

import com.example.r6guidebackend.models.User;
import com.example.r6guidebackend.repositories.IUserRepository;
import com.example.r6guidebackend.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@Mapping("/user")
public class UserController {

    private final IUserService userService;
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository, IUserService userService) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) {

        User checkUser = userRepository.findUserByEmail(user.getEmail());
        if (checkUser != null) {
            if (checkUser.getPassword().equals(user.getPassword())) {
                return new ResponseEntity(checkUser, HttpStatus.OK);
            }

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        User logedUser = userService.findUser(user.getEmail());
//
//        if (logedUser == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        return new ResponseEntity(logedUser, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {

//        System.out.println(request.getFullName() + ", " + request.getPassword());
        System.out.println(user);

        try {
            User checkUser = userRepository.findUserByEmail(user.getEmail());

            if (checkUser == null) {
                User checkUser2 = userRepository.findUserByUsername(user.getUsername());

                if (checkUser2 == null) {
                    userRepository.save(user);
                    return new ResponseEntity(user, HttpStatus.OK);
                }
            }


        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
//        if (userService.addUser(user)) {
//            return ResponseEntity.status(HttpStatus.OK).body(user);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
