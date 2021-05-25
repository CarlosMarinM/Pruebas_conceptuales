package com.carlos.testcontainers.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.carlos.testcontainers.model.Person;
import com.carlos.testcontainers.rowmapper.PersonRowmapper;

@Repository
public class PersonDao {

	JdbcTemplate jdbcTemplate;

	private final String SQL_FIND_PERSON = "select * from person where id = ?";
	private final String SQL_DELETE_PERSON = "delete from person where id = ?";
	private final String SQL_UPDATE_PERSON = "update person set firstname = ?, lastname = ?, mail  = ? where id = ?";
	private final String SQL_GET_ALL = "select * from person";
	private final String SQL_INSERT_PERSON = "insert into person(firstname, lastname, mail) values(?,?,?)";

	@Autowired
	public PersonDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Person getPersonById(Integer id) {
		return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[] { id }, new PersonRowmapper());
	}

	public List<Person> getAllPersons() {
		return jdbcTemplate.query(SQL_GET_ALL, new PersonRowmapper());
	}

	public boolean deletePerson(Person person) {
		return jdbcTemplate.update(SQL_DELETE_PERSON, person.getId()) > 0;
	}

	public boolean updatePerson(Person person) {
		return jdbcTemplate.update(SQL_UPDATE_PERSON, person.getFirstName(), person.getLastName(), person.getMail(),
				person.getId()) > 0;
	}

	public boolean createPerson(Person person) {
		return jdbcTemplate.update(SQL_INSERT_PERSON, person.getFirstName(), person.getLastName(),
				person.getMail()) > 0;
	}
}
