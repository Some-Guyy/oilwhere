package crm.oilwhere.model;

import java.time.LocalDate;
import jakarta.persistence.*;

/**
 * Represents a purchase record entity that maps to the "purchase_history" table in the database.
 * Contains details about each purchase including purchase ID, sale date, sale type, channel,
 * customer ID, ZIP code, shipping method, product details, quantity, and pricing.
 */
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
    private Integer customerId;

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

    /**
     * Default constructor for Purchase.
     */
    public Purchase() {}

    /**
     * Constructs a Purchase with specified fields.
     *
     * @param purchaseId the unique identifier for the purchase
     * @param saleDate the date of the sale
     * @param saleType the type of sale
     * @param digital the channel through which the sale was made
     * @param customerId the ID of the customer who made the purchase
     * @param zipcode the ZIP code of the customer's address
     * @param shippingMethod the method of shipping used for the purchase
     * @param product the name of the product
     * @param variant the variant number of the product
     * @param quantity the quantity of the product purchased
     * @param price the unit price of the product
     * @param productPrice the total price for the quantity of products purchased
     */
    public Purchase(Long purchaseId, LocalDate saleDate, String saleType, String digital, Integer customerId, Integer zipcode, String shippingMethod, String product, Integer variant, Integer quantity, float price, float productPrice) {
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

    /**
     * Retrieves the unique identifier of the purchase.
     *
     * @return the purchase ID
     */
    public Long getPurchaseId() {
        return purchaseId;
    }

    /**
     * Sets the unique identifier of the purchase.
     *
     * @param purchaseId the purchase ID to set
     */
    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * Retrieves the sale date of the purchase.
     *
     * @return the sale date
     */
    public LocalDate getSaleDate() {
        return saleDate;
    }

    /**
     * Sets the sale date of the purchase.
     *
     * @param saleDate the sale date to set
     */
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * Retrieves the type of sale.
     *
     * @return the sale type, or "null" if not specified
     */
    public String getSaleType() {
        if (saleType == null) {
            return "null";
        }
        return saleType;
    }

    /**
     * Sets the type of sale.
     *
     * @param saleType the sale type to set
     */
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    /**
     * Retrieves the channel through which the sale was made.
     *
     * @return the sale channel
     */
    public String getDigital() {
        return digital;
    }

    /**
     * Sets the channel through which the sale was made.
     *
     * @param digital the sale channel to set
     */
    public void setDigital(String digital) {
        this.digital = digital;
    }

    /**
     * Retrieves the customer ID associated with the purchase.
     *
     * @return the customer ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID associated with the purchase.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the ZIP code of the customer's address.
     *
     * @return the ZIP code
     */
    public Integer getZipcode() {
        return zipcode;
    }

    /**
     * Sets the ZIP code of the customer's address.
     *
     * @param zipcode the ZIP code to set
     */
    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Retrieves the method of shipping used for the purchase.
     *
     * @return the shipping method
     */
    public String getShippingMethod() {
        return shippingMethod;
    }

    /**
     * Sets the method of shipping used for the purchase.
     *
     * @param shippingMethod the shipping method to set
     */
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * Retrieves the name of the product purchased.
     *
     * @return the product name
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the name of the product purchased.
     *
     * @param product the product name to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Retrieves the variant number of the product.
     *
     * @return the variant number
     */
    public Integer getVariant() {
        return variant;
    }

    /**
     * Sets the variant number of the product.
     *
     * @param variant the variant number to set
     */
    public void setVariant(Integer variant) {
        this.variant = variant;
    }

    /**
     * Retrieves the quantity of the product purchased.
     *
     * @return the quantity purchased
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product purchased.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the unit price of the product.
     *
     * @return the unit price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the unit price of the product.
     *
     * @param price the unit price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Retrieves the total price for the quantity of products purchased.
     *
     * @return the total product price
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * Sets the total price for the quantity of products purchased.
     *
     * @param productPrice the total product price to set
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
