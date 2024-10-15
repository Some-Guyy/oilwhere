package crm.oilwhere.service;

import crm.oilwhere.dto.UserDTO;
import crm.oilwhere.model.MarketingTeam;
import crm.oilwhere.model.SalesTeam;
import crm.oilwhere.model.SystemAdministrator;
import crm.oilwhere.model.User;
import crm.oilwhere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users service (admin only)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // retrieve user by id (keep here for now, incase FE needs to get specific userid)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // create new user
    public User createUser(UserDTO userDTO) {
        User newUser;
        
        // Determine the user type based on the role
        switch (userDTO.getRole()) {
            case "MARKETING":
                newUser = new MarketingTeam();
                break;
            case "SALES":
                newUser = new SalesTeam();
                break;
            case "ADMIN":
                newUser = new SystemAdministrator();
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }

        // Set common fields
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole(userDTO.getRole());

        // Save the user
        return userRepository.save(newUser);
    }

    // update user details (change username, password or role except for admins)
    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Prevent ADMIN users from updating other ADMIN users
            if ("ADMIN".equals(existingUser.getRole()) && "ADMIN".equals(userDTO.getRole())) {
                throw new RuntimeException("ADMIN users cannot update other ADMIN users.");
            }

            // Update common fields (username, password)
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setPassword(userDTO.getPassword());

            // Update the role without deleting the old user
            existingUser.setRole(userDTO.getRole());

            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // delete user
    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted";
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // login service
    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // compare password
            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty(); //empty if auth fails
    }
}
