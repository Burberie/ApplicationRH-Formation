package com.example.demo.repository;

import com.example.demo.entity.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<DBUser,Long> {
    DBUser save(DBUser user);

    DBUser findbyId(long id);

}
