package com.mgas.formation.controller;

import com.mgas.formation.entity.User;
import com.mgas.formation.exception.IllegalRegisterDetailsException;
import com.mgas.formation.exception.InvalidPasswordException;
import com.mgas.formation.exception.UserNotFoundException;
import com.mgas.formation.model.UserDTO;
import com.mgas.formation.repository.UserRepository;
import com.mgas.formation.service.JWTService;
import com.mgas.formation.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    private UserService userService;
    private JWTService jwtService;

    public AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String getToken(@RequestBody UserDTO user) throws RuntimeException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User userDB = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFoundException(user.getUsername()));
        if (passwordEncoder.matches(user.getPassword(), userDB.getPassword())) {
            return jwtService.generateToken(user.getUsername());
        }
        throw new InvalidPasswordException();
    }

    @ValidateOnExecution
    @PostMapping(value = "/register")
    public UserDTO register(@Valid @RequestBody UserDTO user) throws MethodArgumentNotValidException, IllegalRegisterDetailsException {
        String illegals = "";
        if (user.getRegister_num() != null) illegals += "'register_num'\n";
        if (user.getSeniority_date() != null) illegals += ("'seniority_date'\n");
        if (user.getEnd_date() != null) illegals += ("'end_date'\n");
        if (user.getRole() != null) illegals += ("'role'\n");
        if (user.getService() != null) illegals += ("'service'\n");
        if (user.getContract() != null) illegals += ("'contract'\n");
        if (!illegals.isEmpty()) throw new IllegalRegisterDetailsException(illegals);
        return userService.saveUser(user);
    }
}