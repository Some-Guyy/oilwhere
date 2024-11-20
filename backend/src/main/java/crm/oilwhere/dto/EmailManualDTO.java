package crm.oilwhere.dto;
import java.util.ArrayList;

public class EmailManualDTO {

    private ArrayList<String> emailList;
    private String subject;
    private String body;

    // getters and setters
    public ArrayList<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
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
}
