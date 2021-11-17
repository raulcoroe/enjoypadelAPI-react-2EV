package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAll();
}
