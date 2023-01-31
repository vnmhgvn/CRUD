package com.cruddemo.cruddemo.service;

import com.cruddemo.cruddemo.dto.EmployeeDto;
import com.cruddemo.cruddemo.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();
    void saveEmp(EmployeeDto employeeDto);
    void updateEmp(EmployeeDto employeeDto);
    EmployeeDto findById(long id);
    void deleteById(long id);
    Page<EmployeeDto> findPaginated(int pageNo,int pageSize);
}
