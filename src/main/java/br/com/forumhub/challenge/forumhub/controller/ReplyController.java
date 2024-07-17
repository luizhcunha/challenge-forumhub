package br.com.forumhub.challenge.forumhub.controller;

import br.com.forumhub.challenge.forumhub.domain.reply.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics/{idTopic}/replies")
@SecurityRequirement(name = "bearer-key")
public class ReplyController {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyService replyService;

    @PostMapping
    @Transactional
    public ResponseEntity registerReply(@RequestBody @Valid ReplyCreationDTO replyCreationDTO,
                                        @PathVariable Long idTopic,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Reply reply = replyService.createReplyFromDTO(replyCreationDTO, idTopic);
        replyRepository.save(reply);

        var uri = uriComponentsBuilder.path("/topics/{idTopic}/replies/{idReply}")
                .buildAndExpand(idTopic, reply.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ReplyListiningDTO(reply));
    }

    @GetMapping
    public ResponseEntity<Page<ReplyListiningDTO>> listReplies(@PathVariable Long idTopic,
                                                               @PageableDefault Pageable pageable) {
        Page<Reply> replies = replyService.findRepliesByTopicId(idTopic, pageable);
        Page<ReplyListiningDTO> response = replies.map(ReplyListiningDTO::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getReply(@PathVariable Long idTopic, @PathVariable Long id) {
        try {
            var replyDTO = replyService.getReply(idTopic, id);
            return ResponseEntity.ok(new ReplyListiningDTO(replyDTO));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resposta não encontrada");
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity updateReply(@PathVariable Long idTopic,
                                      @PathVariable Long id,
                                      @RequestBody @Valid ReplyUpdateDTO replyUpdateDTO) {
        var reply = replyService.updateReply(idTopic,id,replyUpdateDTO);
        return ResponseEntity.ok(new ReplyListiningDTO(reply));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleleReply(@PathVariable Long idTopic,
                                      @PathVariable Long id) {
        replyService.deleleReply(idTopic, id);
        return ResponseEntity.noContent().build();
    }
}
