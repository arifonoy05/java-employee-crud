package com.example.basics.backend.controller;

import com.example.basics.backend.domain.Employee;
import com.example.basics.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestcontroller {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeRestcontroller(EmployeeService employeeService){
        super();
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees")
    public List<Employee> restGetAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> restGetEmployeeById(@PathVariable(value = "id") long id){
        return new ResponseEntity<Employee>(employeeService.restGetEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/employees/save")
    public ResponseEntity<Employee> restSaveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.restSaveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> restUpdateEmployee(@RequestBody Employee employee, @PathVariable(value = "id") long id){
        return new ResponseEntity<Employee>(employeeService.restUpdateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> restDeleteEmployee(@PathVariable(value = "id") long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
    }


}
