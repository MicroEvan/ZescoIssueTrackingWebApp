package com.example.demo.role;

import com.example.demo.role.Role;
import com.example.demo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public String createRole(Role role){
        try {
            if (!roleRepository.existsByName(role.getName())){
                role.setId(null == roleRepository.findMaxId()? 0 : roleRepository.findMaxId() + 1);
                roleRepository.save(role);
                return "Student record created successfully.";
            }else {
                return "Student already exists in the database.";
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Role> readRoles(){
        return roleRepository.findAll();
    }

    public String getRole(int id){

        List<Role> roles= roleRepository.findById(id);

        return roles.get(0).getName();
    }

    @Transactional
    public String updateRole(Role role){
        if (roleRepository.existsByName(role.getName())){
            try {
                List<Role> users = roleRepository.findById(role.getId());
                users.stream().forEach(s -> {
                    Role roleToBeUpdate = roleRepository.findById(s.getId()).get(0);
                    roleToBeUpdate.setName(role.getName());
                    roleToBeUpdate.setName(role.getName());
                    roleRepository.save(roleToBeUpdate);
                });
                return "Student record updated.";

            }catch (Exception e){
                throw e;
            }
        }else {
            return "Student does not exists in the database.";
        }
    }

    @Transactional
    public String deleteRole(Role role){
        if (roleRepository.existsByName(role.getName())){
            try {
                List<Role> roles = roleRepository.findById(role.getId());
                roles.stream().forEach(s -> {
                    roleRepository.delete(s);
                });
                return "Student record deleted successfully.";
            }catch (Exception e){
                throw e;
            }

        }else {
            return "Student does not exist";
        }
    }
}
