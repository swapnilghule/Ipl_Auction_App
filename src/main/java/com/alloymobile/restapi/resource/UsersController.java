package com.alloymobile.restapi.resource;

import com.alloymobile.restapi.DTO.UsersDTO;
import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/register")
    public UsersDTO registerUser(@RequestBody UsersDTO userDTO){
        Users users = new Users();
        users.setUsername(userDTO.getUsername());
        users.setPassword(userDTO.getPassword());

        Users savedUsers = usersRepository.save(users);

        return new UsersDTO(savedUsers.getId(), savedUsers.getUsername());
    }

    @GetMapping("/users")
    public List<UsersDTO> getAllUsers(){
        return usersRepository.findAll().stream().map(users ->
                new UsersDTO(users.getId(), users.getUsername()))
                .collect(Collectors.toList());
    }

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        Users users = usersRepository.findByUsername(username);
        if(users != null && users.getPassword().equals(password)){
            return "Login successful for user:" +username;
        }
        else{
            return "Invalid Login ID";
        }
    }


}
