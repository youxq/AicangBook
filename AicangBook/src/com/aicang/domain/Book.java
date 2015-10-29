package com.aicang.domain;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */
public class Book extends AbstractBook implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** minimal constructor */
	public Book(String isbn, String bookName, Integer bookNum) {
		super(isbn, bookName, bookNum);
	}

	/** full constructor */
	public Book(String isbn, String bookName, String bookAuthor,
			String bookPublisher, String bookPublishDate, Integer bookNum,
			String lastUpdateUser, String remark) {
		super(isbn, bookName, bookAuthor, bookPublisher, bookPublishDate,
				bookNum, lastUpdateUser, remark);
	}

}
