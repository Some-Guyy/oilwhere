package crm.oilwhere.service;

import org.springframework.stereotype.Service;
import crm.oilwhere.dto.CustomerDTO;
import crm.oilwhere.model.Customer;
import crm.oilwhere.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing customer-related business logic and interacting with the database via the CustomerRepository.
 * Provides methods to retrieve, create, update, and delete customer records in the database.
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Constructs a new CustomerService with the specified CustomerRepository.
     *
     * @param customerRepository the repository used to interact with the customer data in the database
     */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves all customer records from the database.
     * Uses JPA repository's {@code findAll()} method.
     *
     * @return a list of all customer records
     */
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a specific customer record by its ID.
     * Uses JPA repository's {@code findById()} method.
     *
     * @param customerId the ID of the customer to retrieve
     * @return the customer record with the specified ID
     * @throws RuntimeException if the customer with the specified ID is not found
     */
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    /**
     * Creates a new customer record in the database based on the provided CustomerDTO.
     * Uses JPA repository's {@code save()} method.
     *
     * @param customerDTO the data transfer object containing details of the new customer
     * @return the created customer record
     */
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(customerDTO.getCustomerId());
        newCustomer.setName(customerDTO.getName());
        newCustomer.setEmail(customerDTO.getEmail());
        return customerRepository.save(newCustomer);
    }

    /**
     * Deletes a customer record from the database by its ID.
     * Uses JPA repository's {@code existsById()} and {@code deleteById()} methods.
     *
     * @param customerId the ID of the customer to delete
     * @return a confirmation message if the deletion is successful
     * @throws RuntimeException if the customer with the specified ID is not found
     */
    public String deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return "Customer record successfully deleted";
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    /**
     * Updates an existing customer record in the database with new details from the provided CustomerDTO.
     * Uses JPA repository's {@code findById()} and {@code save()} methods.
     *
     * @param customerId the ID of the customer to update
     * @param customerDTO the data transfer object containing updated customer details
     * @return the updated customer record
     * @throws RuntimeException if the customer with the specified ID is not found
     */
    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setCustomerId(customerDTO.getCustomerId());
            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setEmail(customerDTO.getEmail());
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
}
