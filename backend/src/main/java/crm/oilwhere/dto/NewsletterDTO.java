package crm.oilwhere.dto;

public class NewsletterDTO {
    private Long designId;
    private String content;

    // getters and setters
    public Long getId() {
        return designId;
    }

    public void setId(Long designId) {
        this.designId = designId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
