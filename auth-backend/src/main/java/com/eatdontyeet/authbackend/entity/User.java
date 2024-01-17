package com.eatdontyeet.authbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 59833214569778452L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userid")
    private String userId = UUID.randomUUID().toString();

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
