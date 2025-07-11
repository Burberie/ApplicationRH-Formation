package com.mgas.formation.service;

import com.mgas.formation.entity.User;
import com.mgas.formation.enumeration.RoleEnum;
import com.mgas.formation.exception.UserAlreadyExistsException;
import com.mgas.formation.exception.UserNotFoundException;
import com.mgas.formation.mapper.UserMapper;
import com.mgas.formation.model.UserDTO;
import com.mgas.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    private UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAll(){
        List<UserDTO> users = new ArrayList<>();
        for (User userDB : userRepository.findAll()) {
            users.add(userMapper.dbUserToUser(userDB));
        }
        return users;
    }
    public UserDTO getId(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.dbUserToUser(user);
    }

    public UserDTO saveUser(@RequestBody UserDTO user) {
        autoSetUser(user);
        User userDB = userMapper.userToDBUser(user);
        return userMapper.dbUserToUser(userRepository.save(userDB));
    }

    public List<UserDTO> saveUsers(@RequestBody List<UserDTO> userList) {
        List<User> usersDB = new ArrayList<>();
        for (UserDTO user : userList) {
            autoSetUser(user);
            usersDB.add(userMapper.userToDBUser(user));
        }
        List<UserDTO> users = new ArrayList<>();
        for (User userDB : userRepository.saveAll(usersDB)) {
            users.add(userMapper.dbUserToUser(userDB));
        }
        return users;
    }

    public UserDTO updateUser(@RequestBody UserDTO user, @PathVariable Long id) {
        if (!userRepository.findById(id).equals(userRepository.findByUsername(user.getUsername()))) {
            if (userRepository.existsByUsername(user.getUsername())) { throw new UserAlreadyExistsException("User '" + user.getUsername() + "'"); }
            if (userRepository.existsByEmail(user.getEmail())) { throw new UserAlreadyExistsException("Email '" + user.getEmail() + "'"); }
        }
        if (user.getSeniority_date() == null) { user.setSeniority_date(user.getRegister_date()); }
        if (user.getRole() == null || user.getRole().isEmpty()) { Set<RoleEnum> role = new HashSet<>(); role.add(RoleEnum.COLLABORATOR); user.setRole(role); }
        User userDB = userRepository.findById(id).map(u -> {
            u.setEmail(userMapper.userToDBUser(user).getEmail());
            u.setPassword(userMapper.userToDBUser(user).getPassword());
            u.setLastname(userMapper.userToDBUser(user).getLastname());
            u.setFirstname(userMapper.userToDBUser(user).getFirstname());
            u.setUsername(userMapper.userToDBUser(user).getUsername());
            u.setRegister_num(userMapper.userToDBUser(user).getRegister_num());
            u.setSeniority_date(userMapper.userToDBUser(user).getSeniority_date());
            u.setEnd_date(userMapper.userToDBUser(user).getEnd_date());
            u.setRole(userMapper.userToDBUser(user).getRole());
            u.setService(userMapper.userToDBUser(user).getService());
            u.setContract(userMapper.userToDBUser(user).getContract());
            return userRepository.save(u);
        }).orElseGet(() -> userRepository.save(userMapper.userToDBUser(user)));
        return userMapper.dbUserToUser(userDB);
    }

    public void deleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    public void autoSetUser(UserDTO user) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(user.getUsername())) { throw new UserAlreadyExistsException("User '" + user.getUsername() + "'"); }
        if (userRepository.existsByEmail(user.getEmail())) { throw new UserAlreadyExistsException("Email '" + user.getEmail() + "'"); }
        if (user.getSeniority_date() == null) { user.setSeniority_date(user.getRegister_date()); }
        if (user.getRole() == null || user.getRole().isEmpty()) { Set<RoleEnum> role = new HashSet<>(); role.add(RoleEnum.COLLABORATOR); user.setRole(role); }
    }
}