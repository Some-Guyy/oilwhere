package crm.oilwhere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "newsletter")
public class Newsletter {
    @Id
    @Column(name="design_id")
    private Long designId;

    @Lob
    @Column(name="content", columnDefinition = "TEXT")
    private String content;

    // constructors
    public Newsletter() {}

    public Newsletter(Long designId, String content) {
        this.designId = designId;
        this.content = content;
    }

    // getters and Setters
    public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) { //dont really think we need this method but still put cause "best practices"
        this.designId = designId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Newsletter{" +
                "designId=" + designId +
                ", content='" + content + '\'' +
                '}';
    }
}
