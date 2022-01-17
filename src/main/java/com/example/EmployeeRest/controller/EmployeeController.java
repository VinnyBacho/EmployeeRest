package com.example.EmployeeRest.controller;

import com.example.EmployeeRest.model.Employee;
import com.example.EmployeeRest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public Flux<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable("id") String id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/save")
    public Mono<Employee> saveOrUpdateEmployee(@RequestBody Employee employee) {
        return employeeService.saveOrUpdateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public Mono<Employee> deleteEmployee(@PathVariable("id") String id, @RequestHeader("authorization") String authorizationHeader) {
        return employeeService.deleteStudentById(id, authorizationHeader);
    }
}
