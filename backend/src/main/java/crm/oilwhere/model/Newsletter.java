package crm.oilwhere.model;
import java.util.Date;


public class Newsletter {
    private Long newsletterId;
    private String subject;
    private String body;
    private String contentType;    
    private double offerValue;    
    private Date dateSent;         

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
}
