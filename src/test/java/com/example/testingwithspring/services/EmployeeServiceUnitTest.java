package com.example.testingwithspring.services;

import com.example.testingwithspring.entites.Employee;
import com.example.testingwithspring.repositories.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceUnitTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){

        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();

    }
    @Test
    @Order(1)
    public void saveEmployTest(){

        Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        System.out.println(savedEmployee);


        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    @Order(2)
    public void getAllEmployee(){
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        List<Employee> results = new ArrayList<>();
        results.add(employee);results.add(employee1);
        // precondition
        Mockito.when(employeeRepository.findAll()).thenReturn(results);

        // action
        List<Employee> employeeList = employeeService.getAllEmployees();

        // verify
        System.out.println(employeeList);
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isGreaterThan(1);
    }
}
