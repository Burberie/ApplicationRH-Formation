package com.example.demo.repositery;

import com.example.demo.entity.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositery extends JpaRepository<DBUser,Long> {
}
