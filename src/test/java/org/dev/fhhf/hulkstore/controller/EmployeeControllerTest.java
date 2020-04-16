package org.dev.fhhf.hulkstore.controller;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;

@RestClientTest
public class EmployeeControllerTest {

	@Mock
	private EmployeeService empService;
	
	@InjectMocks
    private EmployeeController employeeController;
    
	private MockMvc mockMvc;
	
	@BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(employeeController).build();
    }
	
	@Test
	public void getAllEmployeesTest() throws Exception {
		Employee employee1 = new Employee(1, "Paulina");
		Employee employee2 = new Employee(2, "Sonia");
		Employee employee3 = new Employee(3, "Camilo");
		
		when(empService.findAllEmployees())
        .thenReturn(Arrays.asList(employee1, employee2, employee3));
		
		mockMvc.perform(get("/emp/all"))
		        .andExpect(status().isOk())
		        .andExpect(view().name("employees"))
		        .andExpect(model().attribute("employees", hasSize(3)))
		        .andExpect(model().attribute("employees", hasItem(
		                allOf(
		                        hasProperty("id", is(1)),
		                        hasProperty("firstName", is("Paulina"))
		                )
		        )))
		        .andExpect(model().attribute("employees", hasItem(
		                allOf(
		                        hasProperty("id", is(2)),
		                        hasProperty("firstName", is("Sonia"))
		                )
		        )))
		        .andExpect(model().attribute("employees", hasItem(
		                allOf(
		                        hasProperty("id", is(3)),
		                        hasProperty("firstName", is("Camilo"))
		                )
		        )));
	}
	
	@Test
	public void initMovementTest() throws Exception {
		when(empService.findEmployeeById(1))
				.thenReturn(new Employee(1, "Paulina"));
		
		mockMvc.perform(get("/emp/1/initMove"))
				.andExpect(status().isOk())
				.andExpect(view().name("movements"))
                .andExpect(model().attribute("employee", allOf(
                                hasProperty("id", is(1)),
                                hasProperty("firstName", is("Paulina"))
                )));
	}
}
