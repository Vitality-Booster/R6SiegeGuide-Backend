package r6guidebackend.services.interfaces;

import r6guidebackend.models.User;
import r6guidebackend.models.requests.GetTokenRequest;
import r6guidebackend.models.requests.RegisterRequest;
import r6guidebackend.models.responses.GetTokenResponse;
import r6guidebackend.models.responses.LoginResponse;
import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.responses.CustomTokenResponse;

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
    CompletableFuture<CustomTokenResponse> registerUser(RegisterRequest model) throws Exception;

    @Async
    CompletableFuture<LoginResponse> loginUser(User model) throws Exception;

    @Async
    CompletableFuture<User> verifyUserByToken(String token) throws Exception;

//    @Async
//    CompletableFuture<GetTokenResponse> createCustomToken(GetTokenRequest model) throws Exception;
}
