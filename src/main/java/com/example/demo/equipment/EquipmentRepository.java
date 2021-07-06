package com.example.demo.equipment;

import com.example.demo.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    public boolean existsById(int id);

    public List<Equipment> findById(int id);

    @Query("select max(s.id) from Equipment s")
    public Integer findMaxId();
}
