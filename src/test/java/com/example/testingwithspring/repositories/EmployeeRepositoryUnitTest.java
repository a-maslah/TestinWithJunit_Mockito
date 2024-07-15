package com.example.testingwithspring.repositories;


import com.example.testingwithspring.entites.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryUnitTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("TEST 1 : save employee Test")
    @Order(2)
    public void saveEmployee(){

        // GiVEN
        Employee e = Employee.builder()
                .firstName("test name")
                .lastName("test last name")
                .email("test@gmail.com")
                .build();

        // WHEN
        employeeRepository.save(e);
        System.out.println(e);
        // THEN
        Assertions.assertThat(e.getId()).isGreaterThan(0);
    }
    @Test
    @DisplayName("TEST 2 : get All employees Test")
    @Order(2)
    public void getListOfEmployeesTest(){
        //Action
        List<Employee> employees = employeeRepository.findAll();
        //Verify
        System.out.println(employees);
        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }
    @Test
    @DisplayName("TEST 3 : get One employee Test")
    @Order(3)
    public void getOnEmployee(){
        //Action
        Employee employee = employeeRepository.findById(1L).get();
        //Verify
        System.out.println(employee);
        Assertions.assertThat(employee.getLastName()).isEqualToIgnoringCase("MASLAH");
    }

    @Test
    @DisplayName("TEST 4 : Update One employee Test")
    @Order(4)
    public void UpdateEmployee(){
        Employee employee = employeeRepository.findById(1L).get();

        employee.setFirstName("AMINOSS");
        Employee employeeUpated =  employeeRepository.save(employee);
        System.out.println(employeeUpated);
        Assertions.assertThat(employeeUpated.getFirstName()).isEqualToIgnoringCase("aminoss");
    }

    @Test
    @DisplayName("TEST 5 : delete One employee Test")
    @Order(5)
    public void deleteEmployee(){
        employeeRepository.deleteById(1L);
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepository.findById(1L).orElse(null));
        Assertions.assertThat(employeeOptional).isNull();
    }

}
