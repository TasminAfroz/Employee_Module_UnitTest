package com.bjit.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bjit.model.Department;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentRepositoryTest {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void shouldFindAllDepartments() {
		int index = 3;
		List<Department> departmentList = new ArrayList<>();
		departmentRepository.findAll().forEach(departmentList::add);

		assertThat(departmentList.get(index).getName(), equalTo("Research and Development"));
	}
}