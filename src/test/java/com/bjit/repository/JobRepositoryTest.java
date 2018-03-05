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

import com.bjit.model.JobTitle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobRepositoryTest {
	@Autowired
	private JobRepository jobTitleRepository;

	@Test
	public void shouldFindAllJobTitles() {
		int index = 3;
		List<JobTitle> jobTitleList = new ArrayList<>();
		jobTitleRepository.findAll().forEach(jobTitleList::add);

		assertThat(jobTitleList.get(index).getName(), equalTo(
				"Experienced Developer"));
	}
}