package com.example.telemedicine.service.JwtSecurity;

import com.example.telemedicine.model.Roles;
import com.example.telemedicine.model.Users;
import com.example.telemedicine.service.Impl.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static UserDetailsImpl create(Users user) {
        return new UserDetailsImpl(
                user.getId(),
                user,
                user.getUserName(),
                user.getPassword(),
                user.isActive(),
                user.getPatients(),
                user.getDoctors(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Roles> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(Roles.class.getEnumConstants().toString())
                ).collect(Collectors.toList());
    }
}