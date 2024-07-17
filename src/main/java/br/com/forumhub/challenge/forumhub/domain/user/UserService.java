package br.com.forumhub.challenge.forumhub.domain.user;

import br.com.forumhub.challenge.forumhub.infra.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void deleteTopic(Long id) {
        var user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }
    }

    public User updateTopic(UserUpdateDTO userUpdateDTO, Long id) {
        var user = userRepository.getReferenceById(id);
        if(userUpdateDTO.name() != null) {
            user.setName(userUpdateDTO.name());
        }
        return user;
    }

    public User registerUser(User user) {
        CheckDuplicityOfEmail(user);
        userRepository.save(user);
        return user;
    }

    public boolean CheckDuplicityOfEmail(User user) {
        var emailExistent = userRepository.existsByEmail(user.getEmail());

        if(emailExistent) {
            throw new ValidationException("O email já cadastrado, faça login para acessar conta.");
        }

        return false;
    }
}
