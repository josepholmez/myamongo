package com.olmez.myamango.services;

import java.util.List;

import com.olmez.myamango.model.Employee;

public interface EmployeeService {

    // Create
    Long addEmployee(Employee emp);

    // Read-I
    List<Employee> getAllEmployees();

    // Read-II
    Employee getEmployeeById(Long empId);

    // Update-I
    Long updateEmployee(Employee givenEmp);

    // Update-II
    Long updateEmployee(Long existingEmpId, Employee givenEmp);

    // Delete
    boolean deleteEmployeeById(Long empId);
}
