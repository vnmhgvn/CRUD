package com.cruddemo.cruddemo.dto;

import com.cruddemo.cruddemo.model.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email không để trống")
    @Email
    private String email;
    @NotEmpty
    private double wage;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        String[] str = employee.getName().split(" ");
        this.firstName = str[0];
        this.lastName = str[1];
        this.email = employee.getEmail();
        this.wage = employee.getWage();
    }
}
