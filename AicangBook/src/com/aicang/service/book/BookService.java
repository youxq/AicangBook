package com.aicang.service.book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicang.domain.PageInfo;
import com.aicang.domain.PageResultSet;
import com.aicang.domain.Book;
import com.aicang.domain.BookDAO;
import com.aicang.domain.PageDAO;

@Component("BookService")
public class BookService {

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private PageDAO pageDAO;
	/**
	 * 添加书籍
	 * 
	 * @param book
	 */
	public void addBook(Book book) {
		bookDAO.save(book);
	}

	/**
	 * 将数量加1
	 * 
	 * @param b
	 */
	public void updateBookNum(Book b) {
		List<Book> bookList = bookDAO.findByIsbn(b.getIsbn());
		for (Book book : bookList) {
			if (book.getBookName().equals(b.getBookName())) {
				int bookNum = book.getBookNum();
				book.setBookNum(bookNum + 1);
				bookDAO.merge(book);
			}
		}
	}

	/**
	 * 查询是否已经存在这本书
	 * 
	 * @param b
	 * @return
	 */
	public boolean hasBook(Book b) {
		try {
			List<Book> bookList = bookDAO.findByIsbn(b.getIsbn());
			for (Book book : bookList) {
				if (book.getBookName().equals(b.getBookName())) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	/**
	 * 获取全部书籍信息
	 * @return
	 */
	public List<Book> getAllExistBook() {
		return bookDAO.findAllExistBook();
	}

	/**
	 * 将全部书单整理成Excel文档
	 * @return
	 */
	public String getExcel() {
		
		// 创建Excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		// sheet 对应一个工作页
		HSSFSheet sheet = wb.createSheet("爱仓书籍信息");

		// 设置标题字体格式
		HSSFCellStyle style = wb.createCellStyle(); // 样式对象
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		HSSFFont font = wb.createFont();
		font.setFontName("Courier New"); // ---》设置字体，是什么类型例如：宋体
		font.setItalic(true); // --->设置是否是加粗
		style.setFont(font); // --->将字体格式加入到style1中

		HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
		String[] titleArray = { "书名", "作者", "出版社", "出版时间", "ISBN", "数量" };
		for (int i = 0; i < titleArray.length; i++) {
			HSSFCell cell = firstrow.createCell((short) i);
			cell.setCellValue(new HSSFRichTextString(titleArray[i]));
			cell.setCellStyle(style);
		}

		List<Book> bookList = this.getAllExistBook();
		int nextRow = 1;
		for (Book book : bookList) {
			HSSFRow row = sheet.createRow(nextRow); // 下标为1的行开始
			String[] bookValue = { book.getBookName(), book.getBookAuthor(),
					book.getBookPublisher(), book.getBookPublishDate(),
					book.getIsbn(), book.getBookNum().toString() };
			for (int i = 0; i < titleArray.length; i++) {
				// 在一行内循环
				HSSFCell cell = row.createCell((short) i);
				// 设置表格的编码集，使支持中文
				// // 先判断数据库中的数据类型
				// 将结果集里的值放入电子表格中
				String value = bookValue[i];
				if (value == null)
					value = "暂缺";

				cell.setCellValue(new HSSFRichTextString(value));
			}
			nextRow++;
		}
		String path = ServletActionContext.getServletContext().getRealPath(
				"/upload")
				+ "\\爱仓书单.xls"; // 设置保存目录
		OutputStream out;
		try {
			out = new FileOutputStream(path);
			wb.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("数据导出成功");
		return path;
	}

	/**
	 * 根据页的大小和当前的页请求结果
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageResultSet<Book> queryByPage(int pageSize, int page,String searchString,String sortCol,String sortDir) {

		// 查询语句
		String hql = "From Book as b where (b.bookName like '%" + searchString
				+ "%' or b.bookAuthor like '%" + searchString 
				+ "%' or b.bookPublisher like '%" + searchString
				+ "%') and b.bookNum != 0" ;
		
		if(sortCol != null){
			hql += " order by b." + sortCol
					+ " " + sortDir; // 查询HQL语句
		}
				

		System.out.println(hql);
		int totalRow = pageDAO.queryRowCount(hql); // 计算总记录个数

		PageInfo pageinfo = new PageInfo(totalRow, pageSize, page);

		// 获取该页的记录
		List<Book> list = pageDAO.queryByPage(hql,
				pageinfo.getBeginIndex(), pageinfo.getPageSize());

		PageResultSet<Book> pageResultSet = new PageResultSet<Book>();

		pageResultSet.setList(list);

		pageResultSet.setPageInfo(pageinfo);

		return pageResultSet;

	}

	/**
	 * 更新书籍
	 * @param book
	 * @return
	 */
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		try{
			bookDAO.merge(book);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 删除书籍，其实是将数量改为0
	 * @param bookId
	 * @return
	 */
	public boolean deleteBook(Integer bookId) {
		// TODO Auto-generated method stub
		try{
			Book book = bookDAO.findById(bookId);
			book.setBookNum(0);
			bookDAO.merge(book);
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
