package com.example.telemedicine.repository;


import com.example.telemedicine.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUserName(String userName);

    Users findById(Integer id);

    Users findByRoles(String roles);




//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("update Users u set u.money = ?1 where u.userName = ?2")
//    int updateMoneyByUserName(BigDecimal money, String userName);

}