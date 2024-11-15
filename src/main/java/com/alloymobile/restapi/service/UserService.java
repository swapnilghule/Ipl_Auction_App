package com.alloymobile.restapi.service;

import com.alloymobile.restapi.persistence.UsersRepository;
import com.alloymobile.restapi.persistence.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    //Service to check whether user is exists or not else save user data
    //Taking an Input from UI
    public Users registerUser(Users user){
        if(usersRepository.findByEmail(user.getEmail()).isPresent() || usersRepository.findByUsername(user.getUsername()).isPresent()){
            throw  new RuntimeException("User Already Exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Optional<Users> loginUser(String username, String password){
        Optional<Users> user= usersRepository.findByUsername(username);
        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
            return user;
        }
        return Optional.empty();
    }

}
