package br.com.forumhub.challenge.forumhub.domain.reply;

import br.com.forumhub.challenge.forumhub.domain.topic.Topic;
import br.com.forumhub.challenge.forumhub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "replies")
@Entity(name = "Reply")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String solution;


    public Reply(ReplyCreationDTO replyCreationDTO, User user, Topic topic) {
        this.message = replyCreationDTO.message();
        this.creationDate = LocalDateTime.now();
        this.solution = replyCreationDTO.solution();
        this.author = user;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public String getSolution() {
        return solution;
    }

    public Topic getTopic() {
        return topic;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
