package com.carlos.testcontainers.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.carlos.testcontainers.model.Person;

public class PersonRowmapper implements RowMapper<Person> {

	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("id"));
		person.setFirstName(rs.getString("firstname"));
		person.setLastName(rs.getString("lastname"));
		person.setMail(rs.getString("mail"));

		return person;
	}

}
