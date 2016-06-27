package org.smw.dao;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.UUID;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.smw.db.Database;
import org.smw.db.DatabaseCreator;
import org.smw.model.Book;

public class BookDaoTest {

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
	public void testGetBooks() {
		BookDao dao = new BookDao(db);
		assertEquals(8, dao.getBooks().size());
	}

	@Test
	public void testGetBook() {
		BookDao dao = new BookDao(db);
		assertEquals("Conscience and Its Enemies", dao.getBook(304).getTitle());
	}

	@Test
	public void testGetBookDetail() {
		BookDao dao = new BookDao(db);
		assertEquals(
				"The definitive history of supply-side economics the most consequential economic counterrevolution of the twentieth century and an incredibly timely work that reveals the foundations of America's prosperity at a time when those very foundations are under attack.",
				dao.getBookDetail(305).getDetail());
	}

	@Test
	public void testGetAvailableBooks() {
		BookDao dao = new BookDao(db);
		assertEquals(2, dao.getAvailableBooks().size());
	}

	@Test
	public void testGetAvailableBooksMap() {
		BookDao dao = new BookDao(db);
		Map<String, Book> map = dao.getAvailableBooksMap();
		assertEquals(2, map.size());
		assertEquals("Through the Looking-Glass", map.get("306").getTitle());

		// check map entries are ordered alphabetically as the books have been
		// inserted out of order
		int i = 0;
		for (Map.Entry<String, Book> e : map.entrySet()) {
			if (i++ == 0) {
				assertEquals("Metamagical Themas", e.getValue().getTitle());
			} else if (i == 1) {
				assertEquals("Through the Looking-Glass", e.getValue().getTitle());
			}
		}
	}

}
