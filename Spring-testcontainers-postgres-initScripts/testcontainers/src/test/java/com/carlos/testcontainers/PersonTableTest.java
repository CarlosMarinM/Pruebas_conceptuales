package com.carlos.testcontainers;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.carlos.testcontainers.dao.PersonDao;
import com.carlos.testcontainers.model.Person;

public class PersonTableTest extends AbstractIntegrationTest {

	@Autowired
	private PersonDao personDao;

	@Test
	public void test01InsertPerson() {
		Person person = new Person();
		person.setFirstName("Carlos Art");
		person.setLastName("Mendez");
		person.setMail("carlosmarin@hotmail.com");
		personDao.createPerson(person);
	}

	@Test
	public void test02GetAllPersons() {
		List<Person> allPersons = personDao.getAllPersons();
		allPersons.forEach(System.out::println);
	}
}
