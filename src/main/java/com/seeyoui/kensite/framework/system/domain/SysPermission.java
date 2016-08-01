/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.system.domain;

import com.seeyoui.kensite.common.base.domain.DataEntity;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
public class SysPermission extends DataEntity<SysPermission> {
	private static final long serialVersionUID = 5454155825314635342L;

	private Long sequence;
	private String name;
	private String checked;

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getSequence() {
		return this.sequence;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}