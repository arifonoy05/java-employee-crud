package com.example.basics.backend.service;

import com.example.basics.backend.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);

    // For RESTful endpoints
    List<Employee> restGetAllEmployees();
    Employee restGetEmployeeById(long id);
    Employee restSaveEmployee(Employee employee);
    Employee restUpdateEmployee(Employee employee, long id);
    void restDeleteEmployee(long id);
}
