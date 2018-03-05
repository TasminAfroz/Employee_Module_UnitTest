package com.bjit.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjit.model.Employee;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {
	@Autowired
	private EmployeeRepository employeeRepository;
	PageRequest limit;
	@Test
	public void shouldFindAllEmployees() {
	
		List<Employee> employeeList = new ArrayList<>();
		employeeRepository.findAll(limit).forEach(employeeList::add);

		
	}
	@Test
	public void shouldGetEmployeeDetails() {
		int index = 7;
		List<Employee> employeeList = new ArrayList<>();
		employeeRepository.findAll().forEach(employeeList::add);

		assertThat(employeeList.get(index).getName(), equalTo(
				"Tania"));
	}
	@Test
	public void shouldFindByName() {
		Employee recentEmployee=  employeeRepository.findByName("Tasmin");
		assertThat(recentEmployee.getDepartment().getName(), equalTo("Management"));
	}
//
	@Test
	public void shouldFindById() {
		Employee employee = employeeRepository.findOne(7);
		assertThat(employee.getEmployeeId(), equalTo(7));
	}
}