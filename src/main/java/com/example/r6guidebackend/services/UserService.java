package com.example.r6guidebackend.services;

import com.example.r6guidebackend.models.User;
import com.example.r6guidebackend.models.responses.LoginResponse;
import com.example.r6guidebackend.repositories.IUserRepository;
import com.example.r6guidebackend.services.interfaces.IUserService;
import javassist.NotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public CompletableFuture<User> getUser(User model) throws Exception {

        if (model.getEmail() == null && model.getUsername() == null) {
            throw new IllegalArgumentException("You need to provide either user's email or their username");
        }
        User user = findUser(model).get();

        if (user == null) {
            throw new NotFoundException("This user does not exist");
        }

        return CompletableFuture.completedFuture(user);
    }

    @Override
    public CompletableFuture<List<User>> getAllUsers() throws Exception {
        List<User> users = userRepository.findAll();

        return CompletableFuture.completedFuture(users);
    }

    @Override
    public CompletableFuture<Void> addUser(User model) throws Exception {
        User user = findUser(model).get();

        if (user != null) {
            throw new IllegalArgumentException("A user with these email or username already exists");
        }
        else {
            userRepository.save(model);
        }
        return CompletableFuture.runAsync(()->{});
    }

    // this method may be changed later
    @Override
    public CompletableFuture<Void> updateUser(User model) throws Exception {
        User user = findUser(model).get();

        if (user == null) {
            throw new NotFoundException("This user does not exist");
        }

        userRepository.save(user);

        // do some changes to user info

        return CompletableFuture.runAsync(()->{});
    }

    @Override
    public CompletableFuture<Void> deleteUser(User model) throws Exception {
        User user = findUser(model).get();

        if (user == null) {
            throw new NotFoundException("This user does not exist");
        }
        else {
            userRepository.delete(user);
        }

        return CompletableFuture.runAsync(() -> {});
    }

    @Override
    public CompletableFuture<Void> registerUser(User model) throws Exception {
        User user = findUser(model).get();

        if (user != null) {
            throw new IllegalArgumentException("A user with these email or username already exists");
        }
        else {
            userRepository.save(model);
        }

        // decide what actually I need to return
        return CompletableFuture.runAsync(() -> {});
    }

    @Override
    public CompletableFuture<LoginResponse> loginUser(User model) throws Exception {
        User user = findUser(model).get();

        if (user == null) {
            throw new NotFoundException("A user with this email does not exist");
        }

        LoginResponse response = new LoginResponse(user.getUsername(), user.isAdmin());

        // decide what actually I need to return
        return CompletableFuture.completedFuture(response);
    }

    private CompletableFuture<User> findUser(User model) {

        User user = userRepository.findUserByEmail(model.getEmail());

        if (user == null) {
            user = userRepository.findUserByUsername(model.getUsername());
        }
        return CompletableFuture.completedFuture(user);
    }
}
