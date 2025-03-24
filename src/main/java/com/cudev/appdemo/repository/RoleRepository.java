package com.cudev.appdemo.repository;


import com.cudev.appdemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RoleRepository extends JpaRepository<Role, Long> {


}
