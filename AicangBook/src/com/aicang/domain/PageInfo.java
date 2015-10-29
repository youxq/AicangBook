package com.aicang.domain;

public class PageInfo {

	private int totalRow; // 总记录数
	private int totalPage; // 总页数
	private int currentPage = 1; // 当前页，默认为1
	private int pageSize = 50; // 页的大小，默认为50
	private boolean hasPrevious; // 是否有前一页
	private boolean hasNext; // 是否有后一页
	private boolean bof; //是否是第一页
	private boolean eof; //是否是最后一页

	/**
	 * 构造方法
	 * @param totalRow 总记录数
	 * @param pageSize 页的大小
	 * @param page 当前页码
	 */
	public PageInfo(int totalRow, int pageSize, int page) {
		this.totalRow = totalRow;
		this.pageSize = pageSize;

		// 根据总记录数和页的大小算出总页数
		this.totalPage = this.countTotalPage(this.pageSize, this.totalRow);
		
		// 设置当前页
		this.setCurrentPage(page);
		init();
	}

	public int getTotalRow(){
		return this.totalRow;
	}
	
	
	public int getTotalPage() {
		return this.totalPage;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage > this.totalPage) {
			this.currentPage = totalPage;
		} else if (currentPage < 0) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 获取当前页开始的记录索引
	 * @return
	 */
	public int getBeginIndex() {
		int beginIndex = (currentPage - 1) * pageSize;
		return beginIndex;
	}

	/**
	 * 算出总页数
	 * @param pageSize 页的大小
	 * @param totalRow 总记录数
	 * @return
	 */
	public int countTotalPage(int pageSize, int totalRow) {
		int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize
				: totalRow / pageSize + 1;
		return totalPage;
	}

	/**
	 * 获取下一页页码
	 * @return
	 */
	public int getNextPage() {
		if (currentPage + 1 >= this.totalPage) {
			return this.totalPage;
		}
		return currentPage + 1;
	}

	/**
	 * 获取上一页页码
	 * @return
	 */
	public int getPreviousPage() {
		if (currentPage - 1 <= 1) {
			return 1;
		} else {
			return currentPage - 1;
		}
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isBof() {
		return bof;

	}

	public boolean isEof() {

		return eof;

	}

	public boolean hasNext() {

		return currentPage < this.totalPage;

	}

	public boolean hasPrevious() {

		return currentPage > 1;

	}

	public boolean isFirst() {

		return currentPage == 1;

	}

	public boolean isLast() {

		return currentPage >= this.totalPage;

	}

	// 初始化信息

	private void init() {

		this.hasNext = hasNext();

		this.hasPrevious = hasPrevious();

		this.bof = isFirst();

		this.eof = isLast();

	}

}
