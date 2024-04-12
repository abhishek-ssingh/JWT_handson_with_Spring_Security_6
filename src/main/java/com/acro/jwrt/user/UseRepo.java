package com.acro.jwrt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UseRepo extends JpaRepository<User, Integer> {

    /**
     *
     * @param email find by email
     * @return a user if present else null
     * @author papa
     */
    Optional<User> findByEmail(String email);
}
