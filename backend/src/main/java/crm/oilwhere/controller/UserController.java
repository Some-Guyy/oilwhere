package crm.oilwhere.controller;

import crm.oilwhere.dto.LoginRequest;
import crm.oilwhere.dto.LoginResponseDTO;
import crm.oilwhere.model.User;
import crm.oilwhere.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (authenticatedUser.isPresent()) {
            User user = authenticatedUser.get();

            // create responseDTO to create object to return to FE
            LoginResponseDTO response = new LoginResponseDTO(user.getUsername(), user.getRole(), "Login successful");

            return ResponseEntity.ok(response); 
        } else {
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, null, "Invalid username or password")); //create responseDTO to give null and messsage
        }
    }
}
