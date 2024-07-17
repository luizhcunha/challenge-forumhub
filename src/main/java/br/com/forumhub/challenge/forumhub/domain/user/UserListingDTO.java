package br.com.forumhub.challenge.forumhub.domain.user;

public record UserListingDTO(Long id, String name, String email) {
    public UserListingDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
