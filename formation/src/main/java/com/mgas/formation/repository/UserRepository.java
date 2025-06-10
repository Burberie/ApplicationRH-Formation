package com.mgas.formation.repository;

import com.mgas.formation.entity.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DBUser, Long> {
    Optional<DBUser> findByUsername(String username);
    Optional<DBUser> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}