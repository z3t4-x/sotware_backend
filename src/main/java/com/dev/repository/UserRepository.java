package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findOneByEmail(String email);

    
    boolean existsByEmail(String email);
    
    Optional<User> findByUsername(String userName);
    
    boolean existsByUsername(String cdUsuario);
    
    Optional<User> findByUsernameOrEmail(String username, String email);

}
