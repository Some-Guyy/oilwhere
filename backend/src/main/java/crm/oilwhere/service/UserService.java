package crm.oilwhere.service;

import crm.oilwhere.dto.UserDTO;
import crm.oilwhere.model.Role;
import crm.oilwhere.model.User;
import crm.oilwhere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing user data and business logic related to user operations.
 * Provides methods to retrieve, create, update, delete, and authenticate users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService with the specified UserRepository.
     *
     * @param userRepository the repository used to interact with user data in the database
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the database.
     * Intended for administrative use.
     *
     * @return a list of all User records
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a specific user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User record with the specified ID
     * @throws RuntimeException if the user with the specified ID is not found
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Creates a new user based on the provided UserDTO.
     *
     * @param userDTO the data transfer object containing details of the new user
     * @return the created User record
     * @throws IllegalArgumentException if the specified role in userDTO is invalid
     */
    public User createUser(UserDTO userDTO) {
        Role role;
        try {
            role = Role.valueOf(userDTO.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + userDTO.getRole());
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(role);

        return userRepository.save(newUser);
    }

    /**
     * Updates an existing user's details, including username, password, and role.
     * Prevents ADMIN users from updating other ADMIN users.
     *
     * @param id the ID of the user to update
     * @param userDTO the data transfer object containing updated user details
     * @return the updated User record
     * @throws RuntimeException if the user with the specified ID is not found or if an ADMIN user tries to update another ADMIN
     * @throws IllegalArgumentException if the specified role in userDTO is invalid
     */
    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (existingUser.getRole() == Role.ADMIN && userDTO.getRole().equalsIgnoreCase("ADMIN")) {
                throw new RuntimeException("ADMIN users cannot update other ADMIN users.");
            }

            existingUser.setUsername(userDTO.getUsername());
            existingUser.setPassword(userDTO.getPassword());

            if (!existingUser.getRole().toString().equalsIgnoreCase(userDTO.getRole())) {
                Role newRole;
                try {
                    newRole = Role.valueOf(userDTO.getRole().toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid role: " + userDTO.getRole());
                }

                existingUser.setRole(newRole);
            }

            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param id the ID of the user to delete
     * @return a confirmation message if the deletion is successful
     * @throws RuntimeException if the user with the specified ID is not found
     */
    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted";
        } else {
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Authenticates a user by verifying their username and password.
     *
     * @param username the username of the user attempting to log in
     * @param password the password of the user attempting to log in
     * @return an Optional containing the authenticated User if successful, or an empty Optional if authentication fails
     */
    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
