package com.example.telemedicine.service.Impl;

import com.example.telemedicine.model.Doctors;
import com.example.telemedicine.model.Patients;
import com.example.telemedicine.model.Roles;
import com.example.telemedicine.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class UserDetailsImpl implements UserDetails {

    private Users users;
    private int id;
    private String userName;
    private String password;
    private boolean active;
//    private Roles roles;
    private Patients patients;
    private Doctors doctors;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Users users) {
        this.users = users;
    }

    public UserDetailsImpl(int id, Users users, String userName, String password, boolean active, Patients patients, Doctors doctors, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.users = users;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.patients = patients;
        this.doctors = doctors;
        this.authorities = authorities;
    }
//    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        System.out.println(users.getUserName());
        return userName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
