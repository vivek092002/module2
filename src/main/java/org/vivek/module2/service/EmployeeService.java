package org.vivek.module2.service;

import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.vivek.module2.DTO.EmployeeDTO;
import org.vivek.module2.entity.EmployeeEntity;
import org.vivek.module2.repository.EmployeeRepository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        //this is used to map the entity into the DTO with matching fields
//        ModelMapper mapper = new ModelMapper();
//        return mapper.map(employeeEntity, EmployeeDTO.class);

        // we don't want to create objects again and again

        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO > getAllEmployees() {
        List<EmployeeEntity> employeeEntities =  employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity saveEmployeeEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(saveEmployeeEntity, EmployeeDTO.class);
    }
}
