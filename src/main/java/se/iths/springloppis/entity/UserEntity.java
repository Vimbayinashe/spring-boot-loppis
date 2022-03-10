package se.iths.springloppis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(unique = true)   // requires interface implementations -> check for constraints in UserService?
    private String username;
    private String email;

    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)   - lazy alternative to DTO Mapping (Not Advisable)
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime registered;       // in JSON payload "registered": "2022-10-03 21:30"

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ItemEntity> items = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RoleEntity> roles = new HashSet<>();


    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    @PrePersist     //körs precis innan något sparas till databas
    public void getCurrentDate() {
        setRegistered(LocalDateTime.now());
    }

    public void addRole(RoleEntity role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(RoleEntity role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addItem(ItemEntity item) {
        items.add(item);
        item.setUser(this);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //@JsonIgnore      // = @JsonBTransient -> Java EE använder JsonB men Spring boot använder Jackson
    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}
