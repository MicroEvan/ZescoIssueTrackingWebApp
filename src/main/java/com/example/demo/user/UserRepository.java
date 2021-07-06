package com.example.demo.user;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public boolean existsByEmail(String email);

    public List<User> findByEmail(String email);

    @Query("select max(s.id) from User s")
    public Integer findMaxId();
}
