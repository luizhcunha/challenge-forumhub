package br.com.forumhub.challenge.forumhub.domain.topic;

import br.com.forumhub.challenge.forumhub.domain.course.Course;
import br.com.forumhub.challenge.forumhub.domain.course.CourseListingDTO;
import br.com.forumhub.challenge.forumhub.domain.reply.Reply;
import br.com.forumhub.challenge.forumhub.domain.user.User;
import br.com.forumhub.challenge.forumhub.domain.user.UserListingDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private StatusTopic status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Reply> replyList;

    public Topic(String title, String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.creationDate = LocalDateTime.now();
        this.status = StatusTopic.OPEN;
        this.author = author;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public StatusTopic getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(StatusTopic status) {
        this.status = status;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public UserListingDTO getAuthor() {
        return new UserListingDTO(author);
    }

    public CourseListingDTO getCourse() {
        return new CourseListingDTO(course);
    }

    public List<Reply> getReplyList() {
        return replyList;
    }
}