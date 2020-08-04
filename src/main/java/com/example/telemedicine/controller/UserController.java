package com.example.telemedicine.controller;

import com.example.telemedicine.model.Patients;
import com.example.telemedicine.model.Users;
import com.example.telemedicine.repository.PatientRepository;
import com.example.telemedicine.repository.UsersRepository;
import com.example.telemedicine.service.JwtSecurity.JwtTokenProvider;
import com.example.telemedicine.service.PatientService;
import com.example.telemedicine.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UsersRepository usersRepository;

    private final UserService userService;

    private final PatientService patientService;

    private final JwtTokenProvider jwtTokenProvider;

    private final PatientRepository patientRepository;

    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UsersRepository usersRepository, PatientRepository patientRepository, UserService userService, PatientService patientService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.usersRepository = usersRepository;
        this.patientRepository = patientRepository;
        this.userService = userService;
        this.patientService = patientService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody Users users) {
        try {
            String username = users.getUserName();
            System.out.println(username);
            System.out.println(users.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUserName(), users.getPassword()));
            System.out.println("oplsdoksado");
            Users user = userService.findByUserName(username);



            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            logger.debug(String.format("User %s received token %s", username, token));
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity register(@RequestBody Users users,
                                    BindingResult bindingResult){
        String repeatedPassword = users.getRepeatedPassword();
        Users user = userService.findByUserName(users.getUserName());
        String password = users.getPassword();

        if(bindingResult.hasErrors() || !password.equals(repeatedPassword) || user!=null){
            return ResponseEntity.ok("Check all fields");
        }


            userService.save(users);

            return ResponseEntity.ok("Registered");

    }


    @RequestMapping(value = "/register/additional", method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity additionalRegister(@RequestBody Patients patients,
                                             BindingResult bindingResult){
        Users patient = userService.findById(patients.getId());
        patientService.save(patients);


        return ResponseEntity.ok("kek");
    }


    @RequestMapping(value = "/patients",method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patients>> listAllPatients() {
    List<Patients> patients;
    patients = patientRepository.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

}
