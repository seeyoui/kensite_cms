package com.seeyoui.kensite.common.base.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.util.UserUtils;

public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected String id;
	
	/**
	 * 当前用户
	 */
	protected SysUser currentUser;
	
	/**
	 * 当前页号
	 */
	protected int page;
	/**
	 * 每页行数
	 */
	protected int rows;
	/**
	 * 当前行号
	 */
	protected int row;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIgnore
	public int getPage() {
		if(this.page == 0) {
			return 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JsonIgnore
	public int getRows() {
		if(this.rows == 0) {
			return 20;
		}
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@JsonIgnore
	public int getRow() {
		if((this.page-1)*this.rows < 0) {
			return 0;
		}
		return (this.page-1)*this.rows;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@JsonIgnore
	public SysUser getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(SysUser currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
}
