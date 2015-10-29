package com.aicang.domain;

/**
 * AbstractBook entity provides the base persistence definition of the Book
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBook implements java.io.Serializable {

	// Fields

	private Integer id;
	private String isbn;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookPublishDate;
	private Integer bookNum;
	private String lastUpdateUser;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractBook() {
	}

	/** minimal constructor */
	public AbstractBook(String isbn, String bookName, Integer bookNum) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.bookNum = bookNum;
	}

	/** full constructor */
	public AbstractBook(String isbn, String bookName, String bookAuthor,
			String bookPublisher, String bookPublishDate, Integer bookNum,
			String lastUpdateUser, String remark) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
		this.bookPublishDate = bookPublishDate;
		this.bookNum = bookNum;
		this.lastUpdateUser = lastUpdateUser;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return this.bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return this.bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookPublishDate() {
		return this.bookPublishDate;
	}

	public void setBookPublishDate(String bookPublishDate) {
		this.bookPublishDate = bookPublishDate;
	}

	public Integer getBookNum() {
		return this.bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}