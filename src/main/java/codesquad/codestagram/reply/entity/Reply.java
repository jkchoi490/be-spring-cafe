package codesquad.codestagram.reply.entity;

import codesquad.codestagram.article.domain.Article;
import codesquad.codestagram.user.domain.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    //작성자
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writer;

    //내용 -> 길이가 긴 문자열(columnDefinition = "TEXT")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //게시글 아이디
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    //댓글 작성시간
    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private String createdDate;

    //댓글 수정시간
    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    //기본 생성자
    public Reply(){}

    //생성자
    public Reply(String content, Article article, User writer){
        this.content=content;
        this.article=article;
        this.writer=writer;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public Long getReplyId() {
        return replyId;
    }

    public User getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Article getArticle() {
        return article;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    //댓글 수정
    public void updateContent(String content){
        this.content=content;
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
