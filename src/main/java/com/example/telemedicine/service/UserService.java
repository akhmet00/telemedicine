package com.example.telemedicine.service;

import com.example.telemedicine.model.Users;


public interface UserService {

    void save(Users users);

    void delete(Integer id);

    Users findByUserName(String userName);

    Users findById(Integer id);

    Users findByRoles(String roles);

}
