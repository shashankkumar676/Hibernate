package com.in28minutes.database.databasedemo.jdbc;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.jdbc.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
	}

	public Person findById(int id) {
		return jdbcTemplate.queryForObject("select * from person where id=?", new Object[] { id },
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("delete  from person where id=?", new Object[] { id });
	}

	public int insert(Person p) {
		return jdbcTemplate.update("insert into person values(?,?,?,?)",
				new Object[] { p.getId(), p.getName(), p.getLocation(), new Timestamp(p.getBirthDate().getTime()) });
	}

	public int update(Person p) {
		return jdbcTemplate.update("update person set name = ? where location=?",
				new Object[] { p.getName(), p.getLocation() });
	}
}
