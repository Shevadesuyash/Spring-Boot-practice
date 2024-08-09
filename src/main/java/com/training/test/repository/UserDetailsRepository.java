package com.training.test.repository;

import com.training.test.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    Optional<UserDetails> findByUsername(String username);
    Optional<UserDetails> findByEmail(String email);
    Optional<UserDetails> findByPhone(Long phone);


    @Query("SELECT u.id, u.username, u.email FROM UserDetails u")
    List<Object[]> findUserDetails();


}
