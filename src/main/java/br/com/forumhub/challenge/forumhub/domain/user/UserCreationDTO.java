package br.com.forumhub.challenge.forumhub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreationDTO(
        @NotBlank
        String name,
        @Email
        @NotBlank
        String email,

        @Size(min = 6)
        @NotBlank
        String password
) {
}
