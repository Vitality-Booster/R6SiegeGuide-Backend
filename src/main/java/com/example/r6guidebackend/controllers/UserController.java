package com.example.r6guidebackend.controllers;

import com.example.r6guidebackend.models.User;
import com.example.r6guidebackend.repositories.IUserRepository;
import com.example.r6guidebackend.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserRepository userRepository, IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User model) {

        try {
            User user = userService.loginUser(model).get();

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch(Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User model) {
        System.out.println(model);
        try {
            User user = userService.registerUser(model).get();
            
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
