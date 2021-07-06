package com.example.demo.role;


import com.example.demo.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "role/info", method = RequestMethod.GET)
    public String info(){
        return "The Equipment application is up...";
    }

    @RequestMapping(path = "role/get/{roleId}",method = RequestMethod.GET)
    public String getRole(@PathVariable("roleId") int id){
        return roleService.getRole(id);
    }

    @RequestMapping(path = "role/read", method = RequestMethod.GET)
    public List<Role> readRoles(){
        return roleService.readRoles();
    }

}
