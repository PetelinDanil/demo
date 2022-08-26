package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "person")
@Data
@Builder(toBuilder = false)
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=5, max=60)
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9.:\\- ]+$")
    private String login;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9.:\\- ]+$")
    private String password;

    private String firstName;

    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotBlank
    @NotNull
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_role",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roleSet;
}
