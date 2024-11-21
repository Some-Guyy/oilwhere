package crm.oilwhere.dto;

/**
 * Data Transfer Object for user details.
 * Contains information about the user, including username, password, and role.
 */
public class UserDTO {
    /**
     * The username of the user.
     * <p>
     * This is typically used as a unique identifier for user login and other operations.
     * </p>
     */
    private String username;

    /**
     * The password of the user.
     * <p>
     * This is used for authentication. Ensure it is stored securely and never exposed directly.
     * </p>
     */
    private String password;

    /**
     * The role of the user.
     * <p>
     * This indicates the user's access level or permissions in the system.
     * Common roles include "ADMIN", "MARKETING", and "SALES".
     * </p>
     */
    private String role;

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
     * Retrieves the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
