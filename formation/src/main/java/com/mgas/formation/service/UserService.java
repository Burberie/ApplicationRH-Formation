package com.mgas.formation.service;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.exception.UserNotFoundException;
import com.mgas.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
    public DBUser getId(Long id) { return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id)); }

    public DBUser saveUser(@RequestBody DBUser user) {
        return userRepository.save(user);
    }

    public List<DBUser> saveUsers(@RequestBody List<DBUser> users) {
        return userRepository.saveAll(users);
    }

    public DBUser updateUser(@RequestBody DBUser user, @PathVariable Long id) {
        return userRepository.findById(id).map(u -> {
            u.setEmail(user.getEmail());
            u.setPassword(user.getPassword());
            u.setLastname(user.getLastname());
            u.setFirstname(user.getFirstname());
            u.setUsername(user.getUsername());
            u.setRegister_num(user.getRegister_num());
            u.setRegister_date(user.getRegister_date());
            u.setSeniority_date(user.getSeniority_date());
            u.setEnd_date(user.getEnd_date());
            u.setRole(user.getRole());
            u.setService(user.getService());
            u.setContract(user.getContract());
            return userRepository.save(u);
        }).orElseGet(() -> userRepository.save(user));
    }

    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
