package se.iths.springloppis.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springloppis.entity.RoleEntity;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.repository.RoleRepository;
import se.iths.springloppis.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    // good to set fields as final -> receive a compilation warning if field is not instantiated / null
    // "final" can then be removed if needed.

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // = new BCryptPasswordEncoder();

    // Vi vill aldrig instansiera data eller datatyper -> helst vill vi låter Spring Boot container dependency
    // manager skapa det åt oss. I SecurityConfig finns en PasswordEncoder metod markerad med @Bean
    // Vi jobbar mot interfaces (i konstruktor) istället.


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        // Demo: hardcoded role (available roles: USER, ADMIN)
        RoleEntity role = roleRepository.findByRole("ROLE_USER");    // sets "USER" role to all users by default
        userEntity.addRole(role);

        //check if user already exists in database
//        if(userRepository.findByUsername(userEntity.getUsername()) != null)
//            throw new Error()   // throw ambiguous error message - avoid too much, specific information -> avoid
//            giving
        // hackers info about which email addresses/names are already saved & they can attempt to hack

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
