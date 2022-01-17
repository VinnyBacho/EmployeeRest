package com.example.EmployeeRest.repository;

import com.example.EmployeeRest.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    @Query("{ '_id' : ?0, 'status' : ?1}")
    Mono<Employee> findById(String id, String status);

    Flux<Employee> findByStatus(String status);
}
