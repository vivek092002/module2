package org.vivek.module2.service;

import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.vivek.module2.DTO.EmployeeDTO;
import org.vivek.module2.entity.EmployeeEntity;
import org.vivek.module2.repository.EmployeeRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        //this is used to map the entity into the DTO with matching fields
//        ModelMapper mapper = new ModelMapper();
//        return mapper.map(employeeEntity, EmployeeDTO.class);

        // we don't want to create objects again and again

//        Optional<EmployeeEntity> employeeEntity =  employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeId(EmployeeDTO employeeDTO, Long employeeId) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity saveEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(saveEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    //we will use reflection for updating the field
    public EmployeeDTO updatePartialEmployeeId(Long employeeId, Map<String, Objects> updates) {
        boolean exist = employeeRepository.existsById(employeeId);
        if (!exist) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated =  ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
