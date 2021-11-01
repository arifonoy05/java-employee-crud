package com.example.basics.backend.service;

import com.example.basics.backend.domain.Employee;
import com.example.basics.backend.exception.ResourceNotFoundException;
import com.example.basics.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = null;
        if(optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }


    // Restful services
    @Override
    public List<Employee> restGetAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee restGetEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id",id)
        );
    }

    @Override
    public Employee restSaveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee restUpdateEmployee(Employee employee, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", id));
        existingEmployee.setFirst_name(employee.getFirst_name());
        existingEmployee.setLast_name(employee.getLast_name());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void restDeleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id)
        );
        employeeRepository.deleteById(id);
    }
}
