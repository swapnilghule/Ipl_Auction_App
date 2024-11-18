package com.alloymobile.restapi.service;

import com.alloymobile.restapi.persistence.UsersRepository;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.security.JWTService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService service;

    //Service to check whether user is exists or not else save user data
    //Taking an Input from UI
    public Users registerUser(Users user){
//        if(usersRepository.findByEmail(user.getEmail()).isPresent() || usersRepository.findByUsername(user.getUsername(){
//            throw  new RuntimeException("User Already Exists");
//        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public String loginUser(String email, String password){

        Users users=  usersRepository.findByEmail(email);

       if(!passwordEncoder.matches(password, users.getPassword())){
           throw new RuntimeException("Invalid crendentils");
       }
       else {
           return service.generateToken(users.getUsername());
       }

    }

}
