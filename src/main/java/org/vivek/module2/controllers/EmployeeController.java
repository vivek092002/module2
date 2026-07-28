package org.vivek.module2.controllers;

import org.springframework.web.bind.annotation.*;
import org.vivek.module2.DTO.EmployeeDTO;
import org.vivek.module2.entity.EmployeeEntity;
import org.vivek.module2.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : asdfghjkl";
//    }


    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    @GetMapping("/employees/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){  //if we want to use another var for method we can use this way
//        return new EmployeeDTO(id, "Vivek", "vivek@gmail.com", 27, LocalDate.of(2026, 7, 27), true);
//    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeId(@PathVariable(name = "employeeId")Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    //if we want all the employees in sorted order
    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
//        return "Hi age " + age + " " + sortBy;
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
//        return "Hello from post";
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmpById(){
        return "Hello from put";
    }
}
