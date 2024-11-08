package crm.oilwhere.model;

import jakarta.persistence.*;

/**
 * Represents a customer entity with details about each customer.
 * Maps to the "customer" table in the database.
 * Contains fields for the customer ID, name, and email.
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    /**
     * Default constructor for Customer.
     */
    public Customer() {}

    /**
     * Constructs a Customer with the specified ID, name, and email.
     *
     * @param customerId the unique identifier of the customer
     * @param name the name of the customer
     * @param email the email address of the customer
     */
    public Customer(Long customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return the customer ID
     */
    public Long getCustomerId(){
        return customerId;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the name to set for the customer
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Retrieves the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmail(){
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email the email address to set for the customer
     */
    public void setEmail(String email){
        this.email = email;
    }
}
