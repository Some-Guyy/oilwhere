package crm.oilwhere.service;

import org.springframework.stereotype.Service;

import crm.oilwhere.dto.CustomerDTO;
import crm.oilwhere.model.Customer;
import crm.oilwhere.repository.CustomerRepository;

import java.util.List;

import java.util.Optional;

// CustomerService is the service layer connected to the CustomerRepository. It contains all the business logic for Customer table. It utilises the JPA repository to query from the database.
// consists of the following functions
// getAllCustomer() -- retrieve all customer records
// getCustomerById(customerId) -- retrieve customer record that has specific customerId
// createCustomer(customerDTO) -- create a new customer record with the customerDTO object
// deleteCustomer(customerId) -- delete the customer record with the specidic customerId
// updateCustomer(customerId, customerDTO) -- update the customer record with the specified customerId and replace with customerDTO object 

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // constructor to create CustomerService object
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // get all customer 
    // Uses JPA repository findAll function to retrieve all customer records
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    // get name, email using customerId
    // Used JPA repository findById function to retrieve record that has specific customerId
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // create new customer
    // Create a new customer object
    // Set the customerId, name and email from the customerDTO object using getCustomerId, getName, getEmail
    // Used JPA repository to save the record after creating the new customer object
    public Customer createCustomer(CustomerDTO customerDTO) {
    
        // Create a new Customer instance
        Customer newCustomer = new Customer();
        
        newCustomer.setCustomerId(customerDTO.getCustomerId());
        newCustomer.setName(customerDTO.getName());
        newCustomer.setEmail(customerDTO.getEmail());
    
        // Save the new customer to the database
        return customerRepository.save(newCustomer);
    }

    // delete customer using customerId
    // Used JPA repository's existsById to find if the record with specified customerId exists
    // Used JPA repository's deleteById to delete the record with specified customerId exists
    public String deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return "Customer record successfully deleted";
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    // update customer using customerId
    // Used JPA repository's findById to find the record with specified customerId and isPresent to check if it exists
    // Obtain the record's customer object
    // Set the customerId, name and email from the customerDTO object using getCustomerId, getName, getEmail
    // 
    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
    Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

    if (optionalCustomer.isPresent()) {
        Customer existingCustomer = optionalCustomer.get();

        // Update any relevant field
        existingCustomer.setCustomerId(customerDTO.getCustomerId());
        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());

        // Save the updated customer
        return customerRepository.save(existingCustomer);
    } else {
        throw new RuntimeException("Customer not found");
    }
    }


}
