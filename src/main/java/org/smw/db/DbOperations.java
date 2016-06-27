package org.smw.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.smw.mapper.BookDetailMapper;
import org.smw.mapper.BookMapper;
import org.smw.mapper.PersonMapper;
import org.smw.model.Book;
import org.smw.model.BookDetail;
import org.smw.model.Person;

public interface DbOperations {

	@SqlUpdate("CREATE TABLE person(id int primary key, surname varchar(30), firstname varchar(30), phone varchar(30), email varchar(50))")
	void createPersonTable();

	@SqlUpdate("CREATE TABLE book(id int primary key, title varchar(255), author varchar(60), isbn long)")
	void createBookTable();

	@SqlUpdate("CREATE TABLE book_detail(id int primary key, bookid int, detail clob)")
	void createBookDetailTable();

	@SqlUpdate("CREATE TABLE borrow(personid int , bookid int)")
	void createBorrowTable();

	@SqlUpdate("insert INTO person (id, surname, firstname, phone, email) VALUES (:id, :surname, :firstname, :phone, :email)")
	void addPerson(@Bind("id") Integer id, @Bind("surname") String surname, @Bind("firstname") String firstname,
			@Bind("phone") String phone, @Bind("email") String email);

	@SqlUpdate("insert INTO book (id, title, author, isbn) VALUES (:id, :title, :author, :isbn)")
	void addBook(@Bind("id") Integer id, @Bind("title") String title, @Bind("author") String author, @Bind("isbn") long isbn);

	@SqlUpdate("insert INTO book_detail (id, bookid, detail) VALUES (:id, :bookid, :detail)")
	void addBookDetail(@Bind("id") Integer id, @Bind("bookid") Integer bookid, @Bind("detail") String detail);

	@SqlUpdate("MERGE INTO borrow(personid, bookid) key(personid, bookid) VALUES (:personid, :bookid)")
	void insertBorrow(@Bind("personid") Integer personid, @Bind("bookid") Integer bookid);

	@SqlUpdate("DELETE from borrow WHERE personid = :personid and bookid = :bookid")
	void removeBorrow(@Bind("personid") Integer personid, @Bind("bookid") Integer bookid);

	@SqlQuery("SELECT p.id, p.surname, p.firstname, p.phone, p.email, count(b.personid) AS booksborrowedtot " + "from person p "
			+ "LEFT JOIN borrow b ON b.personid = p.id " + "group BY p.id ORDER BY p.surname, p.firstname")
	@Mapper(PersonMapper.class)
	List<Person> getPeople();

	@SqlQuery("SELECT p.id, p.surname, p.firstname, p.phone, p.email, count(b.personid) AS booksborrowedtot " + "from person p "
			+ "LEFT JOIN borrow b ON b.personid = p.id " + "WHERE p.id = :id group BY p.id ORDER BY p.surname, p.firstname")
	@Mapper(PersonMapper.class)
	Person getPerson(@Bind("id") Integer id);

	@SqlQuery("SELECT b.id, b.title, b.author, b.isbn, l.personid AS borrowerid, (p.surname || ', ' || p.firstname) AS borrowername from book b "
			+ "LEFT JOIN borrow l ON (l.bookid = b.id) LEFT JOIN person p ON (l.personid = p.id) ORDER BY title")
	@Mapper(BookMapper.class)
	List<Book> getBooks();

	@SqlQuery("SELECT b.id, b.title, b.author, b.isbn, l.personid AS borrowerid, (p.surname || ', ' || p.firstname) AS borrowername from book b "
			+ "LEFT JOIN borrow l ON (l.bookid = b.id) LEFT JOIN person p ON (l.personid = p.id) WHERE b.id = :bookid ORDER BY title")
	@Mapper(BookMapper.class)
	Book getBook(@Bind("bookid") Integer id);

	@SqlQuery("SELECT bd.id, bd.bookid, bd.detail from book_detail bd "
			+ "JOIN book b ON (bd.bookid = b.id) WHERE b.id = :bookid")
	@Mapper(BookDetailMapper.class)
	BookDetail getBookDetail(@Bind("bookid") Integer bookId);

	@SqlQuery("SELECT b.id, b.title, b.author, b.isbn, l.personid AS borrowerid, (p.surname || ', ' || p.firstname) AS borrowername from book b "
			+ "JOIN borrow l ON (l.bookid = b.id) JOIN person p ON (l.personid = p.id) WHERE l.personid = :personid  "
			+ "ORDER BY b.title")
	@Mapper(BookMapper.class)
	List<Book> getBorrowedBooksByPerson(@Bind("personid") Integer id);

	@SqlQuery("SELECT b.id, b.title, b.author, b.isbn, l.personid AS borrowerid, null AS borrowername from book b "
			+ "LEFT JOIN borrow l ON (l.bookid = b.id) LEFT JOIN person p ON (l.personid = p.id) "
			+ "WHERE l.personid IS NULL ORDER BY title")
	@Mapper(BookMapper.class)
	List<Book> getAvailableBooks();

}
