package com.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.test.web.servlet.MockMvc;
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

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class EmployeeServiceTest {
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
	public static void init() {
		createEmployee = new CreateEmployee();
	}

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(restEmployeeController).build();
	}

	// @SuppressWarnings("unchecked")
	@Test
	public void testFindAll() throws Exception {

		// List<Employee> expected = new ArrayList<Employee>();
		// Page foundPage = new PageImpl<Employee>(expected);

		// when(employeeService.findAll(limit)).thenReturn(foundPage);

		// List<Employee> actual = repository.findPersonsForPage(SEARCH_TERM,
		// PAGE_INDEX);

		//
		// Employee user = new Employee(1, "Daenerys Targaryen");
		//
		// Employee user2 = new Employee(2, "Daeneryssss Targaryen");

		List<Employee> users = Arrays.asList(new Employee(1, "Daenerys Targaryen"), new Employee(2, "John Snow"));
		final Page<Employee> page = new PageImpl<>(users);
		when(employeeService.findAll(limit)).thenReturn(page);

		// new PageImpl<Employee>(users, new PageRequest(1, 1), users.size());
		// when(employeeService.findAll(new PageRequest(1,
		// 2))).thenReturn((PageImpl<Employee>) users);
	}
	
//	@Test
//	public Page<Employee> testFindAllPage() throws Exception {
//
//		// List<Employee> expected = new ArrayList<Employee>();
//		// Page foundPage = new PageImpl<Employee>(expected);
//
//		// when(employeeService.findAll(limit)).thenReturn(foundPage);
//
//		// List<Employee> actual = repository.findPersonsForPage(SEARCH_TERM,
//		// PAGE_INDEX);
//
//		//
//		// Employee user = new Employee(1, "Daenerys Targaryen");
//		//
//		// Employee user2 = new Employee(2, "Daeneryssss Targaryen");
//
//		List<Employee> users = Arrays.asList(new Employee(1, "Daenerys Targaryen"), new Employee(2, "John Snow"));
//		final Page<Employee> page = new PageImpl<>(users);
//		when(employeeService.findAll(limit)).thenReturn(page);
//
//		// new PageImpl<Employee>(users, new PageRequest(1, 1), users.size());
//		// when(employeeService.findAll(new PageRequest(1,
//		// 2))).thenReturn((PageImpl<Employee>) users);
//		
//		 return employeeRepository.findAll(limit);
//	}	
////	
	
	
	
	
	@Test
	public void testFindById() throws Exception {

		Employee user = new Employee(1, "Daenerys Targaryen");
		when(employeeService.findById(1)).thenReturn(user);
		// when(employeeService.findById((1)).thenReturn(user);
		//
		// Integer id = 1;
		// Leave leave1 = leaveRepo.findOne(id);
		// System.out.println(leave1);
		// System.out.println(leave);
		// assertEquals(leave, leave1);
	}
	
	@Test
	public void testSaveEmployee() {
		doNothing().when(employeeService).saveEmployee(employee);

	}
	
	@Test
	public void testSaveEmployeeRepo(){
		//given
    	Employee newEmployee = new Employee();
    
		
		//when
		Mockito.when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);
		
		//then
//		Employee createdEmployee = employeeRepository.save(newEmployee);
		newEmployee.setName("AB");
		employeeService.saveEmployee(newEmployee);
//		assertEquals(newEmployee.getName(), createdEmployee.getName());
	}
	
	@Test
	public void testUpdateEmployeeRepo(){
//		//given
//    	Employee newEmployee = new Employee();
//    
//		
//		//when
//		Mockito.when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);
//		
//		//then
////		Employee createdEmployee = employeeRepository.save(newEmployee);
//		newEmployee.setName("AB");
//		employeeService.updateUser(newEmployee);
////		assertEquals(newEmployee.getName(), createdEmployee.getName());
		
		
		
		Employee currentEmployee = createEmployee.getEmployee();
		currentEmployee.setName("Afroz");
		
		//when
		Mockito.when(employeeRepository.save(currentEmployee)).thenReturn(currentEmployee);
		
		//then
		Employee updatedEmployee = employeeRepository.save(currentEmployee);
		assertEquals(currentEmployee, updatedEmployee);
		employeeService.saveEmployee(updatedEmployee);
	}
}
