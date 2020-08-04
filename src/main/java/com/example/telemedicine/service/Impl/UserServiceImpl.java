package com.example.telemedicine.service.Impl;

import com.example.telemedicine.model.Roles;
import com.example.telemedicine.model.Users;
import com.example.telemedicine.repository.UsersRepository;
import com.example.telemedicine.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UsersRepository usersRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.token.secret")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;


    @Autowired
    public UserServiceImpl(UsersRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.usersRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void save(Users users) {

        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setActive(true);
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.USER);
        users.setRoles(roles);
        usersRepository.save(users);
    }

    @Override
    public void delete(Integer id) {
        usersRepository.delete(findById(id));
    }




    @Override
    public Users findByUserName(String userName) {
        return usersRepository.findByUserName(userName);
    }


    @Override
    public Users findById(Integer id) {
        return usersRepository.findById(id);
    }

    @Override
    public Users findByRoles(String roles) {
        return usersRepository.findByRoles(roles);
    }


}
