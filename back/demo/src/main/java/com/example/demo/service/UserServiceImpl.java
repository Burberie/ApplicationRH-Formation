package com.example.demo.service;

import com.example.demo.entity.DBUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<DBUser> findAll() {
        return userRepository.findAll();
    }

    public void save(DBUser user) {
        userRepository.save(user);
    }
}