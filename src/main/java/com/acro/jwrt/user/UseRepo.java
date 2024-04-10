package com.acro.jwrt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UseRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String name);
}
