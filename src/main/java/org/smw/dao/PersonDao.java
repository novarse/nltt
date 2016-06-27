package org.smw.dao;

import java.util.List;

import org.skife.jdbi.v2.Handle;
import org.smw.db.Database;
import org.smw.db.DbOperations;
import org.smw.model.Book;
import org.smw.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

	private final Database database;

	/**
	 * Used for unit testing to allow injecting test database.
	 * 
	 * @param database
	 */
	@Autowired
	public PersonDao(Database database) {
		this.database = database;
	}

	public List<Book> getBorrowedBooksByPerson(int id) {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);
			List<Book> r = db.getBorrowedBooksByPerson(id);
			return r;
		}
	}

	public List<Person> getPeople() {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);
			return db.getPeople();
		}
	}

	public Person getPerson(int id) {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);

			return db.getPerson(id);
		}
	}

	public void borrowBook(Integer personId, Integer bookId) {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);

			db.insertBorrow(personId, bookId);
		}
	}

	public void returnBook(Integer personId, Integer bookId) {
		try (Handle h = database.handle()) {

			DbOperations db = h.attach(DbOperations.class);

			db.removeBorrow(personId, bookId);
		}
	}

}
