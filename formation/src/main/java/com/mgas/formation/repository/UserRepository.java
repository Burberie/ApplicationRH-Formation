package com.mgas.formation.repository;

import com.mgas.formation.entity.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DBUser, Long> {

}
