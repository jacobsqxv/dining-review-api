package com.aries.diningreview.repository;

import com.aries.diningreview.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByDisplayName(String displayName);
    Boolean existsByDisplayName(String displayName);
}
