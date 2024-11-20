package crm.oilwhere.dto;

/**
 * Data Transfer Object for customer information.
 * Contains customer ID, name, and email details.
 */
public class CustomerDTO {

    /** The unique identifier of the customer. */
    private Long customerId;

    /** The name of the customer. */
    private String name;

    /** The email address of the customer. */
    private String email;

    /**
     * Retrieves the customer's unique identifier.
     *
     * @return the customer ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer's unique identifier.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the customer's name.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer's name.
     *
     * @param name the name to set for the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the customer's email address.
     *
     * @return the email of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email the email to set for the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
