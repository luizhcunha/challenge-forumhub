package br.com.forumhub.challenge.forumhub.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TopicCreationDTO(
        @NotBlank(message = "Titulo é obrigatorio")
        String title,
        @NotBlank(message = "Mensagem é obrigatorio")
        String message,
        @NotNull(message = "ID do autor é obrigatorio")
        Long idAuthor,
        @NotNull(message = "ID do curso é obrigatorio")
        Long idCourse
) implements TopicDTO {
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
