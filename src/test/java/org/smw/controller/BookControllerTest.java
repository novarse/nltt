package org.smw.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.smw.dao.BookDao;
import org.smw.model.Book;
import org.smw.model.BookDetail;
import org.smw.view.View;
import org.springframework.ui.ModelMap;

public class BookControllerTest {

	@Test
	public void testBooks() {
		BookDao bookDao = Mockito.mock(BookDao.class);
		List<Book> books = new ArrayList<>();
		Mockito.when(bookDao.getBooks()).thenReturn(books);
		BookController c = new BookController(bookDao);
		ModelMap modelMap = new ModelMap();
		assertEquals(View.BOOKS, c.books(modelMap));
		assertEquals(1, modelMap.size());
		assertEquals(books, modelMap.get("books"));
	}

	@Test
	public void testBookBlank() {
		BookDao bookDao = Mockito.mock(BookDao.class);
		BookController c = new BookController(bookDao);
		assertEquals(View.BOOK, c.bookBlank());
	}

	@Test
	public void testBookForMissingBook() {
		BookDao bookDao = Mockito.mock(BookDao.class);
		BookController c = new BookController(bookDao);
		ModelMap modelMap = new ModelMap();

		int bookId = 0;
		assertEquals(View.BOOK, c.book(bookId, modelMap));
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testBookNonExistantBook() {
		BookDao bookDao = Mockito.mock(BookDao.class);
		BookController c = new BookController(bookDao);
		ModelMap modelMap = new ModelMap();

		int bookId = 5000;
		assertEquals(View.BOOK, c.book(bookId, modelMap));
		assertEquals(0, modelMap.size());
	}

	@Test
	public void testBookNoDetail() {
		BookDao bookDao = Mockito.mock(BookDao.class);
		BookController c = new BookController(bookDao);
		ModelMap modelMap = new ModelMap();

		int bookId = 300;
		Book book = new Book(bookId, "book", "author", "1234", 1, "borrower");
		Mockito.when(bookDao.getBook(bookId)).thenReturn(book);
		assertEquals(View.BOOK, c.book(bookId, modelMap));
		assertEquals(1, modelMap.size());
	}

	@Test
	public void testGetBookDetail() {
		BookDao bookDao = Mockito.mock(BookDao.class);
		BookController c = new BookController(bookDao);
		ModelMap modelMap = new ModelMap();

		int bookId = 300;

		Book book = new Book(bookId, "book", "author", "1234", 1, "borrower");
		BookDetail bookDetail = new BookDetail(1, bookId, "detail");

		Mockito.when(bookDao.getBook(bookId)).thenReturn(book);
		Mockito.when(bookDao.getBookDetail(bookId)).thenReturn(bookDetail);
		assertEquals(View.BOOK, c.book(bookId, modelMap));
		assertEquals(2, modelMap.size());
	}

}