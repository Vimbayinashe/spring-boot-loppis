package se.iths.springloppis.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.springloppis.entity.UserEntity;
import se.iths.springloppis.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("{id}")
    public Optional<UserEntity> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping()
    public Iterable<UserEntity> getAllUsers() {
        return userService.findAllUsers();
    }


}
