package crm.oilwhere.dto;

import crm.oilwhere.model.Role;

/**
 * Data Transfer Object for login response details.
 * Contains the username, role, and message for a login response.
 */
public class LoginResponseDTO {
    private String username;
    private Role role;
    private String message;

    /**
     * Constructs a new LoginResponseDTO with the specified username, role, and message.
     *
     * @param username the username associated with the login response
     * @param role the role of the user associated with the login response
     * @param message a message providing additional information about the login response
     */
    public LoginResponseDTO(String username, Role role, String message) {
        this.username = username;
        this.role = role;
        this.message = message;
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
}
