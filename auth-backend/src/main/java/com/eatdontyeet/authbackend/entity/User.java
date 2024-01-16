package com.eatdontyeet.authbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "UserName cannot be blank")
    @NonNull
    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @NotBlank(message = "email cannot be blank")
    @NonNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "password cannot be blank")
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createdAt")
    private LocalDate createdAt = LocalDate.now();

}
