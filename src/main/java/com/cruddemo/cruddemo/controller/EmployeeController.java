package com.cruddemo.cruddemo.controller;

import com.cruddemo.cruddemo.dto.EmployeeDto;
import com.cruddemo.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showList(Model model){
        model.addAttribute("listEmployees",employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        model.addAttribute("employee",employeeDto);
        return "newEmployee";
    }

    @PostMapping("/SaveEmployee")
    public String SaveEmployee(@ModelAttribute("employee") EmployeeDto employee){
        employeeService.saveEmp(employee);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id,  Model model){
        EmployeeDto employeeDto = employeeService.findById(id);
        model.addAttribute("employee",employeeDto);
        return "updateEmployee";
    }

    @PostMapping("/SaveUpdateEmployee")
    public String SaveUpdateEmployee(@ModelAttribute("employee") EmployeeDto employee){
        employeeService.updateEmp(employee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id){
        this.employeeService.deleteById(id);
        return "redirect:/";
    }
}
