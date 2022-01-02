package r6guidebackend.controllers;

import r6guidebackend.models.User;
import r6guidebackend.models.requests.GetTokenRequest;
import r6guidebackend.models.requests.RegisterRequest;
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
    public ResponseEntity loginUser(@RequestBody User model) {
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
            userService.registerUser(model);
            
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/verifyToken")
    public ResponseEntity verifyToken(@RequestBody String token) {
        try {
            User user = userService.verifyUserByToken(token).get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//    @PostMapping("/getToken")
//    public ResponseEntity getToken(@RequestBody GetTokenRequest model) {
//        try {
//            var response = userService.createCustomToken(model).get();
//
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }
}
