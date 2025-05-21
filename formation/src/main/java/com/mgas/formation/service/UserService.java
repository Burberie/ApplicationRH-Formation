package com.mgas.formation.service;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.exception.UserNotFoundException;
import com.mgas.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
    public DBUser getId(Long id) { return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id)); }

    public DBUser saveUser(@RequestBody DBUser user) {
        return userRepository.save(user);
    }

    public DBUser replaceUser(@RequestBody DBUser user, @PathVariable Long id) {
        return userRepository.findById(id).map(u -> {
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setLastname(user.getLastname());
            u.setFirstname(user.getFirstname());
            u.setRole(user.getRole());
            return userRepository.save(u);
        }).orElseGet(() -> {
           return userRepository.save(user);
        });
    }

    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
