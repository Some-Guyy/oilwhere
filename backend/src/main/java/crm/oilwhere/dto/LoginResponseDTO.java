package crm.oilwhere.dto;

import crm.oilwhere.model.Role;

/**
 * Data Transfer Object for login response details.
 * Contains the username, role, message, and user ID for a login response.
 */
public class LoginResponseDTO {
    
    /** The username associated with the login response. */
    private String username;

    /** The role of the user associated with the login response. */
    private Role role;

    /** A message providing additional information about the login response. */
    private String message;

    /** The unique identifier of the user. */
    private Long userId;

    /**
     * Constructs a new LoginResponseDTO with the specified username, role, message, and user ID.
     *
     * @param username the username associated with the login response
     * @param role the role of the user associated with the login response
     * @param message a message providing additional information about the login response
     * @param userId the unique identifier of the user
     */
    public LoginResponseDTO(String username, Role role, String message, Long userId) {
        this.username = username;
        this.role = role;
        this.message = message;
        this.userId = userId;
    }

    /**
     * Retrieves the username associated with the login response.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the login response.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the role of the user associated with the login response.
     *
     * @return the user's role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user associated with the login response.
     *
     * @param role the role to set for the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Retrieves the message associated with the login response.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the login response.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

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
}
