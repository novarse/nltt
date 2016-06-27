package org.smw.model;

public final class Book {

	// use case doesn't call for editing object so fields made final
	private final int id;
	private final String title;
	private final String author;
	private final String isbn;
	private final int borrowerId;
	private final String borrowerName;

	public Book(int id, String title, String author, String isbn, int borrowerId, String borrowerName) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.borrowerId = borrowerId;
		this.borrowerName = borrowerName;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getBorrowerId() {
		return borrowerId;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

}