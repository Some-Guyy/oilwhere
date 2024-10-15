package crm.oilwhere.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "Role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserID")
    private Long userId;

    @Column(name="Username", nullable = false, unique = true, length = 191)
    private String username;

    @Column(name="Password", nullable = false)
    private String password;

    @Column(name="Role", insertable=false, updatable=false)
    private String role;

    //constructors
    public User(Long userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {}

    //getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
