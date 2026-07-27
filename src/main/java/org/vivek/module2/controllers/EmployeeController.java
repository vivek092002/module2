package org.vivek.module2.controllers;

import org.springframework.web.bind.annotation.*;
import org.vivek.module2.DTO.EmployeeDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : asdfghjkl";
//    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){  //if we want to use another var for method we can use this way
        return new EmployeeDTO(id, "Vivek", "vivek@gmail.com", 27, LocalDate.of(2026, 7, 27), true);
    }

    //if we want all the employees in sorted order
    @GetMapping(path = "/employees")
    public String getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return "Hi age " + age + " " + sortBy;
    }


}
