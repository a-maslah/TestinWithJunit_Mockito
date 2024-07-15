package com.example.testingwithspring.controllers;


import com.example.testingwithspring.entites.Employee;
import com.example.testingwithspring.repositories.EmployeeRepository;
import com.example.testingwithspring.services.EmployeeService;
import com.example.testingwithspring.services.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.JsonPathRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerUnitTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void saveEmployeeTest() throws Exception {

        // Given
        Employee employee =  Employee.builder()
                .id(1L)
                .firstName("employee1")
                .lastName("test")
                .email("employee@gmail.com")
                .build();

        //when
        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class))).thenReturn(employee);

        ResultActions res = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee))
        );

        res.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is("employee1")));
    }

    @Test
    void getAllEmployeeTest() throws Exception {
        List<Employee> employeesList = new ArrayList<>();

        employeesList.add(Employee.builder().id(1L).firstName("Sam1").lastName("Curran1").email("sam1@gmail.com").build());
        employeesList.add(Employee.builder().id(2L).firstName("Sam2").lastName("Curran2").email("sam2@gmail.com").build());

        Mockito.when(employeeService.getAllEmployees()).thenReturn(employeesList);
        ResultActions res = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/employees")
        );
        res.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(employeesList.size())));
    }

    @Test
    public void getOneEmployee() throws Exception {
        Employee employee =  Employee.builder()
                .id(1L)
                .firstName("amine")
                .lastName("test")
                .email("amine@gmail.com")
                .build();

        Mockito.when(employeeService.getEmployeeById(employee.getId())).thenReturn(Optional.of(employee));
        ResultActions res = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/employees/{id}",employee.getId())
        );
        res.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));

    }

}
