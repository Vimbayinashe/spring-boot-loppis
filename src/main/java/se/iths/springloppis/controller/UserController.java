package se.iths.springloppis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity foundUser = userService.createUser(user);
        return new ResponseEntity<>(foundUser, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable Long id) {
        UserEntity foundUser = userService.findUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        Iterable<UserEntity> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
