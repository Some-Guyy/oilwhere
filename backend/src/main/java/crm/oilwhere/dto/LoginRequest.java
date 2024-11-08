package crm.oilwhere.dto;

/**
 * Data Transfer Object for login request details.
 * Contains the username and password for user authentication.
 */
public class LoginRequest {
    private String username;
    private String password;

    /**
     * Retrieves the username for the login request.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the login request.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password for the login request.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the login request.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
