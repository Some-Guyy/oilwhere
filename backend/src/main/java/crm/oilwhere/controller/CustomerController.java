package crm.oilwhere.controller;

import java.util.Iterator;
import java.util.ArrayList;
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
import crm.oilwhere.dto.EmailManualDTO;
import crm.oilwhere.dto.EmailSegmentDTO;
import crm.oilwhere.model.Customer;
import crm.oilwhere.model.CustomerSpending;
import crm.oilwhere.service.CustomerService;
import crm.oilwhere.service.CustomerSegmentService;
import crm.oilwhere.service.NewsletterService;

// The following is the endpoints used to access the customer table
// It includes the following results
// /send-email -- Sends marketing email to the customer segment chosen(low, medium, high)
// /get-all -- Obtain all customer record in the customer table.
// /get/{customerId} -- Obtain specific customer record in the Customer table using the customers id
// /create -- Create a new customer record and put it inside the customer table
// /delete/{customerId} -- Delete specific customer record by the customerId
// /update/{customerId} -- Update specific customer record by the customerId 

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    
    private final CustomerSegmentService filterService;
    private final CustomerService customerService;
    private final NewsletterService newsletterService;


    public CustomerController(CustomerSegmentService filterService, CustomerService customerService, NewsletterService newsletterService) {
        this.filterService = filterService;
        this.customerService = customerService;
        this.newsletterService = newsletterService;
    }

    // Send email to manual input list of emails
    //POST request
    // Takes in an EmailManualDTO object, then sends all email addresses the newsletter
    // Returns the array of emails that was sent to
    @PostMapping("/send-manual")
    public ResponseEntity<String[]> sendManual(@RequestBody EmailManualDTO emailManualDTO) {

        String emails = emailManualDTO.getEmails();
        String subject = emailManualDTO.getSubject();
        String body = emailManualDTO.getBody();

        // split emails if there are multiple inputted
        String[] emailArr = emails.split(",");

        // loop through list of emails to send the newsletter to, name will be front part of email address, before the @ symbol
        for (String email: emailArr) {
            email = email.trim();
            String[] splitEmail = email.split("@");
            String name = splitEmail[0];
            newsletterService.sendMail(name, email, subject, body);
        }

        // return list of customer objects to see which customers supposed to receive the email
        return ResponseEntity.ok(emailArr);
    }

    // Send email to relevant segment
    //POST request
    // Takes in an EmailSegmentDTO object, then finds all customers whose spending fall in the segment specified, then sends an email to these customers address
    // Returns the Customer objects of the customers where email was sent
    @PostMapping("/send-monetary-segment")
    public ResponseEntity<List<Customer>> sendMonetarySegment(@RequestBody EmailSegmentDTO emailSegmentDTO) {
        
        String segment = emailSegmentDTO.getSegment();
        String subject = emailSegmentDTO.getSubject();
        String body = emailSegmentDTO.getBody();
        
        List<CustomerSpending> filter = new ArrayList<>();
        List<Long> customerIds = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        
        // depending on segment in request body call relevant filter
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

        // iterator to iterate through filter returned body
        // body consist of customerId and total spending
        Iterator<CustomerSpending> segmentIter = filter.iterator();

        // add customer ids to arraylist
        while (segmentIter.hasNext()) {
            customerIds.add(segmentIter.next().getCustomerId());           
        }

        // iterator to iterate through customer ids to get each email and name
        Iterator<Long> customerIdIter = customerIds.iterator();
        // add customer ids to arraylist
        while (customerIdIter.hasNext()) {
            Long customerId = customerIdIter.next();  
            try{
                Customer customer = customerService.getCustomerById(customerId); 
                customerList.add(customer);
            }catch(Exception e){
                e.printStackTrace();
            }
                    
        }

        // loop through customer list to send email to each customer

        // create iterator for customer list
        Iterator<Customer> customerDetailIter = customerList.iterator();
        while (customerDetailIter.hasNext()) {
            Customer customer = customerDetailIter.next();  
            try{
                String name = customer.getName();
                String email = customer.getEmail();
                newsletterService.sendMail(name, email, subject, body);
            }catch(Exception e){
                e.printStackTrace();
            }
                    
        }
        
        // return list of customer objects to see which customers supposed to receive the email
        return ResponseEntity.ok(customerList);
        
    }

    // get all customer records
    // GET request
    // Returns list of customer objects
    @GetMapping("/get-all")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        List<Customer> customer = customerService.getAllCustomer();
        return ResponseEntity.ok(customer);
    }

    // get specific customer using customerId
    // GET request
    // Returns Customer object with specific customerId
    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    // create a new customer
    // POST request
    // Takes in a CustomerDTO
    // Returns the Customer object created
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    // delete customer by id
    // DELETE request
    // Takes in a customerId which is the numerical id of the customer record
    // Returns message "Customer record successfully deleted" if success
    // Returns error 500 if unsuccessful
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        String result = customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(result);
    }

    // update customer
    // PUT request
    // Takes in a customerId and a customerDTO object which overrides the record of the selected customerId with the CustomerDTO object
    // Returns newly updated Customer object if successful
    // Returns error 500 if unsuccessful
    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(customer);
    }
}
