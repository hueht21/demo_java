package com.cudev.appdemo.repository;


import com.cudev.appdemo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
