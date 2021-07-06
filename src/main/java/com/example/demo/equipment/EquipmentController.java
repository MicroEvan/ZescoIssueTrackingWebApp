package com.example.demo.equipment;


import com.example.demo.equipment.Equipment;
import com.example.demo.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping(path = "equips/info", method = RequestMethod.GET)
    public String info(){
        return "The Equipment application is up...";
    }

    @RequestMapping(path = "equips/create", method = RequestMethod.POST)
    public List<String> createStudent(@RequestBody Equipment equipment){
        return equipmentService.createEquipment(equipment);
    }

    @RequestMapping(path = "equips/read", method = RequestMethod.GET)
    public List<Equipment> readEquipments(){
        return equipmentService.readEquipments();
    }

    @RequestMapping(path = "equips/update", method = RequestMethod.PUT)
    public List<String> updateEquipment(@RequestBody Equipment equipment){
        return equipmentService.updateEquipment(equipment);
    }

    @RequestMapping(path = "equips/delete", method = RequestMethod.DELETE)
    public List<String> deleteEquipment(@RequestBody Equipment equipment){
        return equipmentService.deleteEquipment(equipment);
    }
}
