package crm.oilwhere.models.Customer;

import java.time.LocalDate;
import java.util.List;

import crm.oilwhere.models.Purchase.*;

public class Customer {
    //customer info
    private Long customerId;
    private String name;
    private String email;
    private String phone;

    //purchase information
    private double totalSpending;
    private LocalDate lastPurchaseDate;
    private List <Purchase> purchaseHistory;

    // Constructors
    public Customer() {
    }

    public Customer(Long customerId, String name, String email, String phone, double totalSpending, LocalDate lastPurchaseDate, List<Purchase> purchaseHistory) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.totalSpending = totalSpending;
        this.lastPurchaseDate = lastPurchaseDate;
        this.purchaseHistory = purchaseHistory;
    }


    // getters and setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalSpending() {
        return this.totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

    public LocalDate getLastPurchaseDate() {
        return this.lastPurchaseDate;
    }

    public void setLastPurchaseDate(LocalDate lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public List<Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<Purchase> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }
}
