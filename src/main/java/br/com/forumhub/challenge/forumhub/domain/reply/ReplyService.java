package br.com.forumhub.challenge.forumhub.domain.reply;

import br.com.forumhub.challenge.forumhub.domain.topic.Topic;
import br.com.forumhub.challenge.forumhub.domain.topic.TopicRepository;
import br.com.forumhub.challenge.forumhub.domain.user.User;
import br.com.forumhub.challenge.forumhub.domain.user.UserRepository;
import br.com.forumhub.challenge.forumhub.infra.exception.ValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ReplyService {
    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Reply createReplyFromDTO(ReplyCreationDTO replyCreationDTO, Long idTopic) {
        User user = userRepository.findById(replyCreationDTO.idAuthor())
                .orElseThrow(() -> new ValidationException("Autor não encontrado"));
        Topic topic = topicRepository.findById(idTopic)
                .orElseThrow(() -> new ValidationException("Tópico não encontrado"));

        return new Reply(replyCreationDTO, user, topic);
    }


    public Page<Reply> findRepliesByTopicId(Long idTopic, Pageable pageable) {
        Topic topic = topicRepository.findById(idTopic)
                .orElseThrow(() -> new ValidationException("Tópico não encontrado"));
        return replyRepository.findByTopic(topic, pageable);
    }

    public Reply getReply(Long idTopic, Long id) {
        Reply reply = replyRepository.findByTopicIdAndId(idTopic, id);
        if (reply == null) {
            throw new ValidationException("Resposta não encontrada");
        }
        return reply;
    }

    public Reply updateReply(Long idTopic, Long id, ReplyUpdateDTO replyUpdateDTO) {
        Reply reply = replyRepository.findByTopicIdAndId(idTopic, id);
        if (reply == null) {
            throw new ValidationException("Resposta não encontrada");
        } else {
            if (replyUpdateDTO.message() != null) {
                reply.setMessage(replyUpdateDTO.message());
            }
            if (replyUpdateDTO.solution() != null) {
                reply.setSolution(replyUpdateDTO.solution());
            }
        }
        return reply;
    }

    public void deleleReply(Long idTopic, Long id) {
        replyRepository.deleteByTopicIdAndId(idTopic, id);
    }
}
