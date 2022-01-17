package com.example.EmployeeRest.repository;

import com.example.EmployeeRest.Application;
import com.example.EmployeeRest.model.Employee;
import com.example.EmployeeRest.model.Status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void givenId_whenFindById_thenFindEmployee() {
        employeeRepository.save(createEmployee()).block();
        Mono<Employee> employeeMono = employeeRepository.findById("1", "ACTIVE");
        StepVerifier.create(employeeMono)
                .assertNext(employee -> {
                    assertEquals("Vincent", employee.getFirstName());
                })
                .expectComplete()
                .verify();
    }
    @Test
    public void givenExistingEmployee_whenSave_thenReturnUpdatedEmployee() {
        Employee example = createEmployee();
        employeeRepository.save(example).block();
        example.setLastName("Smith"); //they got married
        employeeRepository.save(example).block();
        Mono<Employee> employeeMono = employeeRepository.findById("1", "ACTIVE");
        StepVerifier.create(employeeMono)
                .assertNext(employee -> {
                    assertEquals("Smith", employee.getLastName());
                })
                .expectComplete()
                .verify();
    }


    public Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("Vincent");
        employee.setMiddleInitial("C");
        employee.setLastName("Bacho");
        employee.setDateOfEmployment(Date.from(Instant.now()));
        employee.setDateOfBirth(Date.from(Instant.now()));
        employee.setStatus(Status.ACTIVE);
        return employee;
    }
}
