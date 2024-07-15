package com.example.testingwithspring;

import com.example.testingwithspring.entites.Employee;
import com.example.testingwithspring.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestingWithSpringApplication implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;
    public static void main(String[] args) {
        SpringApplication.run(TestingWithSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        Employee empl1 =  new Employee();
        empl1.setEmail("amine@gmail.com");
        empl1.setFirstName("amine");
        empl1.setLastName("maslah");
        Employee empl2 =  new Employee();
        empl2.setEmail("karima@gmail.com");
        empl2.setFirstName("karima");
        empl2.setLastName("jarmoumi");
        employeeList.add(empl1);employeeList.add(empl2);
        employeeRepository.saveAll(employeeList);
    }
}
