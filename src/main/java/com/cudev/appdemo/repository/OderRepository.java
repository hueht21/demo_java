package com.cudev.appdemo.repository;

import com.cudev.appdemo.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  OderRepository extends JpaRepository<Oder, Long> {

    @Query("SELECT p FROM Oder p WHERE p.customer.idCustomer = :customerId")
    List<Oder> findByIdCustomer(@Param("customerId") Long customerId);

}
