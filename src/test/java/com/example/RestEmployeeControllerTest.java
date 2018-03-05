
package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bjit.controller.api.RestEmployeeController;
import com.bjit.model.Country;
import com.bjit.model.CreateEmployee;
import com.bjit.model.Department;
import com.bjit.model.Employee;
import com.bjit.model.JobTitle;
import com.bjit.repository.CountryRepository;
import com.bjit.repository.DepartmentRepository;
import com.bjit.repository.EmployeeRepository;
import com.bjit.repository.JobRepository;
import com.bjit.serviceImpl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class RestEmployeeControllerTest {
	@Mock
	EmployeeServiceImpl employeeService;

	@Mock
	Page limitPage;
	@Mock
	DataTablesInput dbInput;

	@Mock
	Employee employee;
	@Mock
	Country country;
	@Mock
	Department department;
	@Mock
	JobTitle job;

	@Mock
	ModelAttribute modelAttribute;

	@Mock
	Model model;
	MockMvc mockMvc;
	PageRequest limit;
	@InjectMocks
	RestEmployeeController restEmployeeController;

	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	DepartmentRepository departmentRepository;
	@Mock
	JobRepository jobRepository;
	@MockBean
	CountryRepository countryRepository;
	
	private static CreateEmployee createEmployee;
	@BeforeClass
	public static void init(){
		createEmployee = new CreateEmployee();
	}

	@Before
	public void setUp() {
		
		
		mockMvc = MockMvcBuilders.standaloneSetup(restEmployeeController).build();
	}


	@Test
	public void testGetAllUsers() throws Exception {
//		List<Employee> users = Arrays.asList(new Employee(1, "Daenerys Targaryen"), new Employee(2, "John Snow"));
//
//		
//		for (int i=0;i<2;i++) {
//			System.out.println(users.get(i));
//		}
//		// Page<Employee> user =
//
//		// when(employeeService.findAll(limit)).thenReturn((Page<Employee>) users);
//
//		when(employeeRepository.findAll()).thenReturn((List<Employee>) users);
		
		
		
		List<Employee> users = createEmployee.getEmployeeList();
		final Page<Employee> page = new PageImpl<>(users);
		
		when(employeeRepository.findAll()).thenReturn(page);
		mockMvc.perform(get("/api/employee/list").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());



//		when(employeeRepository.findAll()).thenReturn(page);

//		mockMvc.perform(get("api/employee/list")).andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].employeeId", is(1)))
//				.andExpect(jsonPath("$[0].name", is("Daenerys Targaryen")))
//				.andExpect(jsonPath("$[1].employeeId", is(2))).andExpect(jsonPath("$[1].name", is("John Snow")));
//
//		verify(employeeService, times(1)).findAll(limit);
//		verifyNoMoreInteractions(employeeService);
	}

	


	// Checking the negative response for findById of resController

		@Test
		public void test_get_by_id_pass_404_not_found() throws Exception {
			Employee user = new Employee(1, "Daenerys Targaryen");
			
			when(employeeService.findById(1)).thenReturn(user);
			mockMvc.perform(post("/api/employee/details/1").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
//			when(employeeService.findById(1)).thenReturn(user);
//			mockMvc.perform(get("/api/employee/details/1")).andExpect(status().isOk());
////			verify(employeeService, times(1)).findById(1);
////			verifyNoMoreInteractions(employeeService);
		}
	
	
	
	@Test
	public void testDetails() throws Exception {
		Employee user = new Employee(1, "Daenerys Targaryen");
		when(employeeService.findById(1)).thenReturn(user);
//		when(employeeRepository.findOne(1)).thenReturn(user);
		mockMvc.perform(post("/api/employee/details/1").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
//		when(employeeService.findById(1)).thenReturn(user);
//		mockMvc.perform(get("/api/employee/details/1")).andExpect(status().isOk());
////		verify(employeeService, times(1)).findById(1);
////		verifyNoMoreInteractions(employeeService);
	}

	
	
	
	@Test
	public void testSave() throws Exception {
//		Employee user = new Employee(1, "Daenerys Targaryen");
//		when(employeeService.findById(1)).thenReturn(user);
////		when(employeeRepository.findOne(1)).thenReturn(user);
//		mockMvc.perform(put("/api/employee/update").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
////		when(employeeService.findById(1)).thenReturn(user);
////		mockMvc.perform(get("/api/employee/details/1")).andExpect(status().isOk());
//////		verify(employeeService, times(1)).findById(1);
//////		verifyNoMoreInteractions(employeeService);
		
		
		
		Employee employee = new Employee();
		employee.setName("Name to save");
		employee.setEmployeeId(1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String employeeString = objectMapper.writeValueAsString(employee);
		when(employeeRepository.save(employee)).thenReturn(employee);
		// when
//		Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employee);
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeString))
		.andDo(MockMvcResultHandlers.print());	
	}
	
	
	
	
	
	@Test
	public void testUpdate() throws Exception {
//		Employee user = new Employee(1, "Daenerys Targaryen");
//		when(employeeService.findById(1)).thenReturn(user);
////		when(employeeRepository.findOne(1)).thenReturn(user);
//		mockMvc.perform(put("/api/employee/update").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
////		when(employeeService.findById(1)).thenReturn(user);
////		mockMvc.perform(get("/api/employee/details/1")).andExpect(status().isOk());
//////		verify(employeeService, times(1)).findById(1);
//////		verifyNoMoreInteractions(employeeService);
		
		
		
		Employee employee = new Employee();
		employee.setName("Tasmin");
		employee.setEmployeeId(1);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String employeeString = objectMapper.writeValueAsString(employee);
		when(employeeRepository.save(employee)).thenReturn(employee);
		// when
//		Mockito.when(employeeService.saveEmployee(employee)).thenReturn(employee);
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(employeeString))
		.andDo(MockMvcResultHandlers.print());	
	}
	
	
	




	
	
	
}