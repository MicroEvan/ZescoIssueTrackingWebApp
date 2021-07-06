package com.example.demo.role;

import com.example.demo.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public boolean existsByName(String name);

    public List<Role> findById(int id);

    @Query("select max(s.id) from Role s")
    public Integer findMaxId();
}
