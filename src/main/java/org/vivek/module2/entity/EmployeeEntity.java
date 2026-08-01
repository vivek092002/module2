package org.vivek.module2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vivek.module2.annotations.EmployeeRoleValidations;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "required")
    @Size(min = 3, max = 10, message = "[3, 10]")
    private String name;
    private String email;
    //custom validations
    @EmployeeRoleValidations
    private String role;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;


}
