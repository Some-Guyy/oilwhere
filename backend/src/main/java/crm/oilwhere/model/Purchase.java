package crm.oilwhere.model;

import java.time.LocalDate;

public class Purchase {
    private Long purchaseId;
    private String saleType;
    private double value;
    private LocalDate purchaseDate;

    //constructors
    public Purchase() {}

    public Purchase(Long purchaseId, String saleType, double value, LocalDate purchaseDate) {
        this.purchaseId = purchaseId;
        this.saleType = saleType;
        this.value = value;
        this.purchaseDate = purchaseDate;
    }

    //getters and setters
    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
