package com.cudev.appdemo.repository;

import com.cudev.appdemo.entity.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  OderRepository extends JpaRepository<Oder, Long> {
}
