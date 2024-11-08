package crm.oilwhere.model;

import jakarta.persistence.*;

/**
 * Represents a filter entity for customer spending records.
 * Maps to the "customer_spending_ranked" table in the database.
 * Contains fields for the customer ID and their total spending.
 */
@Entity
@Table(name = "customer_spending_ranked")
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="total_spending", nullable = false)
    private float totalSpending;

    /**
     * Default constructor for Filter.
     */
    public Filter() {}

    /**
     * Constructs a Filter with the specified customer ID and total spending.
     *
     * @param customerId the unique identifier of the customer
     * @param totalSpending the total spending of the customer
     */
    public Filter(Long customerId, float totalSpending) {
        this.customerId = customerId;
        this.totalSpending = totalSpending;
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
     * Retrieves the total spending of the customer.
     *
     * @return the total spending of the customer
     */
    public float getTotalSpending(){
        return totalSpending;
    }

    /**
     * Sets the total spending of the customer.
     *
     * @param totalSpending the total spending amount to set
     */
    public void setTotalSpending(float totalSpending){
        this.totalSpending = totalSpending;
    }
}
