package com.alloymobile.restapi.resource;

import com.alloymobile.restapi.DTO.UsersDTO;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
import com.alloymobile.restapi.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins= "http://localhost:4200")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public String registerUser(@RequestBody Users user){
        userService.registerUser(user);
        return "User registered Successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session){
        var user = userService.loginUser(username, password);
        if(user.isPresent()){
            session.setAttribute("loggedInUser", user.get());
            return "Login Successful";
        }
        return "Invalid UserName or Password";
    }

    @GetMapping("/current")
    public Users getCurrentUser(HttpSession session){
        return (Users) session.getAttribute("loggedInUser");
    }

    @PostMapping("/logout")
    public String logoutUser(HttpSession session){
        session.invalidate();
        return "Logout User Successfully";
    }





}
