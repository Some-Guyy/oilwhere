package crm.oilwhere.model;

import jakarta.persistence.*;

// The following is the Filter object to hold each customer and their respective total spending
// The Filter object is for each record in the customer_spending_ranked table
// It contains the customer_id, total_spending
// customer_id: Id of each customer, it is the identifier
// totalSpending: Total spending of each customer

@Entity
@Table(name = "customer_spending_ranked")
public class CustomerSpending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @Column(name="total_spending", nullable = false)
    private float totalSpending;
    
    // constructors
    public CustomerSpending() {}

    public CustomerSpending(Long customerId, float totalSpending) {
        this.customerId = customerId;
        this.totalSpending = totalSpending;
    }

    // getters and setters
    public Long getCustomerId(){
        return customerId;
    }

    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    public float getTotalSpending(){
        return totalSpending;
    }

    public void setTotalSpending(float totalSpending){
        this.totalSpending = totalSpending;
    }
}
