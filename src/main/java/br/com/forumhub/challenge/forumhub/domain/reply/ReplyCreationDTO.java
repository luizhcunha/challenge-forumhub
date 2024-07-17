package br.com.forumhub.challenge.forumhub.domain.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyCreationDTO(
        @NotBlank(message = "Mensagem é obrigatorio")
        String message,
        @NotNull(message = "ID do autor é obrigatorio")
        Long idAuthor,
        @NotNull(message = "ID do tópico é obrigatorio")
        Long idTopic,
        @NotBlank(message = "Solução é obrigatorio")
        String solution
) {
}
