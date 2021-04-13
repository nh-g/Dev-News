package se.sdaproject;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;


    private String body;
    private String authorName;

    @ManyToOne()
    private Article articleCommented;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Article getArticleCommented() {
        return articleCommented;
    }

    public void setArticleCommented(Article articleCommented) {
        this.articleCommented = articleCommented;
    }
}