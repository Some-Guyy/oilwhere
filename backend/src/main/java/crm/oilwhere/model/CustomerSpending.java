package crm.oilwhere.model;

import jakarta.persistence.*;

/**
 * Represents a customer's total spending as stored in the "customer_spending_ranked" table.
 * <p>
 * This model holds information about a customer and their respective total spending.
 * It is used to track and rank customer spending within the system.
 * </p>
 */
@Entity
@Table(name = "customer_spending_ranked")
public class CustomerSpending {

    /** 
     * The unique identifier for a customer.
     * <p>
     * This field is the primary key and is automatically generated using the 
     * identity generation strategy.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    /** 
     * The total spending amount for the customer.
     * <p>
     * This field is non-nullable and maps to the "total_spending" column in the database.
     * </p>
     */
    @Column(name = "total_spending", nullable = false)
    private float totalSpending;

    /**
     * Default constructor for JPA.
     */
    public CustomerSpending() {}

    /**
     * Constructs a new {@code CustomerSpending} object with the specified customer ID and total spending.
     *
     * @param customerId the unique identifier for the customer
     * @param totalSpending the total spending amount for the customer
     */
    public CustomerSpending(Long customerId, float totalSpending) {
        this.customerId = customerId;
        this.totalSpending = totalSpending;
    }

    /**
     * Retrieves the unique identifier for the customer.
     *
     * @return the customer ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the total spending amount for the customer.
     *
     * @return the total spending amount
     */
    public float getTotalSpending() {
        return totalSpending;
    }

    /**
     * Sets the total spending amount for the customer.
     *
     * @param totalSpending the total spending amount to set
     */
    public void setTotalSpending(float totalSpending) {
        this.totalSpending = totalSpending;
    }
}
