package com.mgas.formation.service;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<DBUser> getAll(){
        return userRepository.findAll();
    }

    public DBUser saveUser(@RequestBody DBUser user) {
        return userRepository.save(user);
    }
}
