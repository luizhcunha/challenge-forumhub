package br.com.forumhub.challenge.forumhub.domain.reply;

public record ReplyListiningDTO(Long id,
                                String message,
                                String solution,
                                Long authorId,
                                Long topicId) {
    public ReplyListiningDTO(Reply reply) {
        this(reply.getId(), reply.getMessage(), reply.getSolution(), reply.getAuthor().getId(), reply.getTopic().getId());
    }
}
