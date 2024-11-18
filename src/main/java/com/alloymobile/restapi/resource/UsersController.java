package com.alloymobile.restapi.resource;

import com.alloymobile.restapi.DTO.UsersDTO;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
//import com.alloymobile.restapi.security.JwtUtil;
import com.alloymobile.restapi.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins= "http://localhost:4200")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String registerUser(@RequestBody Users user){
        System.out.println("Insdie reg Service");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return "User registered Successfully";
    }



    @PostMapping("/login-api")
    public String loginUserx(@RequestParam String email, @RequestParam String password){

        return userService.loginUser(email, password);
    }

    @GetMapping("/test-api")
    public String getResult(@RequestParam String username){
        Users user= usersRepository.findByUsername(username);

        if(username != null)
        {
            return username;
        }
        else{
            return "FAILURE";
        }
    }

//    @PostMapping("/login")
//    public Map<String, String> loginUser(@RequestBody UsersDTO user){
//        Users users= usersRepository.findByUsername(user.getUsername())
//                .orElseThrow(() -> new RuntimeException("User Not found"));
//
//        if(!passwordEncoder.matches(user.getPassword(), users.getPassword())){
//            throw new RuntimeException("Invalid crendentils");
//        }
//
//        String token = jwtUtil.generateToken(users.getUsername());
//        Map<String, String> response= new HashMap<>();
//        response.put("token", token);
//        return response;
//
//    }




}
