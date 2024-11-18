package com.alloymobile.restapi.service;

import com.alloymobile.restapi.persistence.Users;
import com.alloymobile.restapi.persistence.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetialsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= usersRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}
