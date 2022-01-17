package com.example.EmployeeRest;

import com.example.EmployeeRest.model.Employee;
import com.example.EmployeeRest.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.deleteAll();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Employee> employeeList = Arrays.asList(objectMapper.readValue(Paths.get("src/main/resources/data.json").toFile(), Employee[].class));
            for (Employee e: employeeList) {
                employeeRepository.save(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
