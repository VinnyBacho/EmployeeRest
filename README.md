# EmployeeRest
A simple API created using SpringBoot with ReactiveMongoRepositories. I decided to use SpringBoot in order to manage all of the configuration details, giving me more freedom to focus on the code and actual implementation. 
The code follows a Controller -> Service -> Repository topology as I found that to be the most direct way to implement this solution. I also decided to use ReactiveMongoRepositories and reactive streams to implement a more modern design
that would be more scalable in a real world setting.

# Requirements To Run
* MongoDB Server
* Maven

# Command To Run
`mvn spring-boot:run`

# Example Curl Commands to Hit API
### Get All Employees
```
curl --location --request GET 'http://localhost:8081/employees/'
```

### Get Employee By ID
```
curl --location --request GET 'http://localhost:8081/employees/{id}'
```

### Save or Update Employee
```
curl --location --request POST 'http://localhost:8081/employees/save' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Vincent",
    "middleInitial": "C",
    "lastName": "Bacho",
    "dateOfBirth": "1997-02-01",
    "dateOfEmployment": "2022-02-01",
    "status": "ACTIVE"
}'
```

# Deleting Employee
```
curl --location --request DELETE 'http://localhost:8081/employees/{id}' \
--header 'Content-Type: application/json' \
--header 'authorization: superSecretHeaderNoOneWillGuess'
```
