package se.iths.springloppis.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.repository.UserRepository;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public void deleteUser(Long id) {
        UserEntity foundUser = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));
        userRepository.deleteById(foundUser.getId());
    }

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Iterable<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
