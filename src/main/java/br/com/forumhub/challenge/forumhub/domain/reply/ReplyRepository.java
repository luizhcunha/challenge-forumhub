package br.com.forumhub.challenge.forumhub.domain.reply;

import br.com.forumhub.challenge.forumhub.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Page<Reply> findByTopic(Topic topic, Pageable pageable);


    Reply findByTopicIdAndId(Long idTopic, Long id);

    void deleteByTopicIdAndId(Long idTopic, Long id);
}
