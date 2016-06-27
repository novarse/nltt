package org.smw.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.smw.model.Person;

public class PersonMapper implements ResultSetMapper<Person> {
	public Person map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Person(r.getInt("id"), r.getString("surname"), r.getString("firstname"), r.getString("phone"),
				r.getString("email"), r.getInt("booksBorrowedTot"));
	}

}