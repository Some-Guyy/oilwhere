package crm.oilwhere.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class SystemAdministrator extends User {

    // Constructor
    public SystemAdministrator(Long userId, String username, String password, String role) {
        super(userId, username, password, role);
    }

    public SystemAdministrator() {
        super();
    }
}
