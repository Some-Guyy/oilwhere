package crm.oilwhere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crm.oilwhere.model.Customer;

// The following is the repository layer which interacts with the database
// It helps the CustomerService access the JPA repository and utilise functions provided by it. These include
// .findAll() -- Returns all the records in the database
// .findById(id) -- Returns the record in the database that fits the exact id
// .save(obj) -- Saves the given entity
// .existsById(id) -- Returns whether an entity with the given id exists.
// .deleteById(id) -- Deletes the entity with the given id.

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
