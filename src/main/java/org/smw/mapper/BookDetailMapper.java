package org.smw.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.smw.model.BookDetail;

public class BookDetailMapper implements ResultSetMapper<BookDetail> {
	public BookDetail map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new BookDetail(r.getInt("id"), r.getInt("bookid"), r.getString("detail"));
	}

}