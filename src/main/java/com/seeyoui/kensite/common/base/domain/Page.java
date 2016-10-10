package com.seeyoui.kensite.common.base.domain;

import java.util.ArrayList;
import java.util.List;


public class Page<T> {
	
	private int page = 1; // 当前页码
	private int rows = 20; // 页面大小
	
	private long total;// 总记录数，设置为“-1”表示不查询总数
	
	private int first;// 首页索引
	private int last;// 尾页索引
	private int prev;// 上一页索引
	private int next;// 下一页索引
	
	private boolean firstPage;//是否是第一页
	private boolean lastPage;//是否是最后一页
	
	private List<T> list = new ArrayList<T>();//数据集合
	
	/**
	 * 构造方法
	 * @param page 当前页码
	 * @param rows 分页大小
	 */
	public Page(int page, int rows) {
		this(page, rows, 0);
	}
	
	/**
	 * 构造方法
	 * @param page 当前页码
	 * @param rows 分页大小
	 * @param total 数据条数
	 * @param list 本页数据对象列表
	 */
	public Page(int page, int rows, long total) {
		this.setTotal(total);
		this.setPage(page);
		this.setRows(rows);
		initialize();
	}
	
	/**
	 * 初始化参数
	 */
	public void initialize(){
		this.first = 1;
		this.last = (int)(total / (this.rows < 1 ? 20 : this.rows) + first - 1);
		if (this.total % this.rows != 0 || this.last == 0) {
			this.last++;
		}
		if (this.last < this.first) {
			this.last = this.first;
		}
		if (this.page <= 1) {
			this.page = this.first;
			this.firstPage=true;
		}
		if (this.page >= this.last) {
			this.page = this.last;
			this.lastPage=true;
		}
		if (this.page < this.last - 1) {
			this.next = this.page + 1;
		} else {
			this.next = this.last;
		}
		if (this.page > 1) {
			this.prev = this.page - 1;
		} else {
			this.prev = this.first;
		}
		if (this.page < this.first) {// 如果当前页小于首页
			this.page = this.first;
		}
		if (this.page > this.last) {// 如果当前页大于尾页
			this.page = this.last;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	
	/**
	 * 获取本页数据对象列表
	 * @return List<T>
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置本页数据对象列表
	 * @param list
	 */
	public Page<T> setList(List<T> list) {
		this.list = list;
		initialize();
		return this;
	}
}
