package crm.oilwhere.repository;

import crm.oilwhere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // for login
    Optional<User> findByUsername(String username);

}
