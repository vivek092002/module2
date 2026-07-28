package org.vivek.module2.controllers;

import org.springframework.web.bind.annotation.*;
import org.vivek.module2.DTO.EmployeeDTO;
import org.vivek.module2.entity.EmployeeEntity;
import org.vivek.module2.repository.EmployeeRepository;
import org.vivek.module2.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message : asdfghjkl";
//    }


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


//    @GetMapping("/employees/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){  //if we want to use another var for method we can use this way
//        return new EmployeeDTO(id, "Vivek", "vivek@gmail.com", 27, LocalDate.of(2026, 7, 27), true);
//    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeId(@PathVariable(name = "employeeId")Long id){
        return employeeService.getEmployeeById(id);
    }

    //if we want all the employees in sorted order
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
//        return "Hi age " + age + " " + sortBy;
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        return "Hello from post";
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmpById(){
        return "Hello from put";
    }
}
