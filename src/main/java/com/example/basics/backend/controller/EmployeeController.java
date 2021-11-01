package com.example.basics.backend.controller;

import com.example.basics.backend.domain.Employee;
import com.example.basics.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping(value = "/")
    public String viewHomePage(Model model){
        model.addAttribute("list_employees", employeeService.getAllEmployees());
        return "index";
    }

    // bind employee to form
    @GetMapping(value = "/addemployee")
    public String addEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addemployee";
    }

    // save binded form data
    @PostMapping(value = "/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    // update employee
    @GetMapping(value = "/updateEmployee/{id}")
    public String updateemployee(@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateemployee";
    }

    // delete employee
    @GetMapping(value = "/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id, Model model){
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
}
