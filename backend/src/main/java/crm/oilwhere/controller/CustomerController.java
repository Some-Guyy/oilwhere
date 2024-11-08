package crm.oilwhere.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.oilwhere.dto.CustomerDTO;
import crm.oilwhere.dto.EmailDTO;
import crm.oilwhere.model.Customer;
import crm.oilwhere.model.Filter;
import crm.oilwhere.service.CustomerService;
import crm.oilwhere.service.FilterService;
import crm.oilwhere.service.NewsletterService;

/**
 * Controller for managing customer records in the customer table.
 * Provides endpoints for CRUD operations on customer records and
 * for sending marketing emails to customer segments.
 */
@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    
    private final FilterService filterService;
    private final CustomerService customerService;
    private final NewsletterService newsletterService;

    /**
     * Constructs a new CustomerController with the specified services.
     *
     * @param filterService the service for managing customer filters
     * @param customerService the service for managing customer records
     * @param newsletterService the service for sending newsletters
     */
    public CustomerController(FilterService filterService, CustomerService customerService, NewsletterService newsletterService) {
        this.filterService = filterService;
        this.customerService = customerService;
        this.newsletterService = newsletterService;
    }

    /**
     * Sends marketing emails to a specified customer segment based on spending.
     *
     * @param emailDTO the email details, including the segment, subject, and body
     * @return a ResponseEntity containing a list of Customer objects representing customers who received the email
     */
    @PostMapping("/send-email")
    public ResponseEntity<List<Customer>> testRoute(@RequestBody EmailDTO emailDTO) {
        
        String segment = emailDTO.getSegment();
        String subject = emailDTO.getSubject();
        String body = emailDTO.getBody();
        
        List<Filter> filter = new ArrayList<>();
        List<Long> customerIds = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        
        // Depending on segment in request body, call relevant filter
        switch (segment) {
            case "high":
                filter = filterService.getHighValueCustomer();
                break;
            case "medium":
                filter = filterService.getMediumValueCustomer();
                break;
            case "low":
                filter = filterService.getLowValueCustomer();
                break;
            default:
                break;
        }

        // Add customer IDs from the filter to the customerIds list
        Iterator<Filter> segmentIter = filter.iterator();
        while (segmentIter.hasNext()) {
            customerIds.add(segmentIter.next().getCustomerId());           
        }

        // Retrieve customer details by ID and add to customerList
        Iterator<Long> customerIdIter = customerIds.iterator();
        while (customerIdIter.hasNext()) {
            Long customerId = customerIdIter.next();  
            try {
                Customer customer = customerService.getCustomerById(customerId); 
                customerList.add(customer);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        // Send emails to each customer in the customerList
        Iterator<Customer> customerDetailIter = customerList.iterator();
        while (customerDetailIter.hasNext()) {
            Customer customer = customerDetailIter.next();  
            try {
                String name = customer.getName();
                String email = customer.getEmail();
                newsletterService.sendMail(name, email, subject, body);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        // Return list of customer objects to see which customers received the email
        return ResponseEntity.ok(customerList);
    }

    /**
     * Retrieves all customer records from the customer table.
     *
     * @return a ResponseEntity containing a list of all Customer objects
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customer = customerService.getAllCustomer();
        return ResponseEntity.ok(customer);
    }

    /**
     * Retrieves a specific customer record by their customer ID.
     *
     * @param customerId the ID of the customer to retrieve
     * @return a ResponseEntity containing the Customer object with the specified ID
     */
    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    /**
     * Creates a new customer record in the customer table.
     *
     * @param customerDTO the data transfer object containing the customer details
     * @return a ResponseEntity containing the created Customer object
     */
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    /**
     * Deletes a customer record by their customer ID.
     *
     * @param customerId the ID of the customer to delete
     * @return a ResponseEntity containing a message indicating the result of the deletion
     */
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        String result = customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(result);
    }

    /**
     * Updates an existing customer record by their customer ID with the details provided in CustomerDTO.
     *
     * @param customerId the ID of the customer to update
     * @param customerDTO the data transfer object containing the updated customer details
     * @return a ResponseEntity containing the updated Customer object
     */
    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(customer);
    }
}
