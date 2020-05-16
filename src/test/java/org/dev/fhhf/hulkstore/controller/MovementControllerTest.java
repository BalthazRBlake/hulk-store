package org.dev.fhhf.hulkstore.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.service.DateFormaterService;
import org.dev.fhhf.hulkstore.service.EmployeeService;
import org.dev.fhhf.hulkstore.service.MovementService;
import org.dev.fhhf.hulkstore.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RestClientTest
public class MovementControllerTest {

	@Mock
	private MovementService movementService;
	@Mock
	private EmployeeService employeeService;
	@Mock
	private ProductService productService;
	@Mock
	private DateFormaterService dateService;
	@InjectMocks
	private MovementController movementController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(movementController).build();
    }
	
	@Test
	public void getAllMovementsTest() throws Exception {
		
		Movement move1 = new Movement(1, "1 0 50,4 5 20", "Input", new Employee(1, "Juan"));
		Movement move2 = new Movement(2, "2 45 5", "Output", new Employee(2, "Laura"));
		
		when(movementService.findAllMovements())
				.thenReturn(Arrays.asList(move1, move2));
		
		mockMvc.perform(get("/move/3/all"))
				.andExpect(status().isOk())
		        .andExpect(model().attribute("moves", hasSize(2)))
		        .andExpect(model().attribute("empId", is(3)));
	}
	
	@Test
	@Disabled
	public void executeOperationTest() throws Exception {

        mockMvc.perform(
                post("/move/6/Input/Products")
		        )
        		.andDo(print());
		        //.andExpect(status().is(302))
		        //.andExpect(view().name("redirect:/move/6/all"));
	}
}
