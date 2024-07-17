package br.com.forumhub.challenge.forumhub.domain.topic;

import br.com.forumhub.challenge.forumhub.domain.course.Course;
import br.com.forumhub.challenge.forumhub.domain.course.CourseRepository;
import br.com.forumhub.challenge.forumhub.domain.user.User;
import br.com.forumhub.challenge.forumhub.domain.user.UserRepository;
import br.com.forumhub.challenge.forumhub.infra.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public User getAuthorById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
    }

    public Topic createTopicFromDTO(TopicCreationDTO topicCreationDTO) {
        try {
            var checkDuplicityOfTopic = CheckDuplicityOfTopic(topicCreationDTO);
            if(!checkDuplicityOfTopic) {
                User author = getAuthorById(topicCreationDTO.idAuthor());
                Course course = getCourseById(topicCreationDTO.idCourse());

                return new Topic(topicCreationDTO.title(), topicCreationDTO.message(), author, course);
            }
        } catch (ValidationException exception) {
            throw new IllegalStateException(exception.getMessage());
        }
        return null;
    }

    public boolean CheckDuplicityOfTopic(TopicDTO topic) {
        var titleExistent = topicRepository.existsByTitle(topic.getTitle());
        var messageExistent = topicRepository.existsByMessage(topic.getMessage());

        if(titleExistent || messageExistent) {
            throw new ValidationException("O tópico com o título ou mensagem fornecida já existe.");
        }

        return false;
    }

    public Topic updateTopic(TopicUpdateDTO topicUpdateDTO, Long idTopic) {
        var topic = topicRepository.getReferenceById(idTopic);
        var checkDuplicityOfTopic = CheckDuplicityOfTopic(topicUpdateDTO);

        if(!checkDuplicityOfTopic) {
            if (topicUpdateDTO.getTitle() != null) {
                topic.setTitle(topicUpdateDTO.getTitle());
            }

            if (topicUpdateDTO.getMessage() != null) {
                topic.setMessage(topicUpdateDTO.getMessage());
            }
        }

        return topic;
    }

    public void deleteTopic(Long idTopic) {
        var topic = topicRepository.findById(idTopic);

        if(topic.isPresent()) {
            topicRepository.deleteById(idTopic);
        } else {
            throw new EntityNotFoundException("Tópico não encontrado.");
        }
    }
}
