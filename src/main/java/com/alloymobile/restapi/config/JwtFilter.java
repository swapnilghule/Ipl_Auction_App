package com.alloymobile.restapi.config;

import com.alloymobile.restapi.security.JWTService;
import com.alloymobile.restapi.service.MyUserDetialsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2V5IiwiaWF0IjoxODcwNDAxODI0Nzg5NzZ9.sDHGjVXb1ZY9SZC3d8CSPHPf_sXogeeGSFCPsi8EQgg

        System.out.println("Doing JWT check for" + request);

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username= null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("Bearer Token is NULL, sorry");

            token = authHeader.substring(7);
            username =jwtService.extractUserName(token);
        }
        System.out.println("Bearer Token is not NULL... Yaayyy let's go for another Check"+ authHeader);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("Username is not nul,,, yaaaay" + username);

            UserDetails userDetails =  applicationContext.getBean(MyUserDetialsService.class)
                    .loadUserByUsername(username);

            System.out.println("UserDetails done....." + userDetails);

            if(jwtService.validateToken(token,userDetails)){
                System.out.println("validating JWT lol done....." + userDetails);

                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
