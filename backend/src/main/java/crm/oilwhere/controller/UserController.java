package crm.oilwhere.controller;

import crm.oilwhere.dto.LoginRequest;
import crm.oilwhere.dto.LoginResponseDTO;
import crm.oilwhere.dto.UserDTO;
import crm.oilwhere.model.User;
import crm.oilwhere.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing user-related operations, such as retrieving, creating,
 * updating, deleting, and authenticating users.
 * Provides endpoints for client interactions with the user data.
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a new UserController with the specified UserService.
     *
     * @param userService the service used to manage user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing a list of all users
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the user with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates a new user based on the provided UserDTO.
     *
     * @param userDTO the data transfer object containing user details
     * @return a ResponseEntity containing the created user
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity containing a message indicating the result of the deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = userService.deleteUser(id);
        return ResponseEntity.ok(result);
    }

    /**
     * Updates an existing user's information based on the provided UserDTO.
     *
     * @param id the ID of the user to update
     * @param userDTO the data transfer object containing updated user details
     * @return a ResponseEntity containing the updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user);
    }

    /**
     * Authenticates a user based on the provided login credentials.
     * <p>
     * This endpoint verifies the username and password provided in the {@link LoginRequest}.
     * If authentication is successful, it returns a {@link LoginResponseDTO} containing the
     * user's details and a success message. Otherwise, it responds with an HTTP 401 status
     * and an appropriate error message.
     * </p>
     *
     * @param loginRequest the data transfer object containing the username and password
     *                     for authentication
     * @return a {@link ResponseEntity} containing:
     *         <ul>
     *           <li>A {@link LoginResponseDTO} with the user's username, role, and a success
     *               message if authentication succeeds.</li>
     *           <li>A {@link LoginResponseDTO} with a null username and role, and an error
     *               message if authentication fails.</li>
     *         </ul>
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> authenticatedUser = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

        if (authenticatedUser.isPresent()) {
            User user = authenticatedUser.get();
            LoginResponseDTO response = new LoginResponseDTO(user.getUsername(), user.getRole(), "Login successful", user.getUserId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(new LoginResponseDTO(null, null, "Invalid username or password", null));
        }
    }
}
