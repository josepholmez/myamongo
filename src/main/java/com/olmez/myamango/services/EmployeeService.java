package com.olmez.myamango.services;

import java.util.List;

import com.olmez.myamango.model.Employee;

public interface EmployeeService {

    // Create
    String addEmployee(Employee emp);

    // Read-I
    List<Employee> getAllEmployees();

    // Read-II
    Employee getEmployeeById(String empId);

    // Update-I
    String updateEmployee(Employee givenEmp);

    // Update-II
    String updateEmployee(String existingEmpId, Employee givenEmp);

    // Delete
    boolean deleteEmployeeById(String empId);
}
