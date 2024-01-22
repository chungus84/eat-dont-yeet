package com.eatdontyeet.recipebackend.repository;

import com.eatdontyeet.recipebackend.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findByUserId(String userId);

    Optional<Profile> findByUserName(String userName);

    @Transactional
    void deleteByUserIdAndRecipeId(String userId, Long recipeId);
}
