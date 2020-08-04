package com.example.telemedicine.repository;

import com.example.telemedicine.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patients,Long> {

    Patients findById(Integer id);

}
