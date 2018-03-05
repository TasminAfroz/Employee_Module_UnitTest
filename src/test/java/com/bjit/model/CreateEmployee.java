package com.bjit.model;

import java.util.ArrayList;
import java.util.List;

public class CreateEmployee {
	
	private Employee employee;
	private List<Employee> employees;
	
	public CreateEmployee() {
		Department department = new Department();
    	department.setId(1);
    	department.setName("Management");
    	
    	
    	
    	JobTitle jobTitle = new JobTitle();
    	jobTitle.setId(1);
    	jobTitle.setName("Chief Executive Officer");
    	
    	
    	
    	Country nationality = new Country();
    	nationality.setId(1);
    	nationality.setCountryName("Bangladesh");
    	
    	employee = new Employee();
    	employee.setName("Tasmin");
    	employee.setEmployeeId(1);
    	employee.setGender("Female");
    	employee.setWorkingAddress("Dhaka");
    	employee.setWorkLocation("Company 1");
    	employee.setEmployeeId(123);
    	employee.setHomeAddress("Barisal");
    	employee.setWorkMobile("01750336470");
    	employee.setWorkMobile("61606");
    	employee.setWorkTime("Part Time");
    	employee.setDateOfBirth(null);
    	employees = new ArrayList<>();
    	employees.add(employee);
    	
		
	}
	
	public List<Employee> getEmployeeList(){
		return employees;
	}
	
	public Employee getEmployee(){
		return employee;
	}
}