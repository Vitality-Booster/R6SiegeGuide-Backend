package r6guidebackend.controllers;

import r6guidebackend.models.User;
import r6guidebackend.models.requests.GetTokenRequest;
import r6guidebackend.models.requests.LoginRequest;
import r6guidebackend.models.requests.RegisterRequest;
import r6guidebackend.models.requests.VerifyTokenRequest;
import r6guidebackend.models.responses.CustomTokenResponse;
import r6guidebackend.repositories.IUserRepository;
import r6guidebackend.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.interfaces.PBEKey;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserRepository userRepository, IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequest model) {
        try {
            var response = userService.loginUser(model).get();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterRequest model) {
        try {
            System.out.println(model);
            var beforeResponse = userService.registerUser(model);
            System.out.println("in real code: " + beforeResponse);
            var response = beforeResponse.get();
            
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verify-token")
    public ResponseEntity verifyToken(@RequestBody VerifyTokenRequest model) {
        try {
            var response = userService.verifyUserByToken(model).get();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
