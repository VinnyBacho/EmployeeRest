package com.example.EmployeeRest.model;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private Date dateOfBirth;
    private Date dateOfEmployment;
    private Status status;

}
