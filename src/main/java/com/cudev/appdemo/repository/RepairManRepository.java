package com.cudev.appdemo.repository;

import com.cudev.appdemo.entity.Repairman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairManRepository extends JpaRepository<Repairman, Long> {

//    Optional<Repairman> findByIdRepairmanAfterAndNumberPhone(Long id,String numberPhone);

}
