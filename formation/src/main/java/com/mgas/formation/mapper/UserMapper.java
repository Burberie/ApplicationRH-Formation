package com.mgas.formation.mapper;

import com.mgas.formation.entity.DBUser;
import com.mgas.formation.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User dbUserToUser(DBUser user) {
        return new User(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
    }

    public DBUser userToDBUser(User user) {
        return new DBUser(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
