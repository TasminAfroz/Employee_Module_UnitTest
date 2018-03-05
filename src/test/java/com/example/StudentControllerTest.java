
package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bjit.controller.EmployeeController;
import com.bjit.model.Country;
import com.bjit.model.Department;
import com.bjit.model.Employee;
import com.bjit.model.JobTitle;
import com.bjit.repository.CountryRepository;
import com.bjit.repository.DepartmentRepository;
import com.bjit.repository.EmployeeRepository;
import com.bjit.repository.JobRepository;
import com.bjit.service.EmployeeService;
import com.bjit.serviceImpl.EmployeeServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class StudentControllerTest {
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
	static private Employee employee1;
	MockMvc mockMvc;
	PageRequest limit;
	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	DepartmentRepository departmentRepository;
	@Mock
	JobRepository jobRepository;
	@MockBean
	CountryRepository countryRepository;
	private String showList = "http://localhost:8080/employee/list";

	private String saveEmployee = "http://localhost:8080/api/employee/save";

	private String updateEmployee = "http://localhost:8080/api/employee/update";

	@Before
	public void setUp() {
		
		
		mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(employeeService)).build();
	}

	
	
	

	@Test
	public void shouldGetEmployeeById2() throws Exception {
		
		Employee user = new Employee(1, "Daenerys Targaryen");
		employeeRepository.save(user);
//		when(employeeRepository.findOne(1)).thenReturn(user);
		int id = 1;
		Employee employee = employeeRepository.findOne(1);
		assertEquals(id, employee.getEmployeeId());
		System.out.println(employee.getName());
	}
	
	

	
	@Test
	public void testHomePag() throws Exception {
		mockMvc.perform(get("/employee/list")).andExpect(status().isOk()).andExpect(view().name("homePage"))
				.andDo(print());

	}

	@Test
	public void testemployeeDetails() throws Exception {
		Employee user = new Employee(1, "Daenerys Targaryen");
		when(employeeRepository.findOne(1)).thenReturn(user);
		mockMvc.perform(get("/employee/details/1")).andExpect(status().isOk()).andExpect(view().name("employeeForm"))
				.andDo(print());

	}
	
	
	@Test
	public void testCreate() throws Exception {
//		List<Country> countryList = (List<Country>) countryRepository.findAll();
		List <Country> countryList = new ArrayList<Country>();
		Country c = new Country();
		c.setCountryName("ads");
		System.out.println(c.getCountryName());
		countryList.add(c);
		when(countryRepository.findAll()).thenReturn(countryList);
//		System.out.println(countryList.size());
		mockMvc.perform(get("/employee/create" )).andExpect(status().isOk()).andExpect(view().name("employeeForm"))
				.andDo(print());

	}
	

	// Checking the negative response for findById of resController
	@Test
	public void test_get_by_id_fail_404_not_found() throws Exception {
		
		when(employeeService.findById(1)).thenReturn(null);
		mockMvc.perform(get("/api/employee/details/{id}", 1)).andExpect(status().isNotFound());
		verify(employeeService, never()).findById(1);
		verifyNoMoreInteractions(employeeService);
	}
	
	
	
//	
//	@Test
//	public void test_get_by_id_pass_404_not_found() throws Exception {
//		Employee user = new Employee(1, "Daenerys Targaryen");
//		
//		when(employeeService.findById(1)).thenReturn(user);
//		mockMvc.perform(get("/api/employee/details/1").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
////		when(employeeService.findById(1)).thenReturn(user);
////		mockMvc.perform(get("/api/employee/details/1")).andExpect(status().isOk());
//////		verify(employeeService, times(1)).findById(1);
//////		verifyNoMoreInteractions(employeeService);
//	}


	
//	@Test
//	public void test_get_by_id_success() throws Exception {
//		Employee user = new Employee(1, "Daenerys Targaryen");
//		when(employeeService.findById(1)).thenReturn(user);
//		
////		mockMvc.perform(get("/api/employee/details/1")).andDo(MockMvcResultHandlers.print()).andDo(print());
//		mockMvc.perform(get("/api/employee/details/1")).andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(jsonPath("$.employeeId", is(1))).andExpect(jsonPath("$.name", is("Daenerys Targaryen")));
////	
//	
////		
//		verify(employeeService, times(1)).findById(1);
//		verifyNoMoreInteractions(employeeService);
//	}
//	
	
	
	// test employee creation
//	@Test
//	public void testSaveEmployee() {
//		doNothing().when(employeeService).saveEmployee(employee);
//
//	}

//	@Test
//	public void testGetAll() {
//		doNothing().when(employeeService).findAll(limit);
//
//	}

	// does "/employee/list" URL gets hitted
	@Test
	public void testHomePage() throws IOException {

		assertNotNull(employeeController.homePage());

	}

	// url to show list requestmapping get
	@Test
	public void testIfGet() throws Exception {
		// assertNotNull(mainController.InvoiceList(modelMock));
		mockMvc.perform(get(showList)).andExpect(status().isOk());
		// mockMvc.perform(post(saveEmployee)).andExpect(status().isOk());

	}

	// url t0 save epmloyee is requestmapping post
	@Test
	public void testIfPost() throws Exception {
		// assertNotNull(mainController.InvoiceList(modelMock));
		// mockMvc.perform(get(showList)).andExpect(status().isOk());
		mockMvc.perform(post(saveEmployee));

	}

	// url t0 update epmloyee is requestmapping put
	@Test
	public void testIfPut() throws Exception {
		// assertNotNull(mainController.InvoiceList(modelMock));
		// mockMvc.perform(get(showList)).andExpect(status().isOk());
		mockMvc.perform(put(updateEmployee));

	}

//	@Test
//	public void testEmployeeForm() throws IOException {
//		model.addAttribute(country);
//		model.addAttribute(department);
//		model.addAttribute(job);
//		model.addAttribute(employee);
//		assertNotNull(employeeController.employeeForm(employee, model));
//
//	}

//	@Test
//	public void TestEmployeeDetails() throws IOException {
//
//		assertNotNull(employeeController.employeeDetails(2, model));
//
//	}



	
}