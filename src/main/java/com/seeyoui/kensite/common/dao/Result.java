/**
 * 
 */
package com.seeyoui.kensite.common.dao;

import java.util.List;

/**
 * 查询结果，存放总记录条数和当前页记录集合
 * 
 * @author zouxuemo
 *
 */
public class Result<T> {
	/**
	 * 当前页记录起始位置（从0开始）
	 */
	private long start = 0;
	
	/**
	 * 当前页尺寸
	 */
	private long limit = 0;
	
	/**
	 * 总记录条数
	 */
	private long total = 0;
	
	/**
	 * 当前页记录条数
	 */
	private List<T> rows;

	public Result(long total, long start, long limit, List<T> rows) {
		this.total = total;
		this.start = start;
		this.limit = limit;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public long getStart() {
		return start;
	}

	public long getLimit() {
		return limit;
	}
	
	public int getPageNum() {
		if (limit > 0)
			return (int)(start / limit + 1);
		else
			return 0;
	}
	
	public int getPageSize() {
		return (int)limit;
	}

	public List<T> getRows() {
		return rows;
	}
	
	public List<T> getData() {
		return rows;
	}
}
