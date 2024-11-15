package crm.oilwhere.model;

import java.time.LocalDate;
import jakarta.persistence.*;

// The following is the Purchase object to hold each purchase record
// The Purchase object is for each record in the purchase_history table 
// It contains the purchase_id, sale_date, sale_type, digital, customer_id, zipcode, shipping_method, product, variant, quantity, price, product_price
// purchase_id: Id of each purchase record, it is the identifier
// sale_date: Date of the purchase
// sale_type: Type of sale
// digital: Which channel was used to do the sale
// customer_id: Id of the customer
// zipcode: Zipcode of the customer's address
// shipping_method: Method of shipping done
// product: Product name
// variant: Variation number of the product
// quantity: Number of product bought
// price: Price of individual product
// product_price: Total price of the products in the purchase record 

@Entity
@Table(name = "purchase_history")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purchase_id")
    private Long purchaseId;

    @Column(name="sale_date", nullable = false)
    private LocalDate saleDate;

    @Column(name="sale_type", length = 255)
    private String saleType;

    @Column(name="digital", length = 255)
    private String digital;

    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Column(name="zipcode", length = 6)
    private Integer zipcode;

    @Column(name="shipping_method", length = 255)
    private String shippingMethod;

    @Column(name="product", length = 255)
    private String product;

    @Column(name="variant", nullable = false)
    private Integer variant;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @Column(name="price", nullable = false)
    private float price;

    @Column(name="product_price", nullable = false)
    private float productPrice;

    // Default constructor
    public Purchase() {}

    // Constructor with all fields
    public Purchase(Long purchaseId, LocalDate saleDate, String saleType, String digital, Long customerId, Integer zipcode, String shippingMethod, String product, Integer variant, Integer quantity, float price, float productPrice) {
        this.purchaseId = purchaseId;
        this.saleDate = saleDate;
        this.saleType = saleType;
        this.digital = digital;
        this.customerId = customerId;
        this.zipcode = zipcode;
        this.shippingMethod = shippingMethod;
        this.product = product;
        this.variant = variant;
        this.quantity = quantity;
        this.price = price;
        this.productPrice = productPrice;
    }

    // Getters and setters
    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getSaleType() {
        if (saleType == null) {
            return "null";
        }
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getVariant() {
        return variant;
    }

    public void setVariant(Integer variant) {
        this.variant = variant;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

}
