package com.example.EmployeeRest.service;

import com.example.EmployeeRest.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface EmployeeService {
    Flux<Employee> getAllEmployees();
    Mono<Employee> getEmployeeById(String id);
    Mono<Employee> saveOrUpdateEmployee(Employee employee);
    Mono<Employee> deleteStudentById(String id, String authorizationHeader);


}
