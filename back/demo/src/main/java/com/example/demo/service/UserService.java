package com.example.demo.service;

import com.example.demo.entity.DBUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    public UserRepository repository;

    public List<DBUser> findAll() {
        return repository.findAll();
    }

    public void save(DBUser user) {
        repository.save(user);
    }
}