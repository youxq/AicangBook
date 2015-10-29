package com.aicang.struts2.book;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicang.domain.Book;
import com.aicang.domain.PageResultSet;
import com.aicang.service.book.BookService;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("BookManage")
public class BookAction extends ActionSupport {

	private Integer bookId;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookPublishDate;
	private String ISBN;
	private Integer bookNum;
	private String storagePath;
	private String fileName;

	// 选中的选项
	private Integer[] selectedBookIds;
	// dataTable属性
	private String aoData;
	@Autowired
	private BookService bookService;

	/**
	 * 扫码之后添加书籍
	 */
	@Action(value = "/AddBook")
	public void addBook() {
		String message = "";
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		book.setBookPublisher(bookPublisher);
		book.setBookPublishDate(bookPublishDate);
		book.setIsbn(ISBN);
		book.setBookNum(1);
		try {
			if (bookService.hasBook(book)) {
				bookService.updateBookNum(book);
			} else {
				bookService.addBook(book);
			}
			message = "添加书籍成功";
		} catch (Exception e) {
			message = "添加书籍失败，请重试";
		}
		try {
			HttpServletResponse response = BaseAction.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建输出流
	 * 
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception {
		System.out.println("进入inputStream");
		storagePath = bookService.getExcel();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(date);
		fileName = "爱仓书单(" + dateNowStr + ").xls";
		this.fileName = new String(fileName.getBytes(), "ISO8859-1");
		return new FileInputStream(storagePath);
	}

	/**
	 * 获取所有书籍Excel
	 * 
	 * @return
	 */
	@Action(value = "/GetBookExcel", results = {
			@Result(name = "success", type = "stream", params = {
					"contentType",
					"application/octet-stream;charset=ISO8859-1", "inputName",
					"inputStream", "contentDisposition",
					"attachment;filename=\"${fileName}\"", "bufferSize", "4096" }),
			@Result(name = "error", location = "/error.jsp") })
	public String getBookExcel() {
		return SUCCESS;
	}

	/**
	 * 显示书籍列表
	 */
	@Action(value = "/GetBookTable")
	public void getBookTable() {

		String[] tableHead = {null,"bookName","bookAuthor","bookPublisher","bookPublishDate","isbn","bookNum"};
		// 接收dataTable传过来的属性
		JSONArray jsonArray = null;
		try {
			jsonArray = JSONArray.fromObject(aoData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 解析属性值
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
		String sSearch = ""; // 搜索文本
		int iSortCol = 0; // 排序列
		String sSortDir = "asc"; //　排序方式
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			System.out.println("name: " + obj.get("name") + ",value=" + obj.get("value"));
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();

			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getInt("value");

			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getInt("value");
			
			if (obj.get("name").equals("sSearch"))
				sSearch = obj.getString("value");
			
			if (obj.get("name").equals("iSortCol_0"))
				iSortCol = obj.getInt("value");
			
			if (obj.get("name").equals("sSortDir_0"))
				sSortDir = obj.getString("value");
		}
		int currentPage = iDisplayStart / iDisplayLength + 1;
		PageResultSet<Book> pageResult = bookService.queryByPage(
				iDisplayLength, currentPage,sSearch,tableHead[iSortCol],sSortDir);
		List<Book> bookList = pageResult.getList();
		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			String[] temp = {
					"<label class=\"position-relative\" style=\"padding-top:4px;margin-left:8px;\"> <input type=\"checkbox\" name=\"selectedBookIds\" value=\"" + book.getId() + "\" class=\"ace\" /> <span class=\"lbl\"></span></label>",
					book.getBookName(), book.getBookAuthor(),
					book.getBookPublisher(), book.getBookPublishDate(),
					book.getIsbn(), book.getBookNum() + "",
					"<button type=\"button\" class=\"btn btn-xs\" onclick=\"edit(this)\">编辑</button>"
							+ " <a class=\"btn btn-xs\" onclick=\"return confirm('您确认删除该书籍？')\" href=\"/AicangBook/BookManage/DeleteBook?bookId=" + book.getId() + "\">删除</a>"};
			result.add(temp);
		}

		// 回传数据
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sEcho", sEcho);
		jsonObject.put("iTotalRecords", bookList.size()); // 实际的行数
		jsonObject.put("iTotalDisplayRecords", bookList.size()); // 显示的行数,这个要和上面写的一样

		jsonObject.put("aaData", result);// 要以JSON格式返回
		try {
			BaseAction.getResponse().getWriter().print(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 编辑书籍信息
	 * @return
	 */
	@Action(value="/EditBook",results={@Result(name="success",location="/admin/common/book_admin.jsp")})
	public String editBook(){
		Book book = new Book();
		book.setId(bookId);
		book.setBookName(bookName);
		book.setBookAuthor(bookAuthor);
		book.setBookPublisher(bookPublisher);
		book.setBookPublishDate(bookPublishDate);
		book.setIsbn(ISBN);
		book.setBookNum(bookNum);
		bookService.updateBook(book);
		return SUCCESS;
	}
	
	/**
	 * 删除书籍
	 * @return
	 */
	@Action(value="/DeleteBook",results={@Result(name="success",location="/admin/common/book_admin.jsp")})
	public String deleteBook(){
		bookService.deleteBook(bookId);
		return SUCCESS;
	}
	
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookPublishDate() {
		return bookPublishDate;
	}

	public void setBookPublishDate(String bookPublishDate) {
		this.bookPublishDate = bookPublishDate;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAoData() {
		return aoData;
	}

	public void setAoData(String aoData) {
		this.aoData = aoData;
	}

	public Integer[] getSelectedBookIds() {
		return selectedBookIds;
	}

	public void setSelectedBookIds(Integer[] selectedBookIds) {
		this.selectedBookIds = selectedBookIds;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	
}
