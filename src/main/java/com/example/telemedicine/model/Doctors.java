package com.example.telemedicine.model;

import javax.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctors {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Users users;

}