package com.pochu.movieweb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from users u WHERE u.name = ?1")
    Optional<User> findUserByName(String name);
}
