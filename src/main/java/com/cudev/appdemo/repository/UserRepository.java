package com.cudev.appdemo.repository;


import com.cudev.appdemo.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @EntityGraph(attributePaths = "role")
    Optional<User> findByUserName(String username);

//    Optional<User> findByUsername(String username, boolean isDeleted);

}
