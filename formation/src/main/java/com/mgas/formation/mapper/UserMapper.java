package com.mgas.formation.mapper;

import com.mgas.formation.entity.User;
import com.mgas.formation.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO dbUserToUser(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getLastname(), user.getFirstname(), user.getRegister_num(), user.getSeniority_date(), user.getEnd_date(), user.getRole(), user.getService(), user.getContract());
    }

    public User userToDBUser(UserDTO user) {
        return new User(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getLastname(), user.getFirstname(), user.getRegister_num(), user.getSeniority_date(), user.getEnd_date(), user.getRole(), user.getService(), user.getContract());
    }
}