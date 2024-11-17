package crm.oilwhere.dto;

import crm.oilwhere.model.Role;

public class LoginResponseDTO {
    private String username;
    private Role role;
    private String message;
    private Long userId;

    // constructor
    public LoginResponseDTO(String username, Role role, String message, Long userId) {
        this.username = username;
        this.role = role;
        this.message = message;
        this.userId = userId;
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
