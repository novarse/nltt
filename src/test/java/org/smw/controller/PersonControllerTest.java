package org.smw.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;
import org.smw.dao.BookDao;
import org.smw.dao.PersonDao;
import org.smw.model.Book;
import org.smw.model.Person;
import org.smw.view.View;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class PersonControllerTest {

	@Test
	public void testPeople() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);

		ArrayList<Person> people = new ArrayList<Person>();
		Mockito.when(personDao.getPeople()).thenReturn(people);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		assertEquals(View.PEOPLE, c.people(modelMap));
		assertEquals(1, modelMap.size());
		assertEquals(people, modelMap.get("people"));
	}

	@Test
	public void testShowBooks() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		List<Book> books = new ArrayList<Book>();
		int personId = 1;
		Mockito.when(personDao.getBorrowedBooksByPerson(personId)).thenReturn(books);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		ModelAndView mav = c.showBooks(personId, modelMap);
		assertEquals(View.BORROWED_BOOKS, mav.getViewName());
		assertEquals(1, modelMap.size());
		assertEquals(books, modelMap.get("borrowedBooksByPerson"));

	}

	@Test
	public void testPersonBlank() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		assertEquals(View.PERSON, c.personBlank());
	}

	@Test
	public void testPersonMissingPerson() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();

		assertEquals(View.PERSON, c.person(0, modelMap));
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testPersonWithGivenPerson() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();

		int personId = 1;
		Person person = new Person(personId, "surname", "firstname", "phone", "email", 1);
		List<Book> borrowedBooksByPerson = new ArrayList<Book>();
		Map<String, Book> availableBooks = new LinkedHashMap<String, Book>();
		Mockito.when(personDao.getPerson(personId)).thenReturn(person);
		Mockito.when(personDao.getBorrowedBooksByPerson(personId)).thenReturn(borrowedBooksByPerson);
		Mockito.when(bookDao.getAvailableBooksMap()).thenReturn(availableBooks);

		assertEquals(View.PERSON, c.person(personId, modelMap));
		assertEquals(4, modelMap.size());
		assertEquals(person, modelMap.get("person"));
		assertEquals(borrowedBooksByPerson, modelMap.get("borrowedBooksByPerson"));
		assertEquals(availableBooks, modelMap.get("availableBooks"));
		assertEquals(personId, modelMap.get("personId"));
	}

	@Test
	public void testBorrowingGet() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		assertEquals(View.PERSON, c.borrowingGet());
	}

	@Test
	public void testBorrowingMissingPerson() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		int borrowBookId = 20;

		assertEquals(View.PERSON, c.borrowing(0, borrowBookId, modelMap));
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testBorrowingMissingPersonAndBook() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();

		assertEquals(View.PERSON, c.borrowing(0, 0, modelMap));
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testBorrowingMissingBook() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		int personId = 1;
		int borrowBookId = 20;
		int availableBookId = 30;

		Person person = new Person(personId, "surname", "firstname", "phone", "email", 1);
		List<Book> borrowedBooksByPerson = new ArrayList<Book>();
		Book borrowedBook = new Book(borrowBookId, "book", "author", "1234", personId, "surname, firstname");
		borrowedBooksByPerson.add(borrowedBook);
		Map<String, Book> availableBooks = new LinkedHashMap<String, Book>();
		Book availableBook = new Book(availableBookId, "available book", "author", "1234", 0, null);
		availableBooks.put(Integer.toString(availableBookId), availableBook);
		Mockito.when(personDao.getPerson(personId)).thenReturn(person);
		Mockito.when(personDao.getBorrowedBooksByPerson(personId)).thenReturn(borrowedBooksByPerson);
		Mockito.when(bookDao.getAvailableBooksMap()).thenReturn(availableBooks);

		assertEquals(View.PERSON, c.borrowing(personId, 0, modelMap));
		assertEquals(4, modelMap.size());
		assertEquals(borrowedBooksByPerson, modelMap.get("borrowedBooksByPerson"));
		assertEquals(availableBooks, modelMap.get("availableBooks"));
		assertEquals(person, modelMap.get("person"));
		assertEquals(personId, modelMap.get("personId"));
	}

	@Test
	public void testBorrowingWithGivenPersonAndBook() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		int personId = 1;
		int borrowBookId = 20;
		int availableBookId = 30;

		Person person = new Person(personId, "surname", "firstname", "phone", "email", 1);
		List<Book> borrowedBooksByPerson = new ArrayList<Book>();
		Book borrowedBook = new Book(borrowBookId, "book", "author", "1234", personId, "surname, firstname");
		borrowedBooksByPerson.add(borrowedBook);
		Map<String, Book> availableBooks = new LinkedHashMap<String, Book>();
		Book availableBook = new Book(availableBookId, "available book", "author", "1234", 0, null);
		availableBooks.put(Integer.toString(availableBookId), availableBook);
		Mockito.when(personDao.getPerson(personId)).thenReturn(person);
		Mockito.when(personDao.getBorrowedBooksByPerson(personId)).thenReturn(borrowedBooksByPerson);
		Mockito.when(bookDao.getAvailableBooksMap()).thenReturn(availableBooks);

		assertEquals(View.PERSON, c.borrowing(personId, borrowBookId, modelMap));
		assertEquals(4, modelMap.size());
		assertEquals(borrowedBooksByPerson, modelMap.get("borrowedBooksByPerson"));
		assertEquals(availableBooks, modelMap.get("availableBooks"));
		assertEquals(person, modelMap.get("person"));
		assertEquals(personId, modelMap.get("personId"));
	}

	@Test
	public void testReturnBookGet() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		assertEquals(View.PERSON, c.returnBookGet());
	}

	@Test
	public void testReturnBookForMissingPerson() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		int returnBookId = 50;

		ModelAndView mav = c.returnBook(0, returnBookId, modelMap);
		assertEquals(View.PERSON, mav.getViewName());
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testReturnBookForMissingBook() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		int personId = 1;

		ModelAndView mav = c.returnBook(0, 0, modelMap);
		mav = c.returnBook(personId, 0, modelMap);
		assertEquals(View.PERSON, mav.getViewName());
		assertEquals(0, modelMap.size());

	}

	@Test
	public void testReturnBookForMissingPersonAndBook() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();

		ModelAndView mav = c.returnBook(0, 0, modelMap);
		assertEquals(View.PERSON, mav.getViewName());
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testReturnBookForGivenPersonAndBook() {
		PersonDao personDao = Mockito.mock(PersonDao.class);
		BookDao bookDao = Mockito.mock(BookDao.class);
		PersonController c = new PersonController(personDao, bookDao);
		ModelMap modelMap = new ModelMap();
		int personId = 1;
		int borrowBookId = 20;
		int availableBookId = 30;
		int returnBookId = 50;

		Person person = new Person(personId, "surname", "firstname", "phone", "email", 1);
		List<Book> borrowedBooksByPerson = new ArrayList<Book>();
		Book borrowedBook = new Book(borrowBookId, "book", "author", "1234", personId, "surname, firstname");
		borrowedBooksByPerson.add(borrowedBook);
		Map<String, Book> availableBooks = new LinkedHashMap<String, Book>();
		Book availableBook = new Book(availableBookId, "available book", "author", "1234", 0, null);
		availableBooks.put(Integer.toString(availableBookId), availableBook);
		Mockito.when(personDao.getPerson(personId)).thenReturn(person);
		Mockito.when(personDao.getBorrowedBooksByPerson(personId)).thenReturn(borrowedBooksByPerson);
		Mockito.when(bookDao.getAvailableBooksMap()).thenReturn(availableBooks);

		ModelAndView mav = c.returnBook(personId, returnBookId, modelMap);
		assertEquals(View.BORROWED_BOOKS_FOR_PERSON, mav.getViewName());
		assertEquals(3, modelMap.size());
		assertEquals(borrowedBooksByPerson, modelMap.get("borrowedBooksByPerson"));
		assertEquals(availableBooks, modelMap.get("availableBooks"));
		assertEquals(personId, modelMap.get("personId"));

	}
}