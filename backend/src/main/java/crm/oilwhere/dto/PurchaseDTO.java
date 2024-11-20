package crm.oilwhere.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object for purchase details.
 * Contains information related to a purchase, including sale date, type, customer details,
 * product details, and pricing information.
 */
public class PurchaseDTO {


    /** The date when the sale occurred. */
    private LocalDate saleDate;

    /** The type of sale, e.g., online or in-store. */
    private String saleType;

    /** Indicates if the purchase was digital (e.g., a download) or physical. */
    private String digital;

    /** The unique identifier of the customer who made the purchase. */
    private Long customerId;

    /** The zip code of the customer's shipping address. */
    private Integer zipcode;

    /** The method used for shipping the purchased product, e.g., standard or express. */
    private String shippingMethod;

    /** The name or description of the product purchased. */
    private String product;

    /** The specific variant of the product purchased, such as color or size. */
    private Integer variant;

    /** The quantity of the product purchased. */
    private Integer quantity;

    /** The total price paid for the purchase, including all items and charges. */
    private float price;

    /** The price of a single unit of the product. */
    private float productPrice;

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
     * Retrieves the type of sale (e.g., wholesale or retail).
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
     * Sets the type of sale (e.g., wholesale or retail).
     *
     * @param saleType the sale type to set
     */
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    /**
     * Retrieves the digital status of the sale (e.g., online or offline).
     *
     * @return the digital status
     */
    public String getDigital() {
        return digital;
    }

    /**
     * Sets the digital status of the sale (e.g., online or offline).
     *
     * @param digital the digital status to set
     */
    public void setDigital(String digital) {
        this.digital = digital;
    }

    /**
     * Retrieves the ID of the customer who made the purchase.
     *
     * @return the customer ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer who made the purchase.
     *
     * @param customerId the customer ID to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the ZIP code associated with the purchase.
     *
     * @return the ZIP code
     */
    public Integer getZipcode() {
        return zipcode;
    }

    /**
     * Sets the ZIP code associated with the purchase.
     *
     * @param zipcode the ZIP code to set
     */
    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Retrieves the shipping method for the purchase.
     *
     * @return the shipping method
     */
    public String getShippingMethod() {
        return shippingMethod;
    }

    /**
     * Sets the shipping method for the purchase.
     *
     * @param shippingMethod the shipping method to set
     */
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * Retrieves the product name associated with the purchase.
     *
     * @return the product name
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the product name associated with the purchase.
     *
     * @param product the product name to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Retrieves the variant of the product.
     *
     * @return the product variant
     */
    public Integer getVariant() {
        return variant;
    }

    /**
     * Sets the variant of the product.
     *
     * @param variant the product variant to set
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
     * Retrieves the total price of the product based on the quantity.
     *
     * @return the total product price
     */
    public float getProductPrice() {
        return productPrice;
    }

    /**
     * Sets the total price of the product based on the quantity.
     *
     * @param productPrice the total product price to set
     */
    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
