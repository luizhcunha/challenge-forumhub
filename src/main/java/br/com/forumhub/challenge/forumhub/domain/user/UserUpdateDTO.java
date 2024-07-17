package br.com.forumhub.challenge.forumhub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        String name
) {
}
