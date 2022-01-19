package r6guidebackend.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import r6guidebackend.models.User;
import r6guidebackend.models.requests.LoginRequest;
import r6guidebackend.models.requests.RegisterRequest;
import r6guidebackend.models.requests.VerifyTokenRequest;
import r6guidebackend.models.responses.CustomTokenResponse;
import r6guidebackend.services.interfaces.IFirebaseConfig;
import r6guidebackend.repositories.IUserRepository;
import r6guidebackend.services.interfaces.IUserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository, IFirebaseConfig firebaseConfig) {
        this.userRepository = userRepository;
        firebaseConfig.setup();
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
    public CompletableFuture<CustomTokenResponse> registerUser(RegisterRequest model) throws Exception {
//        String uid = generateUid();
//
//        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest();
//        createRequest.setUid(uid);
//        createRequest.setEmail(model.getEmail());
//        createRequest.setPassword(model.getPassword());
//        FirebaseAuth.getInstance().createUser(createRequest);
//
//        // only if Firebase doesn't throw any exception then we will create a user in the database
//        User user = new User(model.getFullName(), model.getEmail(), model.getUsername());
//        userRepository.save(user);
//
//        String customToken = createCustomToken(user).get();

        CustomTokenResponse resp = new CustomTokenResponse();
        resp.setToken("customToken");

        return CompletableFuture.completedFuture(resp);
    }

    @Override
    public CompletableFuture<CustomTokenResponse> loginUser(LoginRequest model) throws Exception {
        var user = userRepository.findUserByEmail(model.getEmail());

        if (user == null) {
            throw new NotFoundException("A user with this email does not exist");
        }

        CustomTokenResponse response = new CustomTokenResponse();

        response.setToken(createCustomToken(user).get());

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<CustomTokenResponse> verifyUserByToken(VerifyTokenRequest model) throws Exception {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(model.getIdToken());
        String email = decodedToken.getEmail();
        var user = userRepository.findUserByEmail(email);
        String customToken = createCustomToken(user).get();
        CustomTokenResponse response = new CustomTokenResponse();
        response.setToken(customToken);

        return CompletableFuture.completedFuture(response);
    }

    public CompletableFuture<String> createCustomToken(User user) throws Exception{
        System.out.println("Email: " + user.getEmail());

        String uid = FirebaseAuth.getInstance().getUserByEmail(user.getEmail()).getUid();
        System.out.println("Uid: " + uid);
        Map<String, Object> additionalClaims = new HashMap<>();
        additionalClaims.put("admin", user.isAdmin());

        String customToken = FirebaseAuth.getInstance().createCustomToken(uid, additionalClaims);

        return CompletableFuture.completedFuture(customToken);
    }

    private String generateUid() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase();
        String digits = "0123456789";
        String alphanumeric = upper + lower + digits;
        char[] charArray = alphanumeric.toCharArray();
        StringBuilder userUid = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 32; i++) {
            userUid.append(charArray[random.nextInt(charArray.length)]);
        }
        return userUid.toString();
    }

    private CompletableFuture<User> findUser(User model) {

        User user = userRepository.findUserByEmail(model.getEmail());

        if (user == null) {
            user = userRepository.findUserByUsername(model.getUsername());
        }
        return CompletableFuture.completedFuture(user);
    }
}
