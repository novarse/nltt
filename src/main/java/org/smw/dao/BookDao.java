package org.smw.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.Handle;
import org.smw.db.Database;
import org.smw.db.DbOperations;
import org.smw.model.Book;
import org.smw.model.BookDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {

	private final Database database;

	/**
	 * Used for unit testing to allow injecting test database.
	 * 
	 * @param database
	 */
	@Autowired
	public BookDao(Database database) {
		this.database = database;
	}

	public List<Book> getBooks() {
		try (Handle h = database.handle()) {
			DbOperations db = h.attach(DbOperations.class);
			return db.getBooks();
		}
	}

	public Book getBook(int id) {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);

			return db.getBook(id);
		}
	}

	public BookDetail getBookDetail(int bookId) {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);

			return db.getBookDetail(bookId);
		}
	}

	public List<Book> getAvailableBooks() {
		try (Handle h = database.handle()) {
			DbOperations db = h.attach(DbOperations.class);
			return db.getAvailableBooks();
		}
	}

	public Map<String, Book> getAvailableBooksMap() {
		Map<String, Book> result = new LinkedHashMap<String, Book>();

		for (Book b : getAvailableBooks()) {
			result.put(Integer.toString(b.getId()), b);
		}

		return result;
	}

}
