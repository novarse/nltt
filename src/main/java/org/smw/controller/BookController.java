package org.smw.controller;

import org.smw.dao.BookDao;
import org.smw.model.Book;
import org.smw.model.BookDetail;
import org.smw.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

	private final BookDao bookDao;

	// Note that some developers prefer to use field injection over constructor
	// injection because debugging/tracing is reputed to be easier.
	@Autowired
	public BookController(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String books(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("books", bookDao.getBooks());
		return View.BOOKS;
	}

	/**
	 * Used if book page is called without id.
	 * 
	 * @return book page name
	 */
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String bookBlank() {

		return View.BOOK;
	}

	/**
	 * Show book details when a book id is passed to the page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String book(@PathVariable("id") int id, @ModelAttribute("model") ModelMap model) {

		if (id != 0) {
			Book book = bookDao.getBook(id);
			if (book != null) {
				model.addAttribute("book", book);
				BookDetail bookDetail = bookDao.getBookDetail(id);
				if (bookDetail != null) {
					model.addAttribute("bookDetail", bookDetail);
				}
			}
		}

		return View.BOOK;
	}

}