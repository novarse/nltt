package org.smw.model;

public final class Person {
	// use case doesn't call for editing object so fields made final
	private final int id;
	private final String surname;
	private final String firstname;
	private final String phone;
	private final String email;
	private final int booksBorrowedTot;

	public Person(int id, String surname, String firstname, String phone, String email, int booksBorrowedTot) {
		super();
		this.id = id;
		this.surname = surname;
		this.firstname = firstname;
		this.phone = phone;
		this.email = email;
		this.booksBorrowedTot = booksBorrowedTot;
	}

	public int getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public int getBooksBorrowedTot() {
		return booksBorrowedTot;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

}