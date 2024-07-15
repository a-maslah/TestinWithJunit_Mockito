package com.example.testingwithspring.services;

import com.example.testingwithspring.entites.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);
}
