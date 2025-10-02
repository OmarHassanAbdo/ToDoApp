package com.example.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tasks", "role"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username", nullable = false, length = 20)
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    // choose length according to hashing algorithm (bcrypt=60, argon2 may vary)
    @NotBlank
    @Size(max = 120)
    @Column(name = "password", nullable = false, length = 120)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // bidirectional: mappedBy = "user" (Task.user is the owner)
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-tasks")
    private Set<Task> tasks = new HashSet<>();

    // each user has one role (Many-to-One). EAGER or LAZY depending on needs.
    @ManyToOne(fetch = FetchType.EAGER) // set EAGER if you need role when loading user for auth
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    public User(String userName , String password , String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
