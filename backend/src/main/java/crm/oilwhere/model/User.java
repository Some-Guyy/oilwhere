package crm.oilwhere.model;

import jakarta.persistence.*;

/**
 * Represents a user entity with credentials and role information.
 * Maps to the "user" table in the database.
 * Contains fields for user ID, username, password, and role.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="username", nullable = false, unique = true, length = 191)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) 
    @Column(name="role", nullable=false)
    private Role role;

    /**
     * Constructs a User with the specified ID, username, password, and role.
     *
     * @param userId the unique identifier of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role assigned to the user
     */
    public User(Long userId, String username, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Default constructor for User.
     */
    public User() {}

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return the user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param userId the user ID to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the role assigned to the user.
     *
     * @return the role of the user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role assigned to the user.
     *
     * @param role the role to set for the user
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
