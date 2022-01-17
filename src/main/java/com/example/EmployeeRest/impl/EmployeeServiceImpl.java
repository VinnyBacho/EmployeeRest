package com.example.EmployeeRest.impl;

import com.example.EmployeeRest.model.Employee;
import com.example.EmployeeRest.model.Status;
import com.example.EmployeeRest.repository.EmployeeRepository;
import com.example.EmployeeRest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findByStatus(String.valueOf(Status.ACTIVE));
    }

    @Override
    public Mono<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id, "ACTIVE");
    }

    @Override
    public Mono<Employee> saveOrUpdateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Employee> deleteStudentById(String id, String authorizationHeader) {
        if (Objects.equals(authorizationHeader, "superSecretHeaderNoOneWillGuess")) {
            return employeeRepository.findById(id, "ACTIVE")
                    .map(empl -> {
                        empl.setStatus(Status.INACTIVE);
                        return empl;
                    })
                    .flatMap(employeeRepository::save);
        } else {
            return null;
        }
    }
}
