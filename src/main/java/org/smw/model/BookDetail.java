package org.smw.model;

public final class BookDetail {
	// use case doesn't call for editing object so fields made final
	private final int id;
	private final int bookId;
	private final String detail;

	public BookDetail(int id, int bookId, String detail) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public int getBookId() {
		return bookId;
	}

	public String getDetail() {
		return detail;
	}

}
