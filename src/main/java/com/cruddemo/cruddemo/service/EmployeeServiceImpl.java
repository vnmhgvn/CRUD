package com.cruddemo.cruddemo.service;

import com.cruddemo.cruddemo.dto.EmployeeDto;
import com.cruddemo.cruddemo.model.Employee;
import com.cruddemo.cruddemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map((employee) -> mapToDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public void saveEmp(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setName(employeeDto.getFirstName() + " " + employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setWage(employeeDto.getWage());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmp(EmployeeDto employeeDto){
        Optional<Employee> optional = employeeRepository.findById(employeeDto.getId());
        Employee employee = optional.get();
        employee.setName(employeeDto.getFirstName() + " " + employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setWage(employeeDto.getWage());
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto findById(long id){
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee = optional.get();
        }else{
            throw new RuntimeException("Không tìm thấy id: " + id);
        }
        return  mapToDto(employee);
    }

    @Override
    public void deleteById(long id){
        this.employeeRepository.deleteById(id);
    }

    @Override
    public Page<EmployeeDto> findPaginated(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo - 1,pageSize);
        return this.employeeRepository.findAll(pageable).map(employee -> new EmployeeDto(employee));
    }

    private EmployeeDto mapToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        String[] str = employee.getName().split(" ");
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(str[0]);
        employeeDto.setLastName(str[1]);
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setWage(employee.getWage());
        return employeeDto;
    }
}
