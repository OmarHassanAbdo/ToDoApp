package com.example.todo.entity;

import com.example.todo.entity.enums.AppRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, name = "role_name", nullable = false)
    private AppRole roleName;

    // Role is the parent; don't cascade REMOVE here (you don't want role deletions to remove users)
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore // avoid recursion and big payloads; prefer DTOs for API responses
    private Set<User> users = new HashSet<>();
}
