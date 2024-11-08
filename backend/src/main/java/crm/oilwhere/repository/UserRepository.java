package crm.oilwhere.repository;

import crm.oilwhere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing user entities in the database.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods for User entities.
 * 
 * <p>Commonly used methods from JpaRepository include:</p>
 * <ul>
 *     <li>{@code findAll()} - Retrieves all user records in the database.</li>
 *     <li>{@code findById(id)} - Retrieves a user record by its unique ID.</li>
 *     <li>{@code save(entity)} - Saves the specified user entity to the database.</li>
 *     <li>{@code existsById(id)} - Checks if a user record with the given ID exists.</li>
 *     <li>{@code deleteById(id)} - Deletes the user record with the specified ID.</li>
 * </ul>
 * 
 * <p>Custom query methods:</p>
 * <ul>
 *     <li>{@code findByUsername(username)} - Retrieves a user record based on the username, used primarily for login.</li>
 * </ul>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user record based on the specified username.
     * Primarily used for login to find and authenticate users.
     *
     * @param username the username of the user
     * @return an Optional containing the User if found, or empty if not
     */
    Optional<User> findByUsername(String username);
}
