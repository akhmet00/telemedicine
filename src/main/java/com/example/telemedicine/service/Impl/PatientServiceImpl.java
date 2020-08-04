package com.example.telemedicine.service.Impl;


import com.example.telemedicine.model.Patients;
import com.example.telemedicine.model.Users;
import com.example.telemedicine.repository.PatientRepository;
import com.example.telemedicine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public void save(Patients patients) {
        Authentication  authentication =  SecurityContextHolder.getContext().getAuthentication();
//        Patients user = (Patients) authentication.getPrincipal();
//        Integer id = user.getId();
       // System.out.println(authentication);
       // System.out.println( authentication.getPrincipal());
       // CustomUserDetail myUserDetails = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Integer userId=authentication.getUsers().getId();
       // UserServiceImpl customUser = (UserServiceImpl) authentication.getPrincipal();
       // int userId = customUser.getId();
       // System.out.println(userId);
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getUsers());
        patients.setUsers(user.getUsers());
//        patients.setId(user.getUsers().getId());
        patientRepository.save(patients);
//        System.out.println(users);
      //  patients.setUsers(userId);

//        patients.setUsers(authentication.getName());
        //patientRepository.save(patients);
    }
}
