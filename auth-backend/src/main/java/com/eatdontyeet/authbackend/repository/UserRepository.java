package com.eatdontyeet.authbackend.repository;

import com.eatdontyeet.authbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserName(String userName);

}
