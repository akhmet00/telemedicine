package com.example.telemedicine;

import com.example.telemedicine.repository.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class TelemedicineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelemedicineApplication.class, args);
    }

}
