package br.com.forumhub.challenge.forumhub.controller;

import br.com.forumhub.challenge.forumhub.domain.topic.*;
import br.com.forumhub.challenge.forumhub.infra.exception.ValidationException;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity registerTopic(@RequestBody @Valid TopicCreationDTO topicCreationDTO, UriComponentsBuilder uriBuilder) {
        Topic topic = topicService.createTopicFromDTO(topicCreationDTO);
        topicRepository.save(topic);

        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicListiningDTO(topic));
    }

    @GetMapping
    public ResponseEntity<Page<TopicListiningDTO>> listAllTopics(@PageableDefault(sort = {"creationDate", "course"}) Pageable pageable) {
        var topics = topicRepository.findAll(pageable).map(TopicListiningDTO::new);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopic(@PathVariable Long id) {
        var topic = topicRepository.findById(id)
                .map(TopicListiningDTO::new)
                .orElseThrow(() -> new ValidationException("Tópico não encontrado"));
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO topicUpdateDTO) {
        var topic = topicService.updateTopic(topicUpdateDTO, id);
        return ResponseEntity.ok(new TopicListiningDTO(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
