package crm.oilwhere.service;

import org.springframework.stereotype.Service;

import crm.oilwhere.model.Customer;
import crm.oilwhere.model.CustomerDTO;
import crm.oilwhere.repository.CustomerRepository;

import java.util.List;

import java.util.Optional;



@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // constructor
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // get all
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    // get name, email using customerId
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    // create new customer
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
    public String deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return "Customer record successfully deleted";
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    // update customer using customerId
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
