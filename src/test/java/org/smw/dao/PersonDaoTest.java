package org.smw.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.smw.db.Database;
import org.smw.db.DatabaseCreator;
import org.smw.model.Book;

public class PersonDaoTest {

	private Database db;
	private JdbcConnectionPool ds;

	@Before
	public void init() {
		ds = JdbcConnectionPool.create("jdbc:h2:mem:nltt" + UUID.randomUUID().toString().substring(0, 8), "", "");
		DBI dbi = new DBI(ds);
		DatabaseCreator.createTestDatabase(dbi);
		db = new Database(ds);
	}

	@After
	public void close() {
		ds.dispose();
	}

	@Test
	public void testGetPeople() {
		PersonDao dao = new PersonDao(db);
		assertEquals(4, dao.getPeople().size());
	}

	@Test
	public void testGetPerson() {
		PersonDao dao = new PersonDao(db);
		assertEquals("bg@gmail.com", dao.getPerson(103).getEmail());

		assertEquals(3, dao.getPerson(102).getBooksBorrowedTot());

		assertEquals(0, dao.getPerson(104).getBooksBorrowedTot());
	}

	@Test
	public void testGetBorrowedBooksByPerson() {
		PersonDao dao = new PersonDao(db);
		List<Book> books = dao.getBorrowedBooksByPerson(101);

		assertEquals(2, books.size());
		assertFalse(books.get(0).getTitle().equals(books.get(1).getTitle()));

		for (Book b : books) {
			assertTrue("The Conservative Mind".equals(b.getTitle()) || "The Road to Serfdom".equals(b.getTitle()));
		}
	}

	/**
	 * Test that multiple calls to the borrowBook method with the same person ID
	 * and book ID does not result in duplicate entries.
	 */
	@Test
	public void testBorrowBook() {
		PersonDao dao = new PersonDao(db);

		int bookId = 306;
		int personId = 101;

		assertEquals(2, dao.getBorrowedBooksByPerson(101).size());

		dao.borrowBook(personId, bookId);
		assertEquals(3, dao.getBorrowedBooksByPerson(101).size());

		dao.borrowBook(personId, bookId);
		assertEquals(3, dao.getBorrowedBooksByPerson(101).size());

	}

	@Test
	public void testReturnBook() {
		PersonDao dao = new PersonDao(db);

		assertEquals(2, dao.getBorrowedBooksByPerson(101).size());

		dao.returnBook(101, 300);
		assertEquals(1, dao.getBorrowedBooksByPerson(101).size());
	}

}
