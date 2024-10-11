package crm.oilwhere.service;

import crm.oilwhere.model.User;
import crm.oilwhere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor without PasswordEncoder
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users (Admin action)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a specific user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Create a new user (Admin action)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the user's information
        existingUser.setUsername(updatedUser.getUsername());
        if (!updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(updatedUser.getPassword());  // Directly set the new password
        }
        return userRepository.save(existingUser);
    }

    // Delete a user (Admin action)
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Authenticate a user (for login)
    public boolean authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Compare the plain-text password
            return user.getPassword().equals(password);
        }

        return false;
    }
}
