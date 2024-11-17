package crm.oilwhere.dto;

public class NewsletterDTO {
    private Long designId;
    private String name;
    private String content;
    private Long userId;

    // getters and setters
    public Long getId() {
        return designId;
    }

    public void setId(Long designId) {
        this.designId = designId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
