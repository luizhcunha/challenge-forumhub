package br.com.forumhub.challenge.forumhub.controller;

import br.com.forumhub.challenge.forumhub.domain.user.*;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid UserCreationDTO userCreationDTO, UriComponentsBuilder uriBuilder) {
        User user = new User(userCreationDTO);
        userService.registerUser(user);


        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserListingDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserListingDTO>> listUsers(@PageableDefault Pageable pageable ) {
        var page = userRepository.findAll(pageable).map(UserListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado."));
        return ResponseEntity.ok(new UserListingDTO(user));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        var user = userService.updateTopic(userUpdateDTO, id);
        return ResponseEntity.ok(new UserListingDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
