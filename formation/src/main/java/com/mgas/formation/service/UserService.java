package com.mgas.formation.service;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.exception.UserNotFoundException;
import com.mgas.formation.mapper.UserMapper;
import com.mgas.formation.model.User;
import com.mgas.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    private UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        for (DBUser userDB : userRepository.findAll()) {
            users.add(userMapper.dbUserToUser(userDB));
        }
        return users;
    }
    public User getId(Long id) {
        DBUser user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.dbUserToUser(user);
    }

    public User findUserAccountByEmail(String email) {
        DBUser user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return userMapper.dbUserToUser(user);
    }

    public User saveUser(@RequestBody User user) {
        DBUser userDB = userMapper.userToDBUser(user);
        return userMapper.dbUserToUser(userRepository.save(userDB));
    }

    public List<User> saveUsers(@RequestBody List<User> userList) {
        List<DBUser> usersDB = new ArrayList<>();
        for (User user : userList) {
            usersDB.add(userMapper.userToDBUser(user));
        }
        List<User> users = new ArrayList<>();
        for (DBUser userDB : userRepository.saveAll(usersDB)) {
            users.add(userMapper.dbUserToUser(userDB));
        }
        return users;
    }

    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        DBUser userDB = userRepository.findById(id).map(u -> {
            u.setEmail(userMapper.userToDBUser(user).getEmail());
            u.setPassword(userMapper.userToDBUser(user).getPassword());
            //u.setLastname(userMapper.userToDBUser(user).getLastname());
            //u.setFirstname(userMapper.userToDBUser(user).getFirstname());
            u.setUsername(userMapper.userToDBUser(user).getUsername());
            //u.setRegister_num(userMapper.userToDBUser(user).getRegister_num());
            //u.setRegister_date(userMapper.userToDBUser(user).getRegister_date());
            //u.setSeniority_date(userMapper.userToDBUser(user).getSeniority_date());
            //u.setEnd_date(userMapper.userToDBUser(user).getEnd_date());
            u.setRole(userMapper.userToDBUser(user).getRole());
            //u.setService(userMapper.userToDBUser(user).getService());
            //u.setContract(userMapper.userToDBUser(user).getContract());
            return userRepository.save(u);
        }).orElseGet(() -> userRepository.save(userMapper.userToDBUser(user)));
        return userMapper.dbUserToUser(userDB);
    }

    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    public User findCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findUserAccountByEmail(email);
    }
}
