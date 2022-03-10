package se.iths.springloppis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.entity.RoleEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime registered;

    private final List<ItemEntity> items = new ArrayList<>();
    private final Set<RoleEntity> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }
}
