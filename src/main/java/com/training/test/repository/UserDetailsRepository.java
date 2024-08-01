package com.training.test.repository;

import com.training.test.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    Optional<UserDetails> findByUsername(String username);
}
