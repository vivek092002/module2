package org.vivek.module2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vivek.module2.DTO.EmployeeDTO;
import org.vivek.module2.entity.EmployeeEntity;
import org.vivek.module2.repository.EmployeeRepository;
import org.vivek.module2.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    //we are response entity for returning the response code
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable(name = "employeeId")Long id){
//        EmployeeDTO employeeDTO =  employeeService.getEmployeeById(id);
//        if (employeeDTO == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(employeeDTO);
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    //if we want all the employees in sorted order
    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
//        return "Hi age " + age + " " + sortBy;
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        return "Hello from post";
        EmployeeDTO savedEmployee =  employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return employeeService.updateEmployeeId(employeeDTO, employeeId);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeId(@PathVariable Long employeeId){
//        return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
          boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
          if (gotDeleted) return ResponseEntity.ok(true);
          return ResponseEntity.notFound().build();
    }

    //Patching
    // As we don't know which of the fields may be null in the DTo so we can't send employeeDTO
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Objects> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeId(employeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(employeeDTO);
    }

}
