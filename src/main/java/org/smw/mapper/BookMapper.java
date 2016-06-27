package org.smw.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.smw.model.Book;

public class BookMapper implements ResultSetMapper<Book> {
	public Book map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new Book(r.getInt("id"), r.getString("title"), r.getString("author"), r.getString("isbn"), r.getInt("borrowerid"),
				r.getString("borrowername"));
	}

}