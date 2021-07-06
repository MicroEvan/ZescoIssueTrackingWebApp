package com.example.demo.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Transactional
    public List<String> createEquipment(Equipment equipment){
        try {
            if (!equipmentRepository.existsById(equipment.getId())){
                equipment.setId(null == equipmentRepository.findMaxId()? 0 : equipmentRepository.findMaxId() + 1);
                equipmentRepository.save(equipment);
                return List.of("Equipment record created successfully.");
            }else {
                return List.of("Equipment already exists in the database.");
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Equipment> readEquipments(){
        return equipmentRepository.findAll();
    }

    @Transactional
    public List<String> updateEquipment(Equipment equipment){
        if (equipmentRepository.existsById(equipment.getId())){
            try {
                List<Equipment> issues = equipmentRepository.findById(equipment.getId());
                issues.stream().forEach(s -> {
                    Equipment equipmentToBeUpdate = equipmentRepository.findById(s.getId()).get(0);
                    equipmentToBeUpdate.setName(equipment.getName());
                    equipmentToBeUpdate.setDescription(equipment.getDescription());
                    equipmentRepository.save(equipmentToBeUpdate);
                });
                return List.of("Equipment record updated.");

            }catch (Exception e){
                throw e;
            }
        }else {
            return List.of("Equipment does not exists in the database.");
        }
    }

    @Transactional
    public List<String> deleteEquipment(Equipment equipment){
        if (equipmentRepository.existsById(equipment.getId())){
            try {
                List<Equipment> equipments = equipmentRepository.findById(equipment.getId());
                equipments.stream().forEach(s -> {
                    equipmentRepository.delete(s);
                });
                return List.of("Equipment record deleted successfully.");
            }catch (Exception e){
                throw e;
            }

        }else {
            return List.of("Equipment does not exist");
        }
    }
}
