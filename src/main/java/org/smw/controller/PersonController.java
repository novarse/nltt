package org.smw.controller;

import org.smw.dao.BookDao;
import org.smw.dao.PersonDao;
import org.smw.model.Person;
import org.smw.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

	// Note that some developers prefer to use field injection over constructor
	// injection because debugging/tracing is reputed to be easier.
	private final PersonDao personDao;

	private final BookDao bookDao;

	@Autowired
	public PersonController(PersonDao personDao, BookDao bookDao) {
		this.personDao = personDao;
		this.bookDao = bookDao;
	}

	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public String people(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("people", personDao.getPeople());

		return View.PEOPLE;
	}

	/**
	 * Called by link on people page to show books borrowed by {@link Person}
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/people/show/{id}", method = RequestMethod.GET)
	public ModelAndView showBooks(@PathVariable("id") int id, @ModelAttribute("model") ModelMap model) {

		if (id != 0) {
			// load books borrowed by person into borrowedBooksByPerson list
			model.addAttribute("borrowedBooksByPerson", personDao.getBorrowedBooksByPerson(id));
		}

		return new ModelAndView(View.BORROWED_BOOKS);
	}

	/**
	 * Used if person page is called without id.
	 * 
	 * @return person page name
	 */
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String personBlank() {
		return View.PERSON;
	}

	/**
	 * Show person details when a person id is passed to the page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public String person(@PathVariable("id") int id, @ModelAttribute("model") ModelMap model) {

		if (id != 0) {
			model.addAttribute("person", personDao.getPerson(id));

			// load books borrowed by person into borrowedBooksByPerson list
			model.addAttribute("borrowedBooksByPerson", personDao.getBorrowedBooksByPerson(id));
			model.addAttribute("availableBooks", bookDao.getAvailableBooksMap());

			// personId is used in an imported section
			// (borrowedBooksByPerson.ftl)
			model.addAttribute("personId", id);
		}

		return View.PERSON;
	}

	@RequestMapping(value = "/person/borrowing", method = RequestMethod.GET)
	public String borrowingGet() {
		return View.PERSON;
	}

	@RequestMapping(value = "/person/borrowing", method = RequestMethod.POST)
	public String borrowing(@RequestParam("personId") Integer personId, @RequestParam("bookId") Integer bookId,
			@ModelAttribute("model") ModelMap model) {
		if (personId != 0) {
			model.addAttribute("person", personDao.getPerson(personId));

			if (bookId != 0) {
				personDao.borrowBook(personId, bookId);
			}
			// load books borrowed by person into borrowedBooksByPerson list
			model.addAttribute("borrowedBooksByPerson", personDao.getBorrowedBooksByPerson(personId));
			model.addAttribute("availableBooks", bookDao.getAvailableBooksMap());
			model.addAttribute("personId", personId);
		}

		return View.PERSON;
	}

	@RequestMapping(value = "/person/returnBook", method = RequestMethod.GET)
	public String returnBookGet() {
		return View.PERSON;
	}

	@RequestMapping(value = "/person/returnBook", method = RequestMethod.POST)
	public ModelAndView returnBook(@RequestParam("personId") Integer personId, @RequestParam("bookId") Integer bookId,
			@ModelAttribute("model") ModelMap model) {

		if (personId != 0 && bookId != 0) {
			personDao.returnBook(personId, bookId);

			model.addAttribute("borrowedBooksByPerson", personDao.getBorrowedBooksByPerson(personId));
			model.addAttribute("availableBooks", bookDao.getAvailableBooksMap());
			model.addAttribute("personId", personId);

			return new ModelAndView(View.BORROWED_BOOKS_FOR_PERSON);
		} else {
			return new ModelAndView(View.PERSON);
		}
	}

}