package crm.oilwhere.models.Newsletter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crm.oilwhere.models.Customer.Customer;

public class Newsletter {
    private Long newsletterId;
    private String subject;
    private String body;
    private String contentType;    
    private double offerValue;    
    private Date dateSent;         
    private List<Customer> recipients = new ArrayList<>();    

    //constructors
    public Newsletter() {}

    public Newsletter(Long newsletterId, String subject, String body, String contentType, double offerValue, Date dateSent) {
        this.newsletterId = newsletterId;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
        this.offerValue = offerValue;
        this.dateSent = dateSent;
    }

    // Getters and Setters
    public Long getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public double getOfferValue() {
        return offerValue;
    }

    public void setOfferValue(double offerValue) {
        this.offerValue = offerValue;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    //other methods
    public void addRecipient(Customer customer) {
        recipients.add(customer);
    }

    public void removeRecipient(Customer customer) {
        recipients.remove(customer);
    }

    public void sendNewsLetter() {
        for (Customer customer : recipients) {
            // add other logic here i guess
            System.out.println("Sending newsletter to: " + customer.getEmail());
            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);
        }
    }

    //getters for recipients
    public List<Customer> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Customer> recipients) {
        this.recipients = recipients;
    }
}
