package com.olmez.myamango.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.olmez.myamango.model.Employee;

public interface EmployeeRepository extends BaseObjectRepository<Employee> {

    @Query(value = "{ 'name' : ?0, 'deleted' : false }")
    List<Employee> findByName(String name);

}
