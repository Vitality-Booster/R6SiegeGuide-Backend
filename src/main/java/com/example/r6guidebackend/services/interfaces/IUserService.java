package com.example.r6guidebackend.services.interfaces;

import com.example.r6guidebackend.models.User;
import javassist.NotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

//@Service
public interface IUserService {

    @Async
    CompletableFuture<User> getUser (User model) throws Exception;

    @Async
    CompletableFuture<List<User>> getAllUsers() throws Exception;

    @Async
    CompletableFuture<Void> addUser(User model) throws Exception;

    @Async
    CompletableFuture<Void> updateUser(User model) throws Exception;

    @Async
    CompletableFuture<Void> deleteUser(User model) throws Exception;

    @Async
    CompletableFuture<User> registerUser(User model) throws Exception;

    @Async
    CompletableFuture<User> loginUser(User model) throws Exception;
}
